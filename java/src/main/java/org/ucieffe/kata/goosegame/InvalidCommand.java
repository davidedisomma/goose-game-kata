package org.ucieffe.kata.goosegame;

public class InvalidCommand implements GooseGameCommand {
    private final OutputChannel outputChannel;

    public InvalidCommand(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    @Override
    public void execute() {
        outputChannel.write("No command recognized");
    }
}
