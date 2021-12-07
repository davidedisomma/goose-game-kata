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

    public boolean isAnExistentPlayer(String playerName) {
        return players.containsKey(playerName);
    }

    public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

    public Move movePlayer(RollDices rollDices) {
        Player player = players.get(rollDices.getPlayerName());
        Box lastPosition = player.getCurrentPosition();
        Integer nextPosition = lastPosition.getPosition() + rollDices.totalDices();
        if(isLandedOnWinningBox(nextPosition)) {
            player.movePosition(new WinningBox());
            return new WinningMove(player, rollDices, lastPosition);
        } else if(hasGoneBeyondWinningBox(nextPosition)) {
            player.movePosition(new Box(calculatePositionAfterBounceBack(nextPosition)));
            return new BounceBackMove(player, rollDices, lastPosition);
        } else if (isStartedFromStart(lastPosition)) {
            player.movePosition(new Box(nextPosition));
            return new StartMove(player, rollDices);
        }
        else {
            player.movePosition(new Box(nextPosition));
        }
        return new Move(player, rollDices, lastPosition);
    }

    private boolean isStartedFromStart(Box lastPosition) {
        return lastPosition.getPosition() == 0;
    }

    private int calculatePositionAfterBounceBack(Integer nextPosition) {
        return WINNING_POSITION - (nextPosition - WINNING_POSITION);
    }

    private boolean hasGoneBeyondWinningBox(Integer nextPosition) {
        return nextPosition > WINNING_POSITION;
    }

    private boolean isLandedOnWinningBox(Integer nextPosition) {
        return nextPosition == WINNING_POSITION;
    }

    public List<Player> getAllPlayers() {
        return players.values().stream().collect(Collectors.toList());
    }
}
