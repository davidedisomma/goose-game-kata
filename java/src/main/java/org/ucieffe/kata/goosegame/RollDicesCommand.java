package org.ucieffe.kata.goosegame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RollDicesCommand implements GooseGameCommand {

    private final Board board;
    private final OutputEventListener listener;

    public RollDicesCommand(Board board, OutputEventListener listener) {
        this.board = board;
        this.listener = listener;
    }

    @Override
    public void handle(String commandText) {
        if(!isTriggered(commandText))
            return;
        RollDices rollDices = extractMoveFrom(commandText);
        Move move = board.movePlayer(rollDices);
        GooseGameEvent event = switch (move) {
            case BounceBackMove m -> new BounceBackEvent(m.getPlayer().getName(),
                    m.getRollDices().getFirstDice(),
                    m.getRollDices().getSecondDice(),
                    m.getLastPosition().getName(),
                    m.getCurrentPosition().getName());
            case WinningMove m -> new WinningEvent(m.getPlayer().getName(),
                    m.getRollDices().getFirstDice(),
                    m.getRollDices().getSecondDice(),
                    m.getLastPosition().getName(),
                    m.getCurrentPosition().getName());
            case Move m -> new MoveEvent(m.getPlayer().getName(),
                    m.getRollDices().getFirstDice(),
                    m.getRollDices().getSecondDice(),
                    m.getLastPosition().getName(),
                    m.getCurrentPosition().getName());
        };
        listener.receive(event);
    }

    @Override
    public boolean isTriggered(String commandText) {
        Pattern pattern = Pattern.compile("^move (\\w+) ([1-6]), ([1-6])");
        Matcher matcher = pattern.matcher(commandText);
        return matcher.matches();
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