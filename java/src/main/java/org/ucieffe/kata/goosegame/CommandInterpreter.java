package org.ucieffe.kata.goosegame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandInterpreter {

    private static final String ADD_PLAYER_COMMAND_PREFIX = "add player ";

    private CommandFactory commandFactory;

    public CommandInterpreter(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public GooseGameCommand run(String command) {
        if(isAddPlayerCommand(command)) {
            return commandFactory.addPlayerCommand();
        }
        if(isMoveCommand(command)) {
            return commandFactory.rollDicesCommand();
        }
        return commandFactory.invalidCommand();
    }

    private boolean isMoveCommand(String command) {
        Pattern pattern = Pattern.compile("^move (\\w+) ([1-6]), ([1-6])");
        Matcher matcher = pattern.matcher(command);
        return matcher.matches();
    }

    private boolean isAddPlayerCommand(String command) {
        return command.startsWith(ADD_PLAYER_COMMAND_PREFIX);
    }

}
