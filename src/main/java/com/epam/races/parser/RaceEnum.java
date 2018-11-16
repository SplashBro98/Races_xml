package com.epam.races.parser;

public enum RaceEnum {
    RACES("races"),
    HORSE_RACE("horse-race"),
    DOG_RACE("dog-race");

    private String value;

    RaceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
