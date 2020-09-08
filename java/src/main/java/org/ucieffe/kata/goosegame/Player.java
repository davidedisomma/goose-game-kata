package org.ucieffe.kata.goosegame;

import java.util.Objects;

public class Player {

    private String name;
    private Box currentPosition;

    public Player(String name) {
        this.name = name;
        currentPosition = new StartBox();
    }

    public String getName() {
        return name;
    }

    public Box getCurrentPosition() {
        return currentPosition;
    }

    public void movePosition(RollDices rollDices) {
        currentPosition = currentPosition.movePosition(rollDices);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
