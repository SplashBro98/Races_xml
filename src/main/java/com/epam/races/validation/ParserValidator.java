package com.epam.races.validation;

import com.epam.races.parser.ParserType;

public class ParserValidator {

    public boolean checkParserType(String type){
        ParserType parserType = ParserType.valueOf(type.toUpperCase());
        boolean result = true;
        if(parserType == null){
            result = false;
        }
        return result;
    }
}
