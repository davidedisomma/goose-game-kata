package org.ucieffe.kata.goosegame;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandInterpreterTest {

    private CommandInterpreter interpreter;
    private CommandFactory commandFactory;

    @BeforeEach
    public void setUp() {
        commandFactory = new CommandFactory(new Board());
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