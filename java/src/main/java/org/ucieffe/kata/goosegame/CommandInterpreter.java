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
            Player player = extractPlayerFrom(command);
            return commandFactory.addPlayerCommand(player);
        }
        if(isMoveCommand(command)) {
            RollDices rollDices = extractMoveFrom(command);
            return commandFactory.rollDicesCommand(rollDices);
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

    private Player extractPlayerFrom(String command) {
        return new Player(command.substring(ADD_PLAYER_COMMAND_PREFIX.length()), new StartBox());
    }

    private RollDices extractMoveFrom(String command) {
        Pattern pattern = Pattern.compile("^move (\\w+) ([1-6]), ([1-6])");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            String player = matcher.group(1);
            String firstDice = matcher.group(2);
            String secondDice = matcher.group(3);
            return new RollDices(player, Integer.parseInt(firstDice), Integer.parseInt(secondDice));
        } else {
            throw new IllegalArgumentException(command);
        }
    }
}
