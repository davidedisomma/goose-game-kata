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

    public Player movePlayer(Move move) {
        Player player = players.get(move.getPlayerName());
        player.movePosition(move);
        return player;
    }

    public List<Player> getAllPlayers() {
        return players.values().stream().collect(Collectors.toList());
    }
}
