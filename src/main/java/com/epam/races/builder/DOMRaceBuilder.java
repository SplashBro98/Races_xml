package com.epam.races.builder;

import com.epam.races.entity.Race;
import com.epam.races.parser.DOMRaceHandler;

import javax.xml.parsers.DocumentBuilder;
import java.util.List;

public class DOMRaceBuilder extends RaceBuilder {
    private DOMRaceHandler raceHandler;

    public DOMRaceBuilder(List<Race> races) {
        super(races);
        raceHandler = new DOMRaceHandler();
    }

    public DOMRaceBuilder() {
        raceHandler = new DOMRaceHandler();
    }

    @Override
    public void buildRaceList(String filename) {
        raceHandler.createRaceList(filename);
        setRaces(raceHandler.getRaces());
    }
}
