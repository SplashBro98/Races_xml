package com.epam.races.command;

import com.epam.races.controller.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RussianCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("language","ru_RU");
        return ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.START_PAGE_PATH);
    }
}
