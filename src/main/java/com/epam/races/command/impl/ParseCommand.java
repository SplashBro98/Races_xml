package com.epam.races.command.impl;

import com.epam.races.builder.RaceBuilder;
import com.epam.races.builder.RaceBuilderFactory;
import com.epam.races.command.Command;
import com.epam.races.command.PageManager;
import com.epam.races.exception.ServiceException;
import com.epam.races.service.ParserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ParseCommand implements Command {


    private static Logger logger = LogManager.getLogger(ParseCommand.class);

    public static final String PARSER = " parser";
    private static final String ATTR_HORSE_RACES = "horseRaces";
    private static final String ATTR_DOG_RACES = "dogRaces";
    private static final String PARAM_PARSER = "parser";
    private static final String PARAM_FILENAME = "fileName";

    @Override
    public String execute(HttpServletRequest req) {
        ParserService service = new ParserService();

        String parserType = req.getParameter(PARAM_PARSER);
        String page;
        logger.log(Level.INFO, "ParserType: " + parserType.toUpperCase());


        if(service.parse(parserType)) {
            RaceBuilderFactory factory = new RaceBuilderFactory();
            RaceBuilder raceBuilder = factory.createRaceBuilder(parserType);

            service.setRaceBuilder(raceBuilder);
            try {
                service.createLists(req.getPart(PARAM_FILENAME));
                req.setAttribute(ATTR_HORSE_RACES, service.getHorseRaces());
                req.setAttribute(ATTR_DOG_RACES, service.getDogRaces());
                req.setAttribute(PARAM_PARSER, parserType.toUpperCase() + PARSER);
                page = PageManager.INSTANCE.getProperty(PageManager.MAIN_PAGE_PATH);
            } catch (ServiceException e) {
                logger.error("Problem with parsing", e);
                page = PageManager.INSTANCE.getProperty(PageManager.ERROR_PAGE_PATH);
            } catch (IOException e){
                logger.error("Problem with method getPart");
                page = PageManager.INSTANCE.getProperty(PageManager.ERROR_PAGE_PATH);
            } catch (ServletException e){
                logger.error("Problem with method getPart");
                page = PageManager.INSTANCE.getProperty(PageManager.ERROR_PAGE_PATH);
            }
        }
        else {
            page =  PageManager.INSTANCE.getProperty(PageManager.ERROR_PAGE_PATH);
        }

        return page;
    }
}
