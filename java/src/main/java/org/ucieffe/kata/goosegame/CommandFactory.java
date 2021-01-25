package org.ucieffe.kata.goosegame;

public class CommandFactory {

    private Board board;
    private OutputChannel outputChannel;

    public CommandFactory(Board board, OutputChannel outputChannel) {
        this.board = board;
        this.outputChannel = outputChannel;
    }

    public InvalidCommand invalidCommand() {
        return new InvalidCommand(outputChannel);
    }

    public RollDicesCommand rollDicesCommand(RollDices rollDices) {
        return new RollDicesCommand(board, outputChannel, rollDices);
    }

    public GooseGameCommand addPlayerCommand(Player player) {
        return new AddPlayerCommand(board, outputChannel, player);
    }
}
