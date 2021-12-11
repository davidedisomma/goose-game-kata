package org.ucieffe.kata.goosegame;

public class InvalidCommand implements GooseGameCommand {
    private final OutputEventListener listener;

    public InvalidCommand(OutputEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void handle(String commandText) {
        listener.receive(new InvalidCommandEvent());
    }

    @Override
    public boolean isTriggered(String commandText) {
        return true;
    }
}
