package com.epam.races.parser;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class SimpleHandler implements ContentHandler {

    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        String s = qName;
        for (int i = 0; i < atts.getLength(); i++) {
            s += " " + atts.getQName(i) + "=" + atts.getValue(i) + " ";
        }
        System.out.print(s.trim());
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print(qName);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch,start,length));
    }


    public void setDocumentLocator(Locator locator) {
        //throw new UnsupportedOperationException();
    }

    public void startDocument() throws SAXException {
        //throw new UnsupportedOperationException();
    }

    public void endDocument() throws SAXException {
        //throw new UnsupportedOperationException();
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        //throw new UnsupportedOperationException();
    }

    public void endPrefixMapping(String prefix) throws SAXException {
        //throw new UnsupportedOperationException();
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        //throw new UnsupportedOperationException();
    }

    public void processingInstruction(String target, String data) throws SAXException {
        //throw new UnsupportedOperationException();
    }

    public void skippedEntity(String name) throws SAXException {
        //throw new UnsupportedOperationException();
    }
}
