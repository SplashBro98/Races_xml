package com.epam.races.entity.race;

import com.epam.races.entity.horse.Horse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRace extends Race {
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

    public void addHorse(Horse horse){
        horses.add(horse);
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
