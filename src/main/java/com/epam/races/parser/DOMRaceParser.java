package com.epam.races.parser;

import com.epam.races.entity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DOMRaceParser {
    private List<Race> races;
    private DocumentBuilder docBuilder;
    public DOMRaceParser() {
        races = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Parser configuration Error: " + e);
        }
    }
    public List<Race> getRaces() {
        return Collections.unmodifiableList(races);
    }
    public void createRaceList(InputStream stream) {
        Document doc;
        try {
            doc = docBuilder.parse(stream);
            Element root = doc.getDocumentElement();

            NodeList racesList = root.getElementsByTagName(HorseRaceEnum.HORSE_RACE.getValue());
            for (int i = 0; i < racesList.getLength(); i++) {
                Element raceElement = (Element) racesList.item(i);
                Race race = buildHorseRace(raceElement);
                races.add(race);
            }

            racesList = root.getElementsByTagName(RaceEnum.DOG_RACE.getValue());
            for (int i = 0; i < racesList.getLength(); i++) {
                Element raceElement = (Element) racesList.item(i);
                Race race = buildDogRace(raceElement);
                races.add(race);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }
    private HorseRace buildHorseRace(Element raceElement) {
        HorseRace race = new HorseRace();

        race.setTitle(raceElement.getAttribute(HorseRaceEnum.TITLE.getValue()));
        String organizer = raceElement.getAttribute(HorseRaceEnum.ORGANIZER.getValue());
        if(!organizer.equals("")) {
            race.setOrganizer(organizer);
        }

        race.setDate(LocalDate.parse(getElementTextContent(raceElement, HorseRaceEnum.DATE.getValue())));
        race.setTime(LocalTime.parse(getElementTextContent(raceElement, HorseRaceEnum.TIME.getValue())));
        Double ticketPrice = Double.parseDouble(getElementTextContent(raceElement,
                HorseRaceEnum.TICKET_PRICE.getValue()));
        race.setTicketPrice(ticketPrice);

        Race.Place place = race.getPlace();
        Element placeElement = (Element) raceElement.getElementsByTagName(HorseRaceEnum.PLACE.getValue()).item(0);

        place.setCity(getElementTextContent(placeElement, HorseRaceEnum.CITY.getValue()));
        place.setStreet(getElementTextContent(placeElement, HorseRaceEnum.STREET.getValue()));
        place.setHouseNumber(Integer.parseInt(getElementTextContent(placeElement, HorseRaceEnum.HOUSE_NUMBER.getValue())));

        Element horsesElement = (Element) raceElement.
                getElementsByTagName(HorseRaceEnum.HORSES.getValue()).item(0);
        NodeList horseList = horsesElement.getElementsByTagName(HorseRaceEnum.HORSE.getValue());
        for (int i = 0; i < horseList.getLength(); i++) {
            Element horseElement = (Element) horseList.item(i);
            Horse horse = new Horse();
            horse.setNickname(getElementTextContent(horseElement,HorseRaceEnum.NICKNAME.getValue()));
            horse.setAge(Integer.parseInt(getElementTextContent(horseElement,HorseRaceEnum.AGE.getValue())));
            horse.setBreed(HorseBreed.valueOf(getElementTextContent(horseElement,HorseRaceEnum.BREED.getValue()).
                    toUpperCase()));
            race.addElement(horse);
        }

        return race;
    }
    private DogRace buildDogRace(Element raceElement) {
        DogRace race = new DogRace();

        race.setTitle(raceElement.getAttribute(DogRaceEnum.TITLE.getValue()));
        String organizer = raceElement.getAttribute(DogRaceEnum.ORGANIZER.getValue());
        if(!organizer.equals("")) {
            race.setOrganizer(organizer);
        }

        race.setDate(LocalDate.parse(getElementTextContent(raceElement, DogRaceEnum.DATE.getValue())));
        race.setTime(LocalTime.parse(getElementTextContent(raceElement, DogRaceEnum.TIME.getValue())));
        Double ticketPrice = Double.parseDouble(getElementTextContent(raceElement,DogRaceEnum.TICKET_PRICE.getValue()));
        race.setTicketPrice(ticketPrice);

        Race.Place place = race.getPlace();
        Element placeElement = (Element) raceElement.getElementsByTagName(DogRaceEnum.PLACE.getValue()).item(0);

        place.setCity(getElementTextContent(placeElement, DogRaceEnum.CITY.getValue()));
        place.setStreet(getElementTextContent(placeElement, DogRaceEnum.STREET.getValue()));
        place.setHouseNumber(Integer.parseInt(getElementTextContent(placeElement, DogRaceEnum.HOUSE_NUMBER.getValue())));

        Element dogsElement = (Element) raceElement.getElementsByTagName("dogs").item(0);
        NodeList dogList = dogsElement.getElementsByTagName(DogRaceEnum.DOG.getValue());
        for (int i = 0; i < dogList.getLength(); i++) {
            Element dogElement = (Element) dogList.item(i);
            Dog dog = new Dog();
            dog.setNickname(getElementTextContent(dogElement,DogRaceEnum.NICKNAME.getValue()));
            dog.setAge(Integer.parseInt(getElementTextContent(dogElement,DogRaceEnum.AGE.getValue())));
            dog.setBreed(DogBreed.valueOf(getElementTextContent(dogElement,DogRaceEnum.BREED.getValue()).toUpperCase()));
            race.addElement(dog);
        }

        return race;
    }


    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String complexTypeName) {
        NodeList nList = element.getElementsByTagName(complexTypeName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
