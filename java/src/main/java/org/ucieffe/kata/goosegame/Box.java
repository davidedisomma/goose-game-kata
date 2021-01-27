package org.ucieffe.kata.goosegame;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(position, box.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Box{" +
                "position=" + position +
                '}';
    }
}
