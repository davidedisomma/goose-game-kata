package org.ucieffe.kata.goosegame;

public class RollDiceCommand implements GooseGameCommand {

    private final Board board;
    private final OutputChannel outputChannel;
    private final RollDices rollDices;

    public RollDiceCommand(Board board, OutputChannel outputChannel, RollDices rollDices) {
        this.board = board;
        this.outputChannel = outputChannel;
        this.rollDices = rollDices;
    }

    @Override
    public void execute() {
        Move move = board.movePlayer(rollDices);
        outputChannel.write(String.format(
                "%s rolls %d, %d. %s moves from %s to %s",
                rollDices.getPlayerName(), rollDices.getFirstDice(), rollDices.getSecondDice(),
                rollDices.getPlayerName(), move.getLastPosition().getName(), move.getCurrentPosition().getName()
        ));
    }
}
