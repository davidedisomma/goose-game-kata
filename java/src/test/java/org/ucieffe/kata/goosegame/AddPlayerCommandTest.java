package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddPlayerCommandTest {

    private AddPlayerCommand addPlayerCommand;

    @BeforeEach
    void setUp() {
        addPlayerCommand = new AddPlayerCommand(null, null);
    }

    @Test
    void commandIsTriggeredWhenAddPlayerText() {
        assertTrue(addPlayerCommand.isTriggered("add player Pippo"));
        assertTrue(addPlayerCommand.isTriggered("add player Charlie Brown"));
    }

    @Test
    void commandIsIdleWhenOtherText() {
        assertFalse(addPlayerCommand.isTriggered("add playerz Pippo"));
        assertFalse(addPlayerCommand.isTriggered("add fhjkfh Pippo"));
        assertFalse(addPlayerCommand.isTriggered("dgjhja player Pippo"));
    }
}