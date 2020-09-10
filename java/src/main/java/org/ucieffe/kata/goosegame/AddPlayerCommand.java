package org.ucieffe.kata.goosegame;

import java.util.stream.Collectors;

public class AddPlayerCommand implements GooseGameCommand {

    private final Board board;
    private final OutputChannel outputChannel;
    private final Player player;

    public AddPlayerCommand(Board board, OutputChannel outputChannel, Player player) {
        this.outputChannel = outputChannel;
        this.board = board;
        this.player = player;
    }

    @Override
    public void execute() {
        if (board.isAnExistentPlayer(player)) {
            outputChannel.write(player.getName() + ": already existing player");
        } else {
            board.addPlayer(player);
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
