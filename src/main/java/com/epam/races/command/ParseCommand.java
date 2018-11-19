package com.epam.races.command;

import com.epam.races.builder.RaceBuilder;
import com.epam.races.builder.RaceBuilderFactory;
import com.epam.races.entity.DogRace;
import com.epam.races.entity.Horse;
import com.epam.races.entity.HorseRace;
import com.epam.races.entity.Race;
import com.epam.races.controller.ConfigurationManager;
import com.epam.races.validation.ParserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseCommand implements Command {

    private static Logger logger = LogManager.getLogger(ParseCommand.class);

    private static final String ATTR_HORSE_RACES = "horseRaces";
    private static final String ATTR_DOG_RACES = "dogRaces";
    private static final String PARAM_PARSER = "parser";
    private static final String PARAM_FILENAME = "fileName";

    @Override
    public String execute(HttpServletRequest req) throws ServletException, IOException {
        RaceBuilderFactory raceFactory = new RaceBuilderFactory();
        String page;

        String parserType = req.getParameter(PARAM_PARSER);
        logger.log(Level.INFO, "ParserType: " + parserType.toUpperCase());

        ParserValidator validator = new ParserValidator();
        if(validator.checkParserType(parserType)) {
            RaceBuilder raceBuilder = raceFactory.createRaceBuilder(parserType);
            raceBuilder.buildRaceList(req.getPart(PARAM_FILENAME).getInputStream());
            List<Race> races = raceBuilder.getRaces();

            List<Race> horseRaces = races.stream().filter(r -> r.getClass() == HorseRace.class).
                    collect(Collectors.toList());
            List<Race> dogRaces = races.stream().filter(r -> r.getClass() == DogRace.class).
                    collect(Collectors.toList());


            req.setAttribute(ATTR_HORSE_RACES, horseRaces);
            req.setAttribute(ATTR_DOG_RACES, dogRaces);
            req.setAttribute(PARAM_PARSER, parserType.toUpperCase() + " parser");
            page = ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        }
        else {
            page =  ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }

        return page;
    }
}
