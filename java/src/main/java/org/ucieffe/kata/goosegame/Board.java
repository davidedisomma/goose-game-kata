package org.ucieffe.kata.goosegame;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    public static final int WINNING_POSITION = 63;
    private final LinkedHashMap<String, Player> players;

    public Board() {
        players = new LinkedHashMap<>();
    }

    public boolean isAnExistentPlayer(Player player) {
        return players.containsKey(player.getName());
    }

    public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

    public Move movePlayer(RollDices rollDices) {
        Player player = players.get(rollDices.getPlayerName());
        Box lastPosition = player.getCurrentPosition();
        Integer nextPosition = lastPosition.getPosition() + rollDices.totalDices();
        if(nextPosition == WINNING_POSITION) {
            player.movePosition(new WinningBox());
            return new WinningMove(player, rollDices, lastPosition);
        } else if(nextPosition > WINNING_POSITION) {
            player.movePosition(new Box(WINNING_POSITION - (nextPosition - WINNING_POSITION)));
            return new BounceBackMove(player, rollDices, lastPosition);
        } else {
            player.movePosition(new Box(nextPosition));
        }
        return new Move(player, rollDices, lastPosition);
    }

    public List<Player> getAllPlayers() {
        return players.values().stream().collect(Collectors.toList());
    }
}
