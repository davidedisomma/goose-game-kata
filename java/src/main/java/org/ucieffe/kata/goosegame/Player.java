package org.ucieffe.kata.goosegame;

import java.util.Objects;

public class Player {

    private String name;
    private Integer lastPosition;
    private Integer currentPosition;

    public Player(String name) {
        this.name = name;
        lastPosition = 0;
        currentPosition = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public Integer getLastPosition() {
        return lastPosition;
    }

    public void movePosition(Move move) {
        lastPosition = currentPosition;
        currentPosition += move.calculateProgress();
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
