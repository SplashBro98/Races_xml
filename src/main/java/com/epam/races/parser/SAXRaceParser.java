package com.epam.races.parser;

import com.epam.races.entity.*;
import com.epam.races.parser.DogRaceEnum;
import com.epam.races.parser.HorseRaceEnum;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class SAXRaceParser implements ContentHandler {
    public static final String XML_TAG_HORSE_RACE = "horse-race";
    public static final String XML_TAG_DOG_RACE = "dog-race";
    public static final String XML_ATTR_TITLE = "title";
    public static final String XML_TAG_HORSE = "horse";
    public static final String XML_TAG_DOG = "dog";
    public static final int TITLE_POSITION = 0;
    public static final int ORGANIZER_POSITION = 1;

    private List<Race> races = new ArrayList<>();
    private Race currentRace;
    private Horse currentHorse;
    private Dog currentDog;

    private EnumSet<HorseRaceEnum> horseRaceEnumSet = EnumSet.range(HorseRaceEnum.DATE,HorseRaceEnum.BREED);
    private EnumSet<DogRaceEnum> dogRaceEnumSet = EnumSet.range(DogRaceEnum.DATE,DogRaceEnum.BREED);
    private HorseRaceEnum currentHorseEnum;
    private DogRaceEnum currentDogEnum;

    public List<Race> getRaces() {
        return Collections.unmodifiableList(races);
    }

    @Override
    public void startDocument() throws SAXException {

    }
    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

        switch (qName){
            case XML_TAG_HORSE_RACE:
                currentRace = new HorseRace();
                if(atts.getLength() == 1){
                    currentRace.setTitle(atts.getValue(TITLE_POSITION));
                }
                else{
                    if(atts.getQName(0).equals(XML_ATTR_TITLE)){
                        currentRace.setTitle(atts.getValue(TITLE_POSITION));
                        currentRace.setOrganizer(atts.getValue(ORGANIZER_POSITION));
                    }
                    else {
                        currentRace.setTitle(atts.getValue(ORGANIZER_POSITION));
                        currentRace.setOrganizer(atts.getValue(TITLE_POSITION));
                    }
                }
                break;
            case XML_TAG_DOG_RACE:
                currentRace = new DogRace();
                if(atts.getLength() == 1){
                    currentRace.setTitle(atts.getValue(TITLE_POSITION));
                }
                else{
                    if(atts.getQName(0).equals(XML_ATTR_TITLE)){
                        currentRace.setTitle(atts.getValue(TITLE_POSITION));
                        currentRace.setOrganizer(atts.getValue(ORGANIZER_POSITION));
                    }
                    else {
                        currentRace.setTitle(atts.getValue(ORGANIZER_POSITION));
                        currentRace.setOrganizer(atts.getValue(TITLE_POSITION));
                    }
                }
                break;
            case XML_TAG_HORSE:
                currentHorse = new Horse();
                break;
            case XML_TAG_DOG:
                currentDog = new Dog();
                break;
            default:
                if(currentRace == null){
                    break;
                }
                if(currentRace.getClass() == HorseRace.class){
                    HorseRaceEnum temp = HorseRaceEnum.valueOf(qName.replace('-','_').toUpperCase());
                    if(horseRaceEnumSet.contains(temp)){
                        currentHorseEnum = temp;
                    }
                }
                else {
                    DogRaceEnum temp = DogRaceEnum.valueOf(qName.replace('-','_').toUpperCase());
                    if(dogRaceEnumSet.contains(temp)){
                        currentDogEnum = temp;
                    }
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
            case XML_TAG_HORSE_RACE:
                races.add(currentRace);
                break;
            case XML_TAG_DOG_RACE:
                races.add(currentRace);
                break;
            case XML_TAG_HORSE:
                currentRace.addElement(currentHorse);
                break;
            case XML_TAG_DOG:
                currentRace.addElement(currentDog);
                break;
            default:
        }
        currentHorseEnum = null;
        currentDogEnum = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        boolean isCurrentHorseRace = currentHorseEnum == null;
        boolean isCurrentDogRace = currentDogEnum == null;
        if(isCurrentHorseRace && isCurrentDogRace){
            return;
        }
        if(!isCurrentHorseRace) {
            switch (currentHorseEnum) {
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
                default:
                    throw new EnumConstantNotPresentException(HorseRaceEnum.class, s);
            }
        }
        else{
            switch (currentDogEnum) {
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
                    currentDog.setNickname(s);
                    break;
                case BREED:
                    currentDog.setBreed(DogBreed.valueOf(s.toUpperCase()));
                    break;
                case AGE:
                    currentDog.setAge(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(DogRaceEnum.class, s);
            }
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
