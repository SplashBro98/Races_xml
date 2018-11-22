package com.epam.races.command.impl;

import com.epam.races.command.Command;
import com.epam.races.controller.ConfigurationManager;
import com.epam.races.entity.User;
import com.epam.races.service.LoginService;
import com.epam.races.storage.FakeStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) throws ServletException, IOException {

        String page;
        LoginService service = new LoginService();
        if(service.checkUser(req.getParameter("login"),req.getParameter("password"))){
            page = ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.START_PAGE_PATH);
        }else {
            req.setAttribute("incorrect","Incorrect login or password");
            page = ConfigurationManager.INSTANCE.getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
        return page;
    }
}
