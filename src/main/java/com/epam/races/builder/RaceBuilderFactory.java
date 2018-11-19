package com.epam.races.builder;

import com.epam.races.parser.ParserType;

public class RaceBuilderFactory {

    public RaceBuilder createRaceBuilder(String type){
        ParserType parserType = ParserType.valueOf(type.toUpperCase());
        switch (parserType){
            case DOM:
                return new DOMRaceBuilder();
            case SAX:
                return new SAXRaceBuilder();
            case STAX:
                return new StAXRaceBuilder();
            default:
                throw new EnumConstantNotPresentException(ParserType.class, type);
        }
    }
}
