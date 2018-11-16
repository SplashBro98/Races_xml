package com.epam.races.runner;

import com.epam.races.builder.RaceBuilder;
import com.epam.races.builder.RaceBuilderFactory;
import com.epam.races.entity.Race;
import com.epam.races.parser.DOMRaceHandler;
import com.epam.races.parser.StAXRaceHandler;
import org.xml.sax.*;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {


       // try {
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//
//            XMLReader reader = parser.getXMLReader();
//
//            SAXRaceHandler contentHandler = new SAXRaceHandler();
//            reader.setContentHandler(contentHandler);
//            reader.parse("src\\main\\resources\\data.xml");
//            contentHandler.getRaces().forEach(r -> System.out.println(r.toString()));

//            System.out.println();
//            DOMRaceHandler domRaceHandler = new DOMRaceHandler();
//            domRaceHandler.createRaceList("src\\main\\resources\\data.xml");
//            domRaceHandler.getRaces().forEach(r -> System.out.println(r.toString()));
//
//            StAXRaceHandler raceHandler = new StAXRaceHandler();
//            raceHandler.createRaceList("src\\main\\resources\\data.xml");
//            raceHandler.getRaces().forEach(r -> System.out.println(r.toString()));


        RaceBuilderFactory factory = new RaceBuilderFactory();
        RaceBuilder builder = factory.createRaceBuilder("stax");
        builder.buildRaceList("src\\main\\resources\\data.xml");
        List<Race> races = builder.getRaces();
        races.forEach(r -> System.out.println(r.toString()));

//        }catch (ParserConfigurationException e){
//            e.printStackTrace();
//        }catch (SAXException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
