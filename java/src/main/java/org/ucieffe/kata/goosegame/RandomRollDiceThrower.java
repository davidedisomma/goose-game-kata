package org.ucieffe.kata.goosegame;

import java.util.concurrent.ThreadLocalRandom;

public class RandomRollDiceThrower implements RollDiceThrower {
    @Override
    public RollDices throwDices(String player) {
        return new RollDices(player, randomNumberFromOneToSix(), randomNumberFromOneToSix());
    }

    private int randomNumberFromOneToSix() {
        return ThreadLocalRandom.current().nextInt(1, 6);
    }
}
