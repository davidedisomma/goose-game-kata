package org.ucieffe.kata.goosegame;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void movePlayerFromStart() {
        Player pippo = new Player("Pippo", new StartBox());
        board.addPlayer(pippo);

        GooseGameEvent event = board.movePlayer(new Move("Pippo", new RollDices(2, 4)));

        assertThat(event, instanceOf(StartEvent.class));
        StartEvent moveEvent = (StartEvent) event;
        assertThat(moveEvent.playerName, is("Pippo"));
        assertThat(moveEvent.firstDice, is(2));
        assertThat(moveEvent.secondDice, is(4));
        assertThat(moveEvent.currentPosition, is("6"));
    }

    @Test
    public void movePlayerFromAnyBox() {
        Player pippo = new Player("Pippo", new Box(6));
        board.addPlayer(pippo);

        GooseGameEvent event = board.movePlayer(new Move("Pippo", new RollDices(2, 3)));

        assertThat(event, instanceOf(MoveEvent.class));
        MoveEvent moveEvent = (MoveEvent)event;
        assertThat(moveEvent.playerName, is("Pippo"));
        assertThat(moveEvent.firstDice, is(2));
        assertThat(moveEvent.secondDice, is(3));
        assertThat(moveEvent.lastPosition, is("6"));
        assertThat(moveEvent.currentPosition, is("11"));
    }

    @Test
    public void movePlayerAndLandToWinningBox() {
        Player pippo = new Player("Pippo", new Box(60));
        board.addPlayer(pippo);

        GooseGameEvent event = board.movePlayer(new Move("Pippo", new RollDices(1, 2)));

        assertThat(event, instanceOf(WinningEvent.class));
        WinningEvent moveEvent = (WinningEvent)event;
        assertThat(moveEvent.playerName, is("Pippo"));
        assertThat(moveEvent.firstDice, is(1));
        assertThat(moveEvent.secondDice, is(2));
        assertThat(moveEvent.lastPosition, is("60"));
        assertThat(moveEvent.currentPosition, is("63"));
    }

    @Test
    public void movePlayerAndBounceBack() {
        Player pippo = new Player("Pippo", new Box(60));
        board.addPlayer(pippo);

        GooseGameEvent event = board.movePlayer(new Move("Pippo", new RollDices(2, 5)));

        assertThat(event, instanceOf(BounceBackEvent.class));
        BounceBackEvent bounceBackEvent = (BounceBackEvent)event;
        assertThat(bounceBackEvent.playerName, is("Pippo"));
        assertThat(bounceBackEvent.firstDice, is(2));
        assertThat(bounceBackEvent.secondDice, is(5));
        assertThat(bounceBackEvent.lastPosition, is("60"));
        assertThat(bounceBackEvent.currentPosition, is("59"));
    }

}