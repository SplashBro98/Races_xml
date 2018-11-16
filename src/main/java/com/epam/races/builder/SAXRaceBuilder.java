package com.epam.races.builder;

import com.epam.races.parser.SAXRaceHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXRaceBuilder extends RaceBuilder {
    private SAXRaceHandler raceHandler;
    private XMLReader reader;

    public SAXRaceBuilder() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            raceHandler = new SAXRaceHandler();

            reader.setContentHandler(raceHandler);
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildRaceList(String filename) {
        try {
            reader.parse(filename);
            setRaces(raceHandler.getRaces());
        }catch (SAXException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
