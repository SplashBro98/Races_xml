package com.epam.races.runner;

import com.epam.races.parser.dom.DOMRaceHandler;
import com.epam.races.parser.sax.SAXRaceHandler;
import com.epam.races.parser.stax.StAXRaceHandler;
import org.xml.sax.*;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
//
//            XMLReader reader = parser.getXMLReader();
//
//            SAXRaceHandler contentHandler = new SAXRaceHandler();
//            reader.setContentHandler(contentHandler);
//            reader.parse("src\\main\\resources\\data.xml");
//            contentHandler.getRaces().forEach(r -> System.out.println(r.toString()));
//
//            System.out.println();
//            DOMRaceHandler domRaceHandler = new DOMRaceHandler();
//            domRaceHandler.createRaceList("src\\main\\resources\\data.xml");
//            domRaceHandler.getRaces().forEach(r -> System.out.println(r.toString()));

            StAXRaceHandler raceHandler = new StAXRaceHandler();
            raceHandler.buildRaceList("src\\main\\resources\\data.xml");
            raceHandler.getRaces().forEach(r -> System.out.println(r.toString()));


        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
