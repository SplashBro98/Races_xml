package com.epam.races.controller;

import com.epam.races.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public enum RequestHelper {
    INSTANCE;

    private HashMap<String, Command> commands = new HashMap<>();

    public void addCommand(String type, Command command){
        commands.put(type, command);
    }

    RequestHelper() {
        commands.put("parse",new ParseCommand());
        commands.put("return", new ReturnCommand());
        commands.put("russian", new RussianCommand());
        commands.put("english", new EnglishCommand());
    }

    public Command getCommand(HttpServletRequest req){
        System.out.println(req.getParameter("command"));
        Command command = commands.get(req.getParameter("command"));
        if(command == null){
            command = new NoCommand();
        }
        return command;
    }
}
