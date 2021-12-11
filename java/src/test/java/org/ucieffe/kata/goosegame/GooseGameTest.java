package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GooseGameTest {


    private OutputChannel outputChannel;
    private GooseGame gooseGame;
    private CommandInterpreter commandInterpreter;
    private OutputStreamEventListener outputEventListener;

    @BeforeEach
    public void setUp() {
        outputChannel = mock(OutputChannel.class);
        outputEventListener = new OutputStreamEventListener(outputChannel);
        commandInterpreter = new CommandInterpreter(new CommandFactory(new Board()));
        gooseGame = new GooseGame(commandInterpreter, outputEventListener);
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

    @Test
    public void movePlayersFromStart() {
        gooseGame.nextCommand("add player Pippo");
        gooseGame.nextCommand("add player Pluto");

        gooseGame.nextCommand("move Pippo 4, 2");
        verify(outputChannel).write("Pippo rolls 4, 2. Pippo moves from Start to 6");
        gooseGame.nextCommand("move Pluto 2, 2");
        verify(outputChannel).write("Pluto rolls 2, 2. Pluto moves from Start to 4");
        gooseGame.nextCommand("move Pippo 2, 3");
        verify(outputChannel).write("Pippo rolls 2, 3. Pippo moves from 6 to 11");
    }

    @Test
    public void playerWinWhenReachWinningPosition() {
        Board board = new Board();
        board.addPlayer(new Player("Pippo", new Box(60)));
        gooseGame = new GooseGame(new CommandInterpreter(new CommandFactory(board)), outputEventListener);

        gooseGame.nextCommand("move Pippo 1, 2");
        verify(outputChannel).write("Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!");
    }

    @Test
    public void playerBounceWhenOvercomeWinningPosition() {
        Board board = new Board();
        board.addPlayer(new Player("Pippo", new Box(60)));
        gooseGame = new GooseGame(new CommandInterpreter(new CommandFactory(board)), outputEventListener);

        gooseGame.nextCommand("move Pippo 3, 2");
        verify(outputChannel).write("Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61");
    }
}
