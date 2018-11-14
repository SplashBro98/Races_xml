package com.epam.races.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRace extends Race<Horse> {
    private List<Horse> horses;

    public HorseRace() {
        horses = new ArrayList<>();
    }

    public HorseRace(String title, String organizer, LocalDate date, LocalTime time, double ticketPrice) {
        super(title, organizer, date, time, ticketPrice);
        horses = new ArrayList<>();
    }

    public List<Horse> getHorses(){
        return Collections.unmodifiableList(horses);
    }

    public Horse getHorseByIndex(int i){
        return horses.get(i);
    }


    @Override
    public void addElement(Horse h) {
        horses.add(h);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(super.toString());
        result.append("Horses: \n");
        horses.forEach(h -> result.append(h.toString()));
        return result.toString();
    }
}
