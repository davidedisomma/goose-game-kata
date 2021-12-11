package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RollDicesCommandTest {

    @Test
    void commandIsTriggeredWhenReceiveValidMoveCommand() {
        RollDicesCommand command = new RollDicesCommand(null, null);

        assertTrue(command.isTriggered("move Pippo 4, 2"));
        assertTrue(command.isTriggered("move Pluto 1, 3"));
    }

    @Test
    void commandIsSkippedWhenReceiveInvalidMoveCommand() {
        RollDicesCommand command = new RollDicesCommand(null, null);

        assertFalse(command.isTriggered("fjdhsdhjsfjf"));
        assertFalse(command.isTriggered("move    Pippo 4,   2"));
        assertFalse(command.isTriggered("add player Pippo"));
    }
}