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
        if(isTriggeredBy(commandText)) {
            String playerName = extractPlayerFrom(commandText);
            if (board.isAnExistentPlayer(playerName)) {
                return new PlayerAlreadyPresentEvent(playerName);
            }

            board.addPlayer(new Player(playerName, new StartBox()));
            return new AddPlayerEvent(fetchAllPlayerNames());
        }

        return nextCommand.handle(commandText);
    }

    private String extractPlayerFrom(String commandText) {
        return commandText.substring(ADD_PLAYER_COMMAND_PREFIX.length());
    }

    private boolean isTriggeredBy(String commandText) {
        return commandText.startsWith(ADD_PLAYER_COMMAND_PREFIX);
    }

    private List<String> fetchAllPlayerNames() {
        return board.getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
