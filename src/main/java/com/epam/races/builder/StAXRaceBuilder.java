package com.epam.races.builder;

import com.epam.races.parser.StAXRaceHandler;

public class StAXRaceBuilder extends RaceBuilder {
    private StAXRaceHandler raceHandler;

    public StAXRaceBuilder() {
        raceHandler = new StAXRaceHandler();
    }

    @Override
    public void buildRaceList(String filename) {
        raceHandler.createRaceList(filename);
        setRaces(raceHandler.getRaces());
    }
}
