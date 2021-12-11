package org.ucieffe.kata.goosegame;

import java.util.List;
import java.util.stream.Collectors;

public class AddPlayerCommand implements GooseGameCommand {

    private static final String ADD_PLAYER_COMMAND_PREFIX = "add player ";

    private final Board board;
    private final OutputEventListener listener;

    public AddPlayerCommand(Board board, OutputEventListener listener) {
        this.board = board;
        this.listener = listener;
    }

    @Override
    public void handle(String commandText) {
        if(!isTriggered(commandText))
            return;
        String playerName = extractPlayerFrom(commandText);
        if (board.isAnExistentPlayer(playerName)) {
            listener.receive(new PlayerAlreadyPresentEvent(playerName));
        } else {
            board.addPlayer(new Player(playerName, new StartBox()));
            listener.receive(new AddPlayerEvent(fetchAllPlayerNames()));
        }
    }

    private String extractPlayerFrom(String commandText) {
        return commandText.substring(ADD_PLAYER_COMMAND_PREFIX.length());
    }

    @Override
    public boolean isTriggered(String commandText) {
        return commandText.startsWith(ADD_PLAYER_COMMAND_PREFIX);
    }

    private List<String> fetchAllPlayerNames() {
        return board.getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
