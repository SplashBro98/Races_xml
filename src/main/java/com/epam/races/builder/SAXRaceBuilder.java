package com.epam.races.builder;

import com.epam.races.parser.SAXRaceParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class SAXRaceBuilder extends RaceBuilder {
    private SAXRaceParser raceHandler;
    private XMLReader reader;

    public SAXRaceBuilder() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            raceHandler = new SAXRaceParser();

            reader.setContentHandler(raceHandler);
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildRaceList(InputStream stream) {
        try {
            reader.parse(new InputSource(stream));
            setRaces(raceHandler.getRaces());
        }catch (SAXException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
