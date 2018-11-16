package com.epam.races.parser;

public enum DogRaceEnum {
    DOG_RACE("dog-race"),
    TITLE("title"),
    ORGANIZER("organizer"),
    DOGS("dogs"),
    DOG("dog"),
    PLACE("place"),
    DATE("date"),
    TIME("time"),
    CITY("city"),
    STREET("street"),
    HOUSE_NUMBER("house-number"),
    TICKET_PRICE("ticket-price"),
    NICKNAME("nickname"),
    AGE("age"),
    BREED("breed");

    private String value;

    DogRaceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
