package org.ucieffe.kata.goosegame;

import java.util.List;
import java.util.stream.Collectors;

public class AddPlayerCommand implements GooseGameCommand {

    private static final String ADD_PLAYER_COMMAND_PREFIX = "add player ";

    private final Board board;

    public AddPlayerCommand(Board board) {
        this.board = board;
    }

    @Override
    public GooseGameEvent handle(String commandText) {
        if(!isTriggered(commandText))
            return null;
        String playerName = extractPlayerFrom(commandText);
        GooseGameEvent event;
        if (board.isAnExistentPlayer(playerName)) {
            event = new PlayerAlreadyPresentEvent(playerName);
        } else {
            board.addPlayer(new Player(playerName, new StartBox()));
            event = new AddPlayerEvent(fetchAllPlayerNames());
        }
        return event;
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
