package com.epam.races.command.impl;

import com.epam.races.builder.RaceBuilder;
import com.epam.races.builder.RaceBuilderFactory;
import com.epam.races.command.Command;
import com.epam.races.entity.DogRace;
import com.epam.races.entity.HorseRace;
import com.epam.races.entity.Race;
import com.epam.races.controller.ConfigurationManager;
import com.epam.races.service.ParserService;
import com.epam.races.validation.ParserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ParseCommand implements Command {

    private static Logger logger = LogManager.getLogger(ParseCommand.class);

    private static final String ATTR_HORSE_RACES = "horseRaces";
    private static final String ATTR_DOG_RACES = "dogRaces";
    private static final String PARAM_PARSER = "parser";
    private static final String PARAM_FILENAME = "fileName";

    @Override
    public String execute(HttpServletRequest req) throws ServletException, IOException {
        ParserService service = new ParserService();

        String parserType = req.getParameter(PARAM_PARSER);
        String page;
        logger.log(Level.INFO, "ParserType: " + parserType.toUpperCase());


        if(service.parse(parserType)) {
            RaceBuilderFactory factory = new RaceBuilderFactory();
            RaceBuilder raceBuilder = factory.createRaceBuilder(parserType);

            service.setRaceBuilder(raceBuilder);
            service.createLists(req.getPart(PARAM_FILENAME));

            req.setAttribute(ATTR_HORSE_RACES, service.getHorseRaces());
            req.setAttribute(ATTR_DOG_RACES, service.getDogRaces());
            req.setAttribute(PARAM_PARSER, parserType.toUpperCase() + " parser");
            page = ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        }
        else {
            page =  ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }

        return page;
    }
}
