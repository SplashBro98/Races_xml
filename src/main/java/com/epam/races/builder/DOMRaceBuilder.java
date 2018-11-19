package com.epam.races.builder;

import com.epam.races.entity.Race;
import com.epam.races.parser.DOMRaceParser;

import java.io.InputStream;
import java.util.List;

public class DOMRaceBuilder extends RaceBuilder {
    private DOMRaceParser raceHandler;

    public DOMRaceBuilder(List<Race> races) {
        super(races);
        raceHandler = new DOMRaceParser();
    }

    public DOMRaceBuilder() {
        raceHandler = new DOMRaceParser();
    }

    @Override
    public void buildRaceList(InputStream stream) {
        raceHandler.createRaceList(stream);
        setRaces(raceHandler.getRaces());
    }
}
