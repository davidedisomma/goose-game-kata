package org.ucieffe.kata.goosegame;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void movePlayerFromStart() {
        Player pippo = new Player("Pippo", new StartBox());
        board.addPlayer(pippo);

        Move movePippo = board.movePlayer(new RollDices("Pippo", 2, 4));

        assertThat(movePippo.getPlayer(), is(pippo));
        assertThat(movePippo.getLastPosition(), is(new StartBox()));
        assertThat(movePippo.getCurrentPosition(), is(new Box(6)));
    }

    @Test
    public void movePlayerFromAnyBox() {
        Player pippo = new Player("Pippo", new Box(6));
        board.addPlayer(pippo);

        Move movePippo = board.movePlayer(new RollDices("Pippo", 2, 3));

        assertThat(movePippo.getPlayer(), is(pippo));
        assertThat(movePippo.getLastPosition(), is(new Box(6)));
        assertThat(movePippo.getCurrentPosition(), is(new Box(11)));
    }

    @Test
    public void movePlayerAndLandToWinningBox() {
        Player pippo = new Player("Pippo", new Box(60));
        board.addPlayer(pippo);

        Move movePippo = board.movePlayer(new RollDices("Pippo", 1, 2));

        assertThat(movePippo.getPlayer(), is(pippo));
        assertThat(movePippo.getLastPosition(), is(new Box(60)));
        assertThat(movePippo.getCurrentPosition(), is(new WinningBox()));
    }

}