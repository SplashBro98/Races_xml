package com.epam.races.entity.race;

import com.epam.races.entity.dog.Dog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DogRace extends Race {
    private List<Dog> dogs;

    public DogRace(String title, String organizer, LocalDate date, LocalTime time, double ticketPrice) {
        super(title, organizer, date, time, ticketPrice);
        this.dogs = new ArrayList<>();
    }

    public List<Dog> getDogs(){
        return Collections.unmodifiableList(dogs);
    }

    public Dog getHorseByIndex(int i){
        return dogs.get(i);
    }

    public void addDog(Dog dog){
        dogs.add(dog);
    }
}
