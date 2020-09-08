/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.ucieffe.kata.goosegame;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GooseGame {

    public static final String ADD_PLAYER_COMMAND_PREFIX = "add player ";

    private final OutputChannel outputChannel;
    private final Board board;

    public GooseGame(Board board, OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
        this.board = board;
    }

    public void nextCommand(String command) {
        if (isAddPlayerCommand(command)) {
            Player player = extractPlayerFrom(command);
            if (board.isAnExistentPlayer(player)) {
                outputChannel.write(player.getName() + ": already existing player");
            } else {
                board.addPlayer(player);
                outputChannel.write(returnAddPlayerResult());
            }
            return;
        }
        if (isMoveCommand(command)) {
            RollDices rollDices = extractMoveFrom(command);
            Move move = board.movePlayer(rollDices);
            outputChannel.write(String.format(
                    "%s rolls %d, %d. %s moves from %s to %s",
                    rollDices.getPlayerName(), rollDices.getFirstDice(), rollDices.getSecondDice(),
                    rollDices.getPlayerName(), move.getLastPosition().getName(), move.getCurrentPosition().getName()
            ));
            return;
        }
        outputChannel.write("No command recognized");
    }

    private boolean isAddPlayerCommand(String command) {
        return command.startsWith(ADD_PLAYER_COMMAND_PREFIX);
    }

    private RollDices extractMoveFrom(String command) {
        Pattern pattern = Pattern.compile("^move (\\w+) ([1-6]), ([1-6])");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            String player = matcher.group(1);
            String firstDice = matcher.group(2);
            String secondDice = matcher.group(3);
            return new RollDices(player, Integer.parseInt(firstDice), Integer.parseInt(secondDice));
        } else {
            throw new IllegalArgumentException(command);
        }
    }

    private boolean isMoveCommand(String command) {
        Pattern pattern = Pattern.compile("^move (\\w+) ([1-6]), ([1-6])");
        Matcher matcher = pattern.matcher(command);
        return matcher.matches();
    }

    private Player extractPlayerFrom(String command) {
        return new Player(command.substring(ADD_PLAYER_COMMAND_PREFIX.length()));
    }

    private String returnAddPlayerResult() {
        return "players: " + concatenateAllPlayers();
    }

    private String concatenateAllPlayers() {
        return board.getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        InputChannel inputChannel = new SystemInputChannel(new BufferedReader(new InputStreamReader(System.in)));
        OutputChannel outputChannel = new SystemOutputChannel(System.out);
        GooseGame gooseGame = new GooseGame(new Board(), outputChannel);
        while (true) {
            String command = inputChannel.read();
            gooseGame.nextCommand(command);
        }
    }
}
