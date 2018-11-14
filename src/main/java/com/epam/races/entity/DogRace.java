package com.epam.races.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DogRace extends Race<Dog> {
    private List<Dog> dogs;

    public DogRace() {
        dogs = new ArrayList<>();
    }

    public DogRace(String title, String organizer, LocalDate date, LocalTime time, double ticketPrice) {
        super(title, organizer, date, time, ticketPrice);
        dogs = new ArrayList<>();
    }

    public List<Dog> getDogs(){
        return Collections.unmodifiableList(dogs);
    }

    public Dog getHorseByIndex(int i){
        return dogs.get(i);
    }

    @Override
    public void addElement(Dog dog) {
        dogs.add(dog);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(super.toString());
        result.append("Dogs: \n");
        dogs.forEach(d -> result.append(d.toString()));
        return result.toString();
    }
}
