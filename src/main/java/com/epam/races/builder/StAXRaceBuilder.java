package com.epam.races.builder;

import com.epam.races.parser.StAXRaceParser;

import java.io.InputStream;

public class StAXRaceBuilder extends RaceBuilder {
    private StAXRaceParser raceHandler;

    public StAXRaceBuilder() {
        raceHandler = new StAXRaceParser();
    }

    @Override
    public void buildRaceList(InputStream stream) {
        raceHandler.createRaceList(stream);
        setRaces(raceHandler.getRaces());
    }
}
