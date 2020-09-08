package org.ucieffe.kata.goosegame;

public class Move {
    private Player player;
    private RollDices rollDices;
    private Box lastPosition;

    public Move(Player player, RollDices rollDices, Box lastPosition) {
        this.player = player;
        this.rollDices = rollDices;
        this.lastPosition = lastPosition;
    }

    public Player getPlayer() {
        return player;
    }

    public RollDices getRollDices() {
        return rollDices;
    }

    public Box getLastPosition() {
        return lastPosition;
    }

    public Box getCurrentPosition() {
        return player.getCurrentPosition();
    }
}
