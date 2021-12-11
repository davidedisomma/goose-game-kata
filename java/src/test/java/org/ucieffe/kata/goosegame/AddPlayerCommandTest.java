package org.ucieffe.kata.goosegame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.*;

class AddPlayerCommandTest {

    private AddPlayerCommand addPlayerCommand;
    private GooseGameCommand nextCommand;
    private Board board;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        nextCommand = mock(GooseGameCommand.class);
        addPlayerCommand = new AddPlayerCommand(board, nextCommand);
    }

    @Test
    void addPlayerToBoardWhenAddPlayerText() {
        when(board.isAnExistentPlayer("Pippo"))
                .thenReturn(Boolean.FALSE);
        addPlayerCommand.handle("add player Pippo");

        verify(board).addPlayer(new Player("Pippo", new Box(0)));
    }

    @Test
    void returnAddPlayerEventWhenAddPlayerText() {
        when(board.isAnExistentPlayer(any()))
                .thenReturn(Boolean.FALSE);
        when(board.getAllPlayers())
                .thenReturn(List.of(
                        new Player("Pluto", new Box(0)),
                        new Player("Pippo", new Box(0))
                ));

        GooseGameEvent event = addPlayerCommand.handle("add player Pippo");

        assertThat(event, instanceOf(AddPlayerEvent.class));
        AddPlayerEvent addPlayerEvent = (AddPlayerEvent)event;
        assertThat(addPlayerEvent.playerNames, is(List.of("Pluto", "Pippo")));
    }

    @Test
    void returnPlayerAlreadyPresentEvent() {
        when(board.isAnExistentPlayer(any()))
                .thenReturn(Boolean.TRUE);

        GooseGameEvent event = addPlayerCommand.handle("add player Pippo");

        assertThat(event, instanceOf(PlayerAlreadyPresentEvent.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"add playerz Pippo", "add fhjkfh Pippo", "move Pippo 6,2"})
    void callNextCommandWhenOtherText(String commandText) {
        addPlayerCommand.handle(commandText);

        verify(nextCommand).handle(commandText);
    }
}