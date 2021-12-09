package org.ucieffe.kata.goosegame;

import java.util.List;
import java.util.stream.Collectors;

public class AddPlayerCommand implements GooseGameCommand {

    private final Board board;
    private final OutputEventListener listener;
    private final String playerName;

    public AddPlayerCommand(Board board, OutputEventListener listener, String playerName) {
        this.board = board;
        this.listener = listener;
        this.playerName = playerName;
    }

    @Override
    public void execute() {
        if (board.isAnExistentPlayer(playerName)) {
            listener.receive(new PlayerAlreadyPresentEvent(playerName));
        } else {
            board.addPlayer(new Player(playerName, new StartBox()));
            listener.receive(new AddPlayerEvent(fetchAllPlayerNames()));
        }
    }

    private List<String> fetchAllPlayerNames() {
        return board.getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
