package com.epam.races.command.impl;

import com.epam.races.command.Command;
import com.epam.races.command.PageManager;
import com.epam.races.service.LoginService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req)  {

        String page;
        LoginService service = new LoginService();
        if(service.checkUser(req.getParameter(LOGIN),req.getParameter(PASSWORD))){
            page = PageManager.INSTANCE.getProperty(PageManager.START_PAGE_PATH);
        }else {
            req.setAttribute("incorrect","Incorrect login or password");
            page = PageManager.INSTANCE.getProperty(PageManager.LOGIN_PAGE_PATH);
        }
        return page;
    }
}
