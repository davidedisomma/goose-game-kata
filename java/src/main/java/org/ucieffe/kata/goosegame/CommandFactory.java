package org.ucieffe.kata.goosegame;

public class CommandFactory {

    private Board board;

    public CommandFactory(Board board) {
        this.board = board;
    }

    public InvalidCommand invalidCommand() {
        return new InvalidCommand();
    }

    public RollDicesCommand rollDicesCommand() {
        return new RollDicesCommand(board);
    }

    public GooseGameCommand addPlayerCommand() {
        return new AddPlayerCommand(board);
    }
}
