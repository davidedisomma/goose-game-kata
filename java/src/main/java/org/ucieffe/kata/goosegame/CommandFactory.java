package org.ucieffe.kata.goosegame;

public class CommandFactory {

    private final Board board;
    private final RollDiceThrower rollDiceThrower;

    public CommandFactory(Board board, RollDiceThrower rollDiceThrower) {
        this.board = board;
        this.rollDiceThrower = rollDiceThrower;
    }
    public GooseGameCommand chainOfCommands() {
        GameRollDicesCommand gameRollDicesCommand = new GameRollDicesCommand(board, new InvalidCommand(), rollDiceThrower);
        RollDicesCommand rollDicesCommand = new RollDicesCommand(board, gameRollDicesCommand);
        return new AddPlayerCommand(board, rollDicesCommand);
    }
}
