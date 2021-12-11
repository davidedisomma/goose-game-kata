package org.ucieffe.kata.goosegame;

public class InvalidCommand implements GooseGameCommand {

    public InvalidCommand() {
    }

    @Override
    public GooseGameEvent handle(String commandText) {
        return new InvalidCommandEvent();
    }

    @Override
    public boolean isTriggered(String commandText) {
        return true;
    }
}
