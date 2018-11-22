package com.epam.races.command.impl;

import com.epam.races.command.Command;
import com.epam.races.controller.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EnglishCommand implements Command {
    private static Logger logger = LogManager.getLogger(EnglishCommand.class);

    @Override
    public String execute(HttpServletRequest req) throws ServletException, IOException {
        req.setAttribute("language","en_EN");
//        logger.log(Level.INFO, "ServletPath: " + req.getServletPath());
//        logger.log(Level.INFO, "PathInfo: " + req.getPathInfo());
//        logger.log(Level.INFO, "RealPath: " + req.getServletContext().getRealPath("/"));
//        logger.log(Level.INFO, "ContextPath: " + req.getContextPath());
//        logger.log(Level.INFO, "RequestURL: " + req.getRequestURL());
        return ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.START_PAGE_PATH);
    }
}
