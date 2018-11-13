package com.epam.races.entity.race;

public enum RaceEnum {
    TITLE("title"),
    ORGANIZER("organizer"),
    DATE("date"),
    TIME("time"),
    PLACE("place"),
    CITY("city"),
    STREET("street"),
    HOUSE_NUMBER("house-number"),
    TICKET_PRICE("ticket-price");





    private String value;

    RaceEnum(String value) {
        this.value = value;
    }
}
