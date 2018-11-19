package com.epam.races.parser;

import com.epam.races.entity.*;
import com.epam.races.parser.DogRaceEnum;
import com.epam.races.parser.HorseRaceEnum;
import com.epam.races.parser.RaceEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StAXRaceParser {
    private List<Race> races;
    private XMLInputFactory inputFactory;
    public StAXRaceParser() {
        races = new ArrayList<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    public List<Race> getRaces(){
        return Collections.unmodifiableList(races);
    }

    public void createRaceList(InputStream stream) {
        //FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            //inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(stream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName().replace('-','_').toUpperCase();
                    RaceEnum raceEnum = RaceEnum.valueOf(name);
                    Race race;
                    switch (raceEnum){
                        case HORSE_RACE:
                            race = buildHorseRace(reader);
                            races.add(race);
                            break;
                        case DOG_RACE:
                            race = buildDogRace(reader);
                            races.add(race);
                            break;
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
//        } catch (FileNotFoundException ex) {
//            //System.err.println("File " + fileName + " not found! " + ex);
//        }finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                //System.err.println("Impossible close file " + fileName + " : "+e);
            }
        }
    }

    private Race buildHorseRace(XMLStreamReader reader) throws XMLStreamException {
        Race result = new HorseRace();

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
                    name = reader.getLocalName().replace('-','_').toUpperCase();
                    switch (HorseRaceEnum.valueOf(name)) {
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
                    name = reader.getLocalName().replace('-','_').toUpperCase();
                    if (HorseRaceEnum.valueOf(name) == HorseRaceEnum.HORSE_RACE) {
                        return result;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag horse-race");
    }

    private Race buildDogRace(XMLStreamReader reader) throws XMLStreamException {
        Race result = new DogRace();

        result.setTitle(reader.getAttributeValue(null,DogRaceEnum.TITLE.getValue()));
        String organizer = reader.getAttributeValue(null,DogRaceEnum.ORGANIZER.getValue());
        if(organizer != null){
            result.setOrganizer(organizer);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName().replace('-','_').toUpperCase();
                    switch (DogRaceEnum.valueOf(name.replace('-','_').toUpperCase())) {
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
                        case DOGS:
                            getDogList(reader).forEach(h -> result.addElement(h));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replace('-','_').toUpperCase();
                    if (DogRaceEnum.valueOf(name) == DogRaceEnum.DOG_RACE) {
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
        Horse horse = null;
        while (reader.hasNext()) {
            type = reader.next();
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
                        case BREED:
                            horse.setBreed(HorseBreed.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case HORSE:
                            horse = new Horse();
                            break;
                        default:
                            throw new EnumConstantNotPresentException(HorseRaceEnum.class, name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replace('-','_').toUpperCase();
                    if (HorseRaceEnum.valueOf(name) == HorseRaceEnum.HORSE){
                        result.add(horse);
                    }
                    if(HorseRaceEnum.valueOf(name) == HorseRaceEnum.HORSES){
                        return result;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag horses");
    }

    private List<Dog> getDogList(XMLStreamReader reader) throws XMLStreamException{
        List<Dog> result = new ArrayList<>();
        int type;
        String name;
        Dog dog = null;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DogRaceEnum.valueOf(name.replace('-','_').toUpperCase())) {
                        case NICKNAME:
                            dog.setNickname(getXMLText(reader));
                            break;
                        case AGE:
                            dog.setAge(Integer.parseInt(getXMLText(reader)));
                            break;
                        case BREED:
                            dog.setBreed(DogBreed.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case DOG:
                             dog = new Dog();
                            break;
                        default:
                            throw new EnumConstantNotPresentException(DogRaceEnum.class, name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName().replace('-','_').toUpperCase();
                    if (DogRaceEnum.valueOf(name) == DogRaceEnum.DOG){
                        result.add(dog);
                    }
                    if(DogRaceEnum.valueOf(name) == DogRaceEnum.DOGS){
                        return result;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag dogs");
    }

}
