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

    public RollDicesCommand rollDicesCommand() {
        return new RollDicesCommand(board, listener);
    }

    public GooseGameCommand addPlayerCommand() {
        return new AddPlayerCommand(board, listener);
    }
}
