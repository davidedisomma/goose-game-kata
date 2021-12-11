package org.ucieffe.kata.goosegame;

public class CommandFactory {

    private Board board;

    public CommandFactory(Board board) {
        this.board = board;
    }
    public GooseGameCommand chainOfCommands() {
        RollDicesCommand rollDicesCommand = new RollDicesCommand(board, new InvalidCommand());
        return new AddPlayerCommand(board, rollDicesCommand);
    }
}
