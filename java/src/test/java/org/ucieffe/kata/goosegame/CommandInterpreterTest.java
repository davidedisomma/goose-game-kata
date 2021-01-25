package org.ucieffe.kata.goosegame;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CommandInterpreterTest {

    private CommandInterpreter interpreter;
    private CommandFactory commandFactory;

    @Before
    public void setUp() {
        commandFactory = new CommandFactory(new Board(), mock(OutputChannel.class));
        interpreter = new CommandInterpreter(commandFactory);
    }

    @Test
    public void returnAddPlayerCommand() {
        GooseGameCommand command = interpreter.run("add player Pippo");

        assertThat(command, instanceOf(AddPlayerCommand.class));
    }

    @Test
    public void returnRollDicesCommand() {
        GooseGameCommand command = interpreter.run("move Pippo 4, 2");

        assertThat(command, instanceOf(RollDicesCommand.class));
    }

    @Test
    public void returnInvalidCommand() {
        GooseGameCommand command = interpreter.run("inexistent command");

        assertThat(command, instanceOf(InvalidCommand.class));
    }
}