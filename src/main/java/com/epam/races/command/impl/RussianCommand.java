package com.epam.races.command.impl;

import com.epam.races.command.Command;
import com.epam.races.controller.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RussianCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) throws ServletException, IOException {
        req.setAttribute("language","ru_RU");
        return ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.START_PAGE_PATH);
    }
}
