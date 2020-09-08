package org.ucieffe.kata.goosegame;

public class RollDices {

    private final String playerName;
    private final Integer firstDice;
    private final Integer secondDice;

    public RollDices(String playerName, Integer firstDice, Integer secondDice) {

        this.playerName = playerName;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getFirstDice() {
        return firstDice;
    }

    public Integer getSecondDice() {
        return secondDice;
    }

    public Integer totalDices() {
        return firstDice + secondDice;
    }
}
