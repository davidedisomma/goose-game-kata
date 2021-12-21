package org.ucieffe.kata.goosegame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameRollDicesCommand implements GooseGameCommand{

    private final Board board;
    private final GooseGameCommand nextCommand;
    private final RollDiceThrower rollDicesThrower;

    public GameRollDicesCommand(Board board, GooseGameCommand nextCommand, RollDiceThrower rollDicesThrower) {
        this.board = board;
        this.nextCommand = nextCommand;
        this.rollDicesThrower = rollDicesThrower;
    }

    @Override
    public GooseGameEvent handle(String commandText) {
        if(isTriggeredBy(commandText)) {
            String playerName = extractPlayerNameFrom(commandText);
            RollDices rollDices = rollDicesThrower.throwDices(playerName);
            Move playerMove = new Move(playerName, rollDices);
            return board.movePlayer(playerMove);
        }
        return nextCommand.handle(commandText);
    }

    private boolean isTriggeredBy(String commandText) {
        return createGameRollDicesCommandMatcherFrom(commandText).matches();
    }

    private Matcher createGameRollDicesCommandMatcherFrom(String commandText) {
        Pattern pattern = Pattern.compile("^move (\\w+)");
        return pattern.matcher(commandText);
    }

    private String extractPlayerNameFrom(String command) {
        Matcher matcher = createGameRollDicesCommandMatcherFrom(command);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException(command);
        }
    }

}
