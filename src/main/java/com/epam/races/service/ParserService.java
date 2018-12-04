package com.epam.races.service;

import com.epam.races.builder.RaceBuilder;
import com.epam.races.builder.RaceBuilderFactory;
import com.epam.races.entity.DogRace;
import com.epam.races.entity.HorseRace;
import com.epam.races.entity.Race;
import com.epam.races.exception.ServiceException;
import com.epam.races.validation.ParserValidator;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParserService {

    private RaceBuilder raceBuilder;
    private List<Race> horseRaces;
    private List<Race> dogRaces;

    public ParserService() {

    }

    public boolean parse(String parserType){
        ParserValidator validator = new ParserValidator();
        return validator.checkParserType(parserType);
    }

    public void createLists(Part part) throws ServiceException {

        try {
            raceBuilder.buildRaceList(part.getInputStream());
            List<Race> races = raceBuilder.getRaces();

            horseRaces = races.stream().filter(r -> r.getClass() == HorseRace.class).
                    collect(Collectors.toList());

            dogRaces = races.stream().filter(r -> r.getClass() == DogRace.class).
                    collect(Collectors.toList());
        }catch (IOException e){
            throw new ServiceException(e);
        }
    }

    public RaceBuilder getRaceBuilder() {
        return raceBuilder;
    }

    public void setRaceBuilder(RaceBuilder raceBuilder) {
        this.raceBuilder = raceBuilder;
    }

    public List<Race> getHorseRaces() {
        return Collections.unmodifiableList(horseRaces);
    }

    public List<Race> getDogRaces() {
        return Collections.unmodifiableList(dogRaces);
    }
}
