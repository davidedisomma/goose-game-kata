package org.ucieffe.kata.goosegame;

public class RollDicesCommand implements GooseGameCommand {

    private final Board board;
    private final OutputEventListener listener;
    private final RollDices rollDices;

    public RollDicesCommand(Board board, OutputEventListener listener, RollDices rollDices) {
        this.board = board;
        this.listener = listener;
        this.rollDices = rollDices;
    }

    @Override
    public void execute() {
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
}