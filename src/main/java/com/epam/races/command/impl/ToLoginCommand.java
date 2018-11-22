package com.epam.races.command.impl;

import com.epam.races.command.Command;
import com.epam.races.controller.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ToLoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) throws ServletException, IOException {
        return ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
    }
}
