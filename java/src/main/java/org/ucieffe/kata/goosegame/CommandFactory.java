package org.ucieffe.kata.goosegame;

public class CommandFactory {

    private Board board;
    private OutputChannel outputChannel;
    private OutputEventListener listener;

    public CommandFactory(Board board, OutputChannel outputChannel, OutputEventListener listener) {
        this.board = board;
        this.outputChannel = outputChannel;
        this.listener = listener;
    }

    public InvalidCommand invalidCommand() {
        return new InvalidCommand(outputChannel);
    }

    public RollDicesCommand rollDicesCommand(RollDices rollDices) {
        return new RollDicesCommand(board, listener, rollDices);
    }

    public GooseGameCommand addPlayerCommand(String playerName) {
        return new AddPlayerCommand(board, listener, playerName);
    }
}
