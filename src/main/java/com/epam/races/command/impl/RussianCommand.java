package com.epam.races.command.impl;

import com.epam.races.command.Command;
import com.epam.races.command.PageManager;

import javax.servlet.http.HttpServletRequest;

public class RussianCommand implements Command {

    @Override
    public String execute(HttpServletRequest req)  {
        req.setAttribute("language","ru_RU");
        return PageManager.INSTANCE.getProperty(PageManager.START_PAGE_PATH);
    }
}
