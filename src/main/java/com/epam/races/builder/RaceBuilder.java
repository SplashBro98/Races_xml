package com.epam.races.builder;

import com.epam.races.entity.Race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RaceBuilder {
    private List<Race> races;

    public RaceBuilder() {
        races = new ArrayList<>();
    }

    public RaceBuilder(List<Race> races) {
        this.races = races;
    }

    public List<Race> getRaces() {
        return Collections.unmodifiableList(races);
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

    public abstract void buildRaceList(String filename);
}
