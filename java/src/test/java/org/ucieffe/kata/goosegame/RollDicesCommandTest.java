package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
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
                .thenReturn(new Move(
                        new Player("Pippo", new Box(6)),
                        new RollDices("Pippo", 4, 2),
                        new Box(0)
                )
        );
    }

    @Test
    void commandInvokeBoardWhenReceiveValidMoveCommand() {
        rollDicesCommand.handle("move Pippo 4, 2");

        verify(board).movePlayer(new RollDices("Pippo", 4, 2));
    }

    @Test
    void returnMoveEventWhenStandardMove() {
        when(board.movePlayer(any()))
                .thenReturn(new Move(
                        new Player("Pippo", new Box(6)),
                        new RollDices("Pippo", 4, 2),
                        new Box(0)
                        )
                );

        GooseGameEvent event = rollDicesCommand.handle("move Pippo 4, 2");

        assertThat(event, instanceOf(MoveEvent.class));
        MoveEvent moveEvent = (MoveEvent)event;
        assertThat(moveEvent.playerName, is("Pippo"));
        assertThat(moveEvent.firstDice, is(4));
        assertThat(moveEvent.secondDice, is(2));
        assertThat(moveEvent.lastPosition, is("0"));
        assertThat(moveEvent.currentPosition, is("6"));
    }

    @Test
    void returnWinningEventWhenWinningMove() {
        when(board.movePlayer(any()))
                .thenReturn(new WinningMove(
                        new Player("Pippo", new Box(63)),
                        new RollDices("Pippo", 4, 2),
                        new Box(57)
                        )
                );

        GooseGameEvent event = rollDicesCommand.handle("move Pippo 4, 2");

        assertThat(event, instanceOf(WinningEvent.class));
        WinningEvent winningEvent = (WinningEvent)event;
        assertThat(winningEvent.playerName, is("Pippo"));
        assertThat(winningEvent.firstDice, is(4));
        assertThat(winningEvent.secondDice, is(2));
        assertThat(winningEvent.lastPosition, is("57"));
        assertThat(winningEvent.currentPosition, is("63"));
    }

    @Test
    void returnBouncedBackEventWhenBouncedBackMove() {
        when(board.movePlayer(any()))
                .thenReturn(new BounceBackMove(
                                new Player("Pippo", new Box(63)),
                                new RollDices("Pippo", 4, 2),
                                new Box(57)
                        )
                );

        GooseGameEvent event = rollDicesCommand.handle("move Pippo 4, 2");

        assertThat(event, instanceOf(BounceBackEvent.class));
        BounceBackEvent bouncedBackEvent = (BounceBackEvent)event;
        assertThat(bouncedBackEvent.playerName, is("Pippo"));
        assertThat(bouncedBackEvent.firstDice, is(4));
        assertThat(bouncedBackEvent.secondDice, is(2));
        assertThat(bouncedBackEvent.lastPosition, is("57"));
        assertThat(bouncedBackEvent.currentPosition, is("63"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"fjdhsdhjsfjf", "move    Pippo 4,   2", "add player Pippo"})
    void commandIsSkippedWhenReceiveInvalidMoveCommand(String commandText) {
        rollDicesCommand.handle(commandText);

        verify(nextCommand).handle(commandText);
    }
}