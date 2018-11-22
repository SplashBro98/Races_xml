package com.epam.races.validation;

import com.epam.races.parser.ParserType;

public class ParserValidator {

    public boolean checkParserType(String type){
        try {
            ParserType parserType = ParserType.valueOf(type.toUpperCase());
            return true;
        }catch (EnumConstantNotPresentException e){
            return false;
        }
    }
}
