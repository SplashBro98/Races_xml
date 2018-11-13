package com.epam.races.runner;

import com.epam.races.parser.sax.HorseRaceHandler;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String filename = "data\\races.xml";
        DOMParser parser = new DOMParser();
        try {
            //parser.setFeature("http://xml.org/sax/features/validation", true);
            parser.parse(filename);
            System.out.println("Check is finished");
            XMLReader reader = XMLReaderFactory.createXMLReader();
            HorseRaceHandler contentHandler = new HorseRaceHandler();
            reader.setContentHandler(contentHandler);
            reader.parse("data\\races.xml");
            contentHandler.getHorseRaces().forEach(r -> System.out.println(r.toString()));

        } catch (SAXNotRecognizedException e) {
            e.printStackTrace();
        } catch (SAXNotSupportedException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
////создание SAX-анализатора
//            XMLReader reader = XMLReaderFactory.createXMLReader();
//            SimpleHandler contentHandler = new SimpleHandler();
//            reader.setContentHandler(contentHandler);
//            reader.parse("data\\races.xml");
//        } catch (SAXException e) {
//            e.printStackTrace();
//            System.out.print("ошибка SAX парсера");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.print("ошибка I/О потока");
//        }
    }
}
