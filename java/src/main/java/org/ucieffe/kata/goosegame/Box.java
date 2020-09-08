package org.ucieffe.kata.goosegame;

public class Box {
    private Integer position;

    public Box(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public String getName() {
        return position.toString();
    }

    public Box movePosition(RollDices rollDices) {
        return new Box(position + rollDices.totalDices());
    }
}
