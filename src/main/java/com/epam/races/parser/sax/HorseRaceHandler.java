package com.epam.races.parser.sax;

import com.epam.races.entity.horse.HorseBreed;
import com.epam.races.entity.horse.Horse;
import com.epam.races.entity.race.HorseRace;
import com.epam.races.entity.race.HorseRaceEnum;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRaceHandler implements ContentHandler {
    public static final String XML_TAG_HORSE_RACE = "horse-race";
    public static final String XML_TAG_TITLE = "title";
    public static final String XML_TAG_HORSE = "horse";

    private List<HorseRace> horseRaces = new ArrayList<>();
    private HorseRace currentRace = null;
    private Horse currentHorse = null;
    private HorseRaceEnum currentEnum = null;

    public List<HorseRace> getHorseRaces() {
        return Collections.unmodifiableList(horseRaces);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing is started");
    }
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing is finished");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if(qName.equals(XML_TAG_HORSE_RACE)){
            currentRace = new HorseRace();
            if(atts.getLength() == 1){
                currentRace.setTitle(atts.getValue(0));
            }
            else{
                if(atts.getQName(0).equals(XML_TAG_TITLE)){
                    currentRace.setTitle(atts.getValue(0));
                    currentRace.setOrganizer(atts.getValue(1));
                }
                else {
                    currentRace.setTitle(atts.getValue(1));
                    currentRace.setOrganizer(atts.getValue(0));
                }
            }
        }
        else if(qName.equals(XML_TAG_HORSE)){
            currentHorse = new Horse();
        }
        else {
            currentEnum = HorseRaceEnum.valueOf(qName.replace('-','_').toUpperCase());
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals(XML_TAG_HORSE_RACE)){
            horseRaces.add(currentRace);
        }
        else if(qName.equals(XML_TAG_HORSE)){
            currentRace.addHorse(currentHorse);
        }
        currentEnum = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        if(currentEnum == null){
            return;
        }
        switch (currentEnum){
            case DATE:
                currentRace.setDate(LocalDate.parse(s));
                break;
            case TIME:
                currentRace.setTime(LocalTime.parse(s));
                break;
            case CITY:
                currentRace.getPlace().setCity(s);
                break;
            case STREET:
                currentRace.getPlace().setStreet(s);
                break;
            case HOUSE_NUMBER:
                currentRace.getPlace().setHouseNumber(Integer.parseInt(s));
                break;
            case TICKET_PRICE:
                currentRace.setTicketPrice(Double.parseDouble(s));
                break;
            case NICKNAME:
                currentHorse.setNickname(s);
                break;
            case BREED:
                currentHorse.setBreed(HorseBreed.valueOf(s.toUpperCase()));
                break;
            case AGE:
                currentHorse.setAge(Integer.parseInt(s));
                break;
            case RACES:
                break;
            case HORSES:
                break;
            case PLACE:
                break;
            default:
                throw new EnumConstantNotPresentException(HorseRaceEnum.class, s);

        }

    }


    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
