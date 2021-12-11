package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCommandTest {

    @Test
    void isAlwaysTriggered() {
        InvalidCommand command = new InvalidCommand(null);

        assertTrue(command.isTriggered("any invalid command"));
        assertTrue(command.isTriggered("add player pippo"));
        assertTrue(command.isTriggered("move Pippo 4, 2"));
    }
}