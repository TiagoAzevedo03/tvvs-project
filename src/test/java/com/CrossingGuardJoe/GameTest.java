package com.CrossingGuardJoe;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.Menu;
import com.CrossingGuardJoe.states.State;
import com.CrossingGuardJoe.states.menu.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.awt.FontFormatException;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        game = new Game();
    }

    @Test
    public void testInitialState() {
        assertNotNull(game.getCurrentState());
    }

    @Test
    public void testSetState() {
        State<Menu> newState = new MenuState(new Menu());
        game.setState(newState);
        assertEquals(newState, game.getCurrentState());
    }

    @Test
    public void testPopState() {
        game.popState();
        assertNull(game.getCurrentState());
    }

    @Test
    public void testPopStateWhenStateStackIsEmpty() {
        game.popState();
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

    @Test
    void testRun() throws Exception {
        Game game = spy(new Game());
        State state = mock(State.class);
        doReturn(state).doReturn(state).doReturn(null).when(game).getCurrentState();
        doNothing().when(state).step(any(Game.class), any(GUI.class), anyLong());
        Method method = Game.class.getDeclaredMethod("run");
        method.setAccessible(true);
        method.invoke(game);
        verify(state, atLeastOnce()).step(any(Game.class), any(GUI.class), anyLong());
    }
}
