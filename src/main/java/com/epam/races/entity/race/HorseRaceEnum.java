package com.epam.races.entity.race;

public enum HorseRaceEnum {
    RACES("races"),
    TITLE("title"),
    ORGANIZER("organizer"),
    DATE("date"),
    TIME("time"),
    PLACE("place"),
    CITY("city"),
    STREET("street"),
    HOUSE_NUMBER("house-number"),
    TICKET_PRICE("ticket-price"),
    HORSES("horses"),
    HORSE("horse"),
    NICKNAME("nickname"),
    AGE("age"),
    BREED("breed");



    private String value;

    HorseRaceEnum(String value) {
        this.value = value;
    }
}
