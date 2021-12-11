package org.ucieffe.kata.goosegame;

import java.util.List;
import java.util.stream.Collectors;

public class AddPlayerCommand implements GooseGameCommand {

    private static final String ADD_PLAYER_COMMAND_PREFIX = "add player ";

    private final Board board;
    private final GooseGameCommand nextCommand;

    public AddPlayerCommand(Board board, GooseGameCommand nextCommand) {
        this.board = board;
        this.nextCommand = nextCommand;
    }

    @Override
    public GooseGameEvent handle(String commandText) {
        if(!isTriggered(commandText))
            return nextCommand.handle(commandText);

        String playerName = extractPlayerFrom(commandText);
        if (board.isAnExistentPlayer(playerName)) {
            return new PlayerAlreadyPresentEvent(playerName);
        } else {
            board.addPlayer(new Player(playerName, new StartBox()));
            return new AddPlayerEvent(fetchAllPlayerNames());
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
