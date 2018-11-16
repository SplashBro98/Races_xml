package com.epam.races.command;

import com.epam.races.builder.RaceBuilder;
import com.epam.races.builder.RaceBuilderFactory;
import com.epam.races.entity.Race;
import com.epam.races.controller.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ParseCommand implements Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RaceBuilderFactory raceFactory = new RaceBuilderFactory();
        RaceBuilder raceBuilder = raceFactory.createRaceBuilder(req.getParameter("parser"));
        raceBuilder.buildRaceList(req.getServletContext().getRealPath("/WEB-INF/classes/data.xml"));
        List<Race> races = raceBuilder.getRaces();
        req.setAttribute("races", races);
        req.setAttribute("parser", req.getParameter("parser").toUpperCase() + " parser");
        return ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.MAIN_PAGE_PATH);
    }
}
