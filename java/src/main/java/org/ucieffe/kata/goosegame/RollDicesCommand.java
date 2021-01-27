package org.ucieffe.kata.goosegame;

public class RollDicesCommand implements GooseGameCommand {

    private final Board board;
    private final OutputChannel outputChannel;
    private final RollDices rollDices;

    public RollDicesCommand(Board board, OutputChannel outputChannel, RollDices rollDices) {
        this.board = board;
        this.outputChannel = outputChannel;
        this.rollDices = rollDices;
    }

    @Override
    public void execute() {
        Move move = board.movePlayer(rollDices);
        String message = String.format(
                "%s rolls %d, %d. %s moves from %s to %s",
                rollDices.getPlayerName(), rollDices.getFirstDice(), rollDices.getSecondDice(),
                rollDices.getPlayerName(), move.getLastPosition().getName(), move.getCurrentPosition().getName()
        );
        if(move.getCurrentPosition() instanceof WinningBox) {
            message += ". " + move.getPlayer().getName() + " Wins!!";
        }
        outputChannel.write(message);
    }
}
