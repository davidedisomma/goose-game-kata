package org.ucieffe.kata.goosegame;

public class CommandFactory {

    private Board board;
    private OutputEventListener listener;

    public CommandFactory(Board board, OutputEventListener listener) {
        this.board = board;
        this.listener = listener;
    }

    public InvalidCommand invalidCommand() {
        return new InvalidCommand(listener);
    }

    public RollDicesCommand rollDicesCommand(RollDices rollDices) {
        return new RollDicesCommand(board, listener, rollDices);
    }

    public GooseGameCommand addPlayerCommand(String playerName) {
        return new AddPlayerCommand(board, listener, playerName);
    }
}
