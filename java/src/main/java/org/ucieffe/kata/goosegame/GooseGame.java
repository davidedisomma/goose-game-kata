/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.ucieffe.kata.goosegame;

import java.io.*;

public class GooseGame {

    private final CommandInterpreter commandInterpreter;

    public GooseGame(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public void nextCommand(String stringCommand) {
        GooseGameCommand command = commandInterpreter.run(stringCommand);
        command.execute();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        InputChannel inputChannel = new SystemInputChannel(new BufferedReader(new InputStreamReader(System.in)));
        OutputChannel outputChannel = new SystemOutputChannel(System.out);
        OutputStreamEventListener listener = new OutputStreamEventListener(outputChannel);
        Board board = new Board();
        CommandInterpreter commandInterpreter = new CommandInterpreter(new CommandFactory(board, listener));
        GooseGame gooseGame = new GooseGame(commandInterpreter);
        do {
            String command = inputChannel.read();
            gooseGame.nextCommand(command);
        } while (true);
    }
}
