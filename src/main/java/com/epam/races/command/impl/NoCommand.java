package com.epam.races.command.impl;

import com.epam.races.command.Command;
import com.epam.races.command.PageManager;

import javax.servlet.http.HttpServletRequest;

public class NoCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return PageManager.INSTANCE.getProperty(PageManager.ERROR_PAGE_PATH);
    }
}
