package org.ucieffe.kata.goosegame;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GooseGameAT {

    private OutputChannel outputChannel;
    private GooseGame gooseGame;

    @Before
    public void setUp() throws Exception {
        outputChannel = mock(OutputChannel.class);
        gooseGame = new GooseGame(outputChannel);
    }

    @Test
    public void addPlayers() {
        gooseGame.nextCommand("add player Pippo");
        verify(outputChannel).write("players: Pippo");

        gooseGame.nextCommand("add player Pluto");
        verify(outputChannel).write("players: Pippo, Pluto");
    }

    @Test
    public void notAllowAddAnAlreadyExistentPlayer() {
        gooseGame.nextCommand("add player Pippo");
        gooseGame.nextCommand("add player Pippo");

        verify(outputChannel).write("Pippo: already existing player");
    }

    @Test
    public void commandNotRecognized() {
        gooseGame.nextCommand("any other command");

        verify(outputChannel).write("No command recognized");
    }
}
