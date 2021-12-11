package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.mock;

class InvalidCommandTest {

    private OutputEventListener listener;

    @BeforeEach
    void setUp() {
        listener = mock(OutputEventListener.class);
    }

    @Test
    void raiseAlwaysAnInvalidEvent() {
        InvalidCommand command = new InvalidCommand();

        assertThat(command.handle("any invalid command"), instanceOf(InvalidCommandEvent.class));
        assertThat(command.handle("add player pippo"), instanceOf(InvalidCommandEvent.class));
        assertThat(command.handle("move Pippo 4, 2"), instanceOf(InvalidCommandEvent.class));
    }
}