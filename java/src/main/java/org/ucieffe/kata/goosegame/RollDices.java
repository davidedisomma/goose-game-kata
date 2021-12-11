package org.ucieffe.kata.goosegame;

public record RollDices(String playerName, Integer firstDice, Integer secondDice) {

    public Integer totalDices() {
        return firstDice + secondDice;
    }
}
