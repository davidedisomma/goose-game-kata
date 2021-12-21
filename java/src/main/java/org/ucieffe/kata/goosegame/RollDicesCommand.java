package org.ucieffe.kata.goosegame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RollDicesCommand implements GooseGameCommand {

    private final Board board;
    private final GooseGameCommand nextCommand;

    public RollDicesCommand(Board board, GooseGameCommand nextCommand) {
        this.board = board;
        this.nextCommand = nextCommand;
    }

    @Override
    public GooseGameEvent handle(String commandText) {
        if(isTriggeredBy(commandText)) {
            Move playerMove = extractMoveFrom(commandText);
            return board.movePlayer(playerMove);
        }

        return nextCommand.handle(commandText);
    }

    private boolean isTriggeredBy(String commandText) {
        Pattern pattern = Pattern.compile("^move (\\w+) ([1-6]), ([1-6])");
        Matcher matcher = pattern.matcher(commandText);
        return matcher.matches();
    }

    private Move extractMoveFrom(String command) {
        Pattern pattern = Pattern.compile("^move (\\w+) ([1-6]), ([1-6])");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            String playerName = matcher.group(1);
            String firstDice = matcher.group(2);
            String secondDice = matcher.group(3);
            final RollDices rollDices = new RollDices(Integer.parseInt(firstDice), Integer.parseInt(secondDice));
            return new Move(playerName, rollDices);
        } else {
            throw new IllegalArgumentException(command);
        }
    }
}