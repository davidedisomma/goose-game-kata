package org.ucieffe.kata.goosegame;

public class OutputStreamEventListener implements OutputEventListener {

    private final OutputChannel out;

    public OutputStreamEventListener(OutputChannel out) {
        this.out = out;
    }

    @Override
    public void receive(GooseGameEvent event) {
        String text = switch (event) {
            case AddPlayerEvent e -> "players: " + concatenateAllPlayerNames(e);
            case PlayerAlreadyPresentEvent e -> e.playerName + ": already existing player";
            case BounceBackEvent e -> bounceBackMessage(e);
            case WinningEvent e -> winningMessage(e);
            case MoveEvent e -> moveMessage(e);
            case StartEvent e -> startMessage(e);
            case InvalidCommandEvent e -> "No command recognized";
        };

        out.write(text);
    }

    private String startMessage(StartEvent e) {
        return  String.format(
                "%s rolls %d, %d. %s moves from %s to %s",
                e.playerName, e.firstDice, e.secondDice,
                e.playerName, "Start",
                e.currentPosition
        );
    }

    private String moveMessage(MoveEvent e) {
        return  String.format(
                "%s rolls %d, %d. %s moves from %s to %s",
                e.playerName, e.firstDice, e.secondDice,
                e.playerName, e.lastPosition,
                e.currentPosition
        );
    }

    private String winningMessage(WinningEvent e) {
        return  String.format(
                "%s rolls %d, %d. %s moves from %s to %s. %s Wins!!",
                e.playerName, e.firstDice, e.secondDice,
                e.playerName, e.lastPosition,
                e.currentPosition, e.playerName
        );
    }

    private String bounceBackMessage(BounceBackEvent e) {
        return  String.format(
                "%s rolls %d, %d. %s moves from %s to 63. %s bounces! %s returns to %s",
                e.playerName, e.firstDice, e.secondDice,
                e.playerName, e.lastPosition, e.playerName,
                e.playerName, e.currentPosition
        );

    }


    private String concatenateAllPlayerNames(AddPlayerEvent addPlayer) {
        return String.join(", ", addPlayer.playerNames);
    }

}
