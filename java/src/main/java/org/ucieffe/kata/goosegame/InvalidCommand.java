package org.ucieffe.kata.goosegame;

public class InvalidCommand implements GooseGameCommand {
    private final OutputEventListener listener;

    public InvalidCommand(OutputEventListener listener) {
        this.listener = listener;
    }

    @Override
    public void execute() {
        listener.receive(new InvalidCommandEvent());
    }
}
