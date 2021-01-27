package org.ucieffe.kata.goosegame;

import java.util.Objects;

public class Player {

    private String name;
    private Box currentPosition;

    public Player(String name, Box currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }

    public Box getCurrentPosition() {
        return currentPosition;
    }

    public void movePosition(Box currentPosition) {
        this.currentPosition = currentPosition;
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
