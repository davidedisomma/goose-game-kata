package org.ucieffe.kata.goosegame;

public class InvalidCommand implements GooseGameCommand {

    public InvalidCommand() {
    }

    @Override
    public GooseGameEvent handle(String commandText) {
        return new InvalidCommandEvent();
    }
}
