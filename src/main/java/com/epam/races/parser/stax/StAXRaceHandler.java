package com.epam.races.parser.stax;

import com.epam.races.entity.Horse;
import com.epam.races.entity.HorseBreed;
import com.epam.races.entity.HorseRace;
import com.epam.races.entity.Race;
import com.epam.races.parser.HorseRaceEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StAXRaceHandler {
    private List<Race> races;
    private XMLInputFactory inputFactory;
    public StAXRaceHandler() {
        races = new ArrayList<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Race> getRaces(){
        return Collections.unmodifiableList(races);
    }

    public void buildRaceList(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
// StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (HorseRaceEnum.valueOf(name.replace('-','_').toUpperCase()) == HorseRaceEnum.HORSE_RACE) {
                        HorseRace race = buildHorseRace(reader);
                        races.add(race);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file "+fileName+" : "+e);
            }
        }
    }

    private HorseRace buildHorseRace(XMLStreamReader reader) throws XMLStreamException {
        HorseRace result = new HorseRace();

        result.setTitle(reader.getAttributeValue(null,HorseRaceEnum.TITLE.getValue()));
        String organizer = reader.getAttributeValue(null,HorseRaceEnum.ORGANIZER.getValue());
        if(organizer != null){
            result.setOrganizer(organizer);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (HorseRaceEnum.valueOf(name.replace('-','_').toUpperCase())) {
                        case DATE:
                            result.setDate(LocalDate.parse(getXMLText(reader)));
                            break;
                        case TIME:
                            result.setTime(LocalTime.parse(getXMLText(reader)));
                            break;
                        case TICKET_PRICE:
                            result.setTicketPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PLACE:
                            setPlace(reader,result.getPlace());
                            break;
                        case HORSES:
                            getHorseList(reader).forEach(h -> result.addElement(h));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (HorseRaceEnum.valueOf(name.replace('-','_').toUpperCase()) == HorseRaceEnum.HORSE_RACE) {
                        return result;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private void setPlace(XMLStreamReader reader, Race.Place place) throws XMLStreamException {
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (HorseRaceEnum.valueOf(name.replace('-','_').toUpperCase())) {
                        case CITY:
                            place.setCity(getXMLText(reader));
                            break;
                        case STREET:
                            place.setStreet(getXMLText(reader));
                            break;
                        case HOUSE_NUMBER:
                            place.setHouseNumber(Integer.parseInt(getXMLText(reader)));
                            break;
                        default:
                            throw new EnumConstantNotPresentException(HorseRaceEnum.class, name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (HorseRaceEnum.valueOf(name.replace('-','_').toUpperCase()) == HorseRaceEnum.PLACE){
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag place");
    }

    private List<Horse> getHorseList(XMLStreamReader reader) throws XMLStreamException{
        List<Horse> result = new ArrayList<>();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            Horse horse = new Horse();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (HorseRaceEnum.valueOf(name.replace('-','_').toUpperCase())) {
                        case NICKNAME:
                            horse.setNickname(getXMLText(reader));
                            break;
                        case AGE:
                            horse.setAge(Integer.parseInt(getXMLText(reader)));
                            break;
                        case HOUSE_NUMBER:
                            horse.setBreed(HorseBreed.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        default:
                            throw new EnumConstantNotPresentException(HorseRaceEnum.class, name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (HorseRaceEnum.valueOf(name.replace('-','_').toUpperCase()) == HorseRaceEnum.HORSE){
                        result.add(horse);
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag horse");
    }

}
