package org.ucieffe.kata.goosegame;

public class StartMove extends Move {

    public StartMove(Player player, RollDices rollDices) {
        super(player, rollDices, new StartBox());
    }
}
