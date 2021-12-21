package org.ucieffe.kata.goosegame;

public record RollDices(Integer firstDice, Integer secondDice) {

    public Integer totalDices() {
        return firstDice + secondDice;
    }
}
