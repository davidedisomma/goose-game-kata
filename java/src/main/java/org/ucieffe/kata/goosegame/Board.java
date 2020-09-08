package org.ucieffe.kata.goosegame;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

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
        player.movePosition(rollDices);
        return new Move(player, rollDices, lastPosition);
    }

    public List<Player> getAllPlayers() {
        return players.values().stream().collect(Collectors.toList());
    }
}
