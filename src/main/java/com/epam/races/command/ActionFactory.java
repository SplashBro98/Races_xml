package com.epam.races.command;

import com.epam.races.command.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;

public enum ActionFactory {
    INSTANCE;

    public static final String PARAM_COMMAND = "command";
    private static Logger logger = LogManager.getLogger(ActionFactory.class);

    private EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);


    ActionFactory() {
        commands.put(CommandType.PARSE,new ParseCommand());
        commands.put(CommandType.NO, new NoCommand());
        commands.put(CommandType.RUSSIAN, new RussianCommand());
        commands.put(CommandType.ENGLISH, new EnglishCommand());
        commands.put(CommandType.TO_START, new ToStartCommand());
        commands.put(CommandType.LOGIN, new LoginCommand());
        commands.put(CommandType.TO_LOGIN, new ToLoginCommand());
    }

    public Command getCommand(HttpServletRequest req){
        String commandType = req.getParameter(PARAM_COMMAND);
        logger.log(Level.INFO, "Command: " + commandType);

        Command command = commands.get(CommandType.valueOf(commandType.replace(' ','_').toUpperCase()));
        if(command == null){
            command = new NoCommand();
        }
        return command;
    }
}
