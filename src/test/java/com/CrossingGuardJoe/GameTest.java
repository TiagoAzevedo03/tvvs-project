package com.CrossingGuardJoe;

import com.CrossingGuardJoe.model.menu.Menu;
import com.CrossingGuardJoe.states.State;
import com.CrossingGuardJoe.states.menu.MenuState;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;

import static org.junit.Assert.*;


public class GameTest {
    private Game game;

    @Before
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = new Game();
    }

    @Test
    public void testInitialState() {
        assertNotNull(game.getCurrentState());
    }

    @Test
    public void testSetState() {
        State newState = new MenuState(new Menu());
        game.setState(newState);
        assertEquals(newState, game.getCurrentState());
    }

    @Test
    public void testPopState() {
        game.popState();
        assertNull(game.getCurrentState());
    }

    @Test
    public void testHighestScore() {
        game.setHighestScore(100);
        assertEquals(100, game.getHighestScore());
    }

    @Test
    public void testHighestLevel() {
        game.setHighestLevel(5);
        assertEquals(5, game.getHighestLevel());
    }
}