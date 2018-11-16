package com.epam.races.parser;

public enum HorseRaceEnum {
    HORSE_RACE("horse-race"),
    TITLE("title"),
    ORGANIZER("organizer"),
    HORSES("horses"),
    HORSE("horse"),
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

    HorseRaceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
