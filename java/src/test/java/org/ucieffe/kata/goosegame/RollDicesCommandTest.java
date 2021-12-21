package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.mockito.Mockito.*;

class RollDicesCommandTest {

    private Board board;
    private GooseGameCommand nextCommand;
    private RollDicesCommand rollDicesCommand;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        nextCommand = mock(GooseGameCommand.class);
        rollDicesCommand = new RollDicesCommand(board, nextCommand);
        when(board.movePlayer(any()))
                .thenReturn(new MoveEvent("Pippo", 4, 2, "0", "6"));
    }

    @Test
    void commandInvokeBoardWhenReceiveValidMoveCommand() {
        rollDicesCommand.handle("move Pippo 4, 2");

        verify(board).movePlayer(new Move("Pippo", new RollDices("Pippo", 4 , 2)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"fjdhsdhjsfjf", "move    Pippo 4,   2", "add player Pippo"})
    void commandIsSkippedWhenReceiveInvalidMoveCommand(String commandText) {
        rollDicesCommand.handle(commandText);

        verify(nextCommand).handle(commandText);
    }
}