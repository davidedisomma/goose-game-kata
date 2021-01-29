package org.ucieffe.kata.goosegame;

import java.util.stream.Collectors;

public class AddPlayerCommand implements GooseGameCommand {

    private final Board board;
    private final OutputChannel outputChannel;
    private final String playerName;

    public AddPlayerCommand(Board board, OutputChannel outputChannel, String playerName) {
        this.outputChannel = outputChannel;
        this.board = board;
        this.playerName = playerName;
    }

    @Override
    public void execute() {
        if (board.isAnExistentPlayer(playerName)) {
            outputChannel.write(playerName + ": already existing player");
        } else {
            board.addPlayer(playerName);
            outputChannel.write(returnAddPlayerResult());
        }
    }

    private String returnAddPlayerResult() {
        return "players: " + concatenateAllPlayers();
    }

    private String concatenateAllPlayers() {
        return board.getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }
}
