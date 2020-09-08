/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.ucieffe.kata.goosegame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GooseGame {

    public static final String ADD_PLAYER_COMMAND_PREFIX = "add player ";

    private final OutputChannel outputChannel;
    private List<String> players;

    public GooseGame(OutputChannel out) {
        outputChannel = out;
        players = new ArrayList<>();
    }

    public void nextCommand(String command) {
        String result = "No command recognized";
        if(command.startsWith(ADD_PLAYER_COMMAND_PREFIX)) {
            String player = extractPlayerFrom(command);
            if(isAnExistentPlayer(player)) {
                result = player + ": already existing player";
            } else {
                players.add(player);
                result = returnAddPlayerResult();
            }
        }
        outputChannel.write(result);
    }

    private boolean isAnExistentPlayer(String player) {
        return players.contains(player);
    }

    private String extractPlayerFrom(String command) {
        return command.substring(ADD_PLAYER_COMMAND_PREFIX.length());
    }

    private String returnAddPlayerResult() {
        return "players: " + concatenateAllPlayers();
    }

    private String concatenateAllPlayers() {
        return players.stream().collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        InputChannel inputChannel = new SystemInputChannel(new BufferedReader(new InputStreamReader(System.in)));
        OutputChannel outputChannel = new SystemOutputChannel(System.out);
        GooseGame gooseGame = new GooseGame(outputChannel);
        while(true) {
            String command = inputChannel.read();
            gooseGame.nextCommand(command);
        }
    }
}
