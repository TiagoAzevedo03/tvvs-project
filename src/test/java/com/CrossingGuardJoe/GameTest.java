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
    void testRunWithPositiveSleepTime() throws Exception {
        Game game = spy(new Game());
        State state = mock(State.class);

        // Mocking `getCurrentState` to return a state repeatedly, followed by null
        doReturn(state).doReturn(state).doReturn(null).when(game).getCurrentState();

        // Mocking `step` to ensure a condition that makes `sleepTime > 0`
        doAnswer(invocation -> {
            // Simulate conditions that would cause sleepTime > 0
            Thread.sleep(1); // Placeholder logic to align with your method
            return null;
        }).when(state).step(any(Game.class), any(GUI.class), anyLong());

        Method method = Game.class.getDeclaredMethod("run");
        method.setAccessible(true);
        method.invoke(game);

        verify(state, atLeastOnce()).step(any(Game.class), any(GUI.class), anyLong());
        // Optionally verify behavior associated with the thread sleeping
    }

    @Test
    void testRunWithNonPositiveSleepTime() throws Exception {
        Game game = spy(new Game());
        State state = mock(State.class);

        // Mocking `getCurrentState` to return a state repeatedly, followed by null
        doReturn(state).doReturn(state).doReturn(null).when(game).getCurrentState();

        // Mocking `step` to simulate conditions where sleepTime <= 0
        doAnswer(invocation -> {
            // Simulate conditions that would cause sleepTime <= 0
            return null;
        }).when(state).step(any(Game.class), any(GUI.class), anyLong());

        Method method = Game.class.getDeclaredMethod("run");
        method.setAccessible(true);
        method.invoke(game);

        verify(state, atLeastOnce()).step(any(Game.class), any(GUI.class), anyLong());
        // Optionally verify that no sleep behavior occurs
    }

}
