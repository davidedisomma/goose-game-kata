package org.ucieffe.kata.goosegame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    public GooseGameEvent movePlayer(RollDices rollDices) {
        Player player = players.get(rollDices.playerName());
        Box lastPosition = player.getCurrentPosition();
        Integer nextPosition = lastPosition.getPosition() + rollDices.totalDices();
        if(isLandedOnWinningBox(nextPosition)) {
            player.movePosition(new WinningBox());
            return new WinningEvent(player.getName(), rollDices.firstDice(), rollDices.secondDice(), lastPosition.getPosition().toString(), player.getCurrentPosition().getPosition().toString());
        } else if(hasGoneBeyondWinningBox(nextPosition)) {
            player.movePosition(new Box(calculatePositionAfterBounceBack(nextPosition)));
            return new BounceBackEvent(player.getName(), rollDices.firstDice(), rollDices.secondDice(), lastPosition.getPosition().toString(), player.getCurrentPosition().getPosition().toString());
        } else if(isStartedFromStart(lastPosition)) {
            player.movePosition(new Box(nextPosition));
            return new StartEvent(player.getName(), rollDices.firstDice(), rollDices.secondDice(), player.getCurrentPosition().getPosition().toString());
        }
        player.movePosition(new Box(nextPosition));
        return new MoveEvent(player.getName(), rollDices.firstDice(), rollDices.secondDice(), lastPosition.getPosition().toString(), player.getCurrentPosition().getPosition().toString());
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
        return new ArrayList<>(players.values());
    }
}
