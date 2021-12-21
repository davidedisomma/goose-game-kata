package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameRollDicesCommandTest {

    private Board board;
    private GooseGameCommand nextCommand;
    private GameRollDicesCommand command;
    private RandomRollDiceThrower rollDicesThrower;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        nextCommand = mock(GooseGameCommand.class);
        rollDicesThrower = mock(RandomRollDiceThrower.class);
        command = new GameRollDicesCommand(board, nextCommand, rollDicesThrower);
    }

    @Test
    void callNextCommandWhenTextDoesNotMatch() {

        command.handle("any other command");

        verify(nextCommand).handle("any other command");
    }

    @Test
    void triggerCommandWhenTextMatches() {
        RollDices rollDices = new RollDices(3, 2);
        when(rollDicesThrower.throwDices("Pippo"))
                .thenReturn(rollDices);

        command.handle("move Pippo");

        verify(board).movePlayer(new Move("Pippo", rollDices));
    }
}