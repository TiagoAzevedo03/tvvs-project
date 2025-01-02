package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.menu.PauseMenu;
import com.CrossingGuardJoe.states.menu.StatsMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class PauseMenuControllerTest {

    private PauseMenuController pauseMenuController;
    private PauseMenu pauseMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        pauseMenu = mock(PauseMenu.class);
        game = mock(Game.class);
        pauseMenuController = new PauseMenuController(pauseMenu);
    }

    @Test
    void testNextActionUp() throws IOException {
        pauseMenuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

        verify(pauseMenu).navigateUp();
    }

    @Test
    void testNextActionDown() throws IOException {
        pauseMenuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(pauseMenu).navigateDown();
    }

    @Test
    void testNextActionSelectResume() throws IOException {
        when(pauseMenu.isSelectedResume()).thenReturn(true);

        pauseMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game).popState();
    }

    @Test
    void testNextActionSelectExit() throws IOException {
        when(pauseMenu.isSelectedExit()).thenReturn(true);

        pauseMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game, times(2)).popState();
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        pauseMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verifyNoInteractions(pauseMenu);
        verifyNoInteractions(game);
    }

    @Test
    void testNextActionSelectStats() throws IOException {
        when(pauseMenu.isSelectedStats()).thenReturn(true);
        when(pauseMenu.isSelectedExit()).thenReturn(false);

        Road mockCurrentGame = mock(Road.class);
        Joe mockJoe = mock(Joe.class);
        when(mockCurrentGame.getJoe()).thenReturn(mockJoe);
        when(mockJoe.getScore()).thenReturn(100);
        when(mockCurrentGame.getCurrentLevel()).thenReturn(1);
        when(pauseMenu.getCurrentGame()).thenReturn(mockCurrentGame);

        pauseMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game).setState(any(StatsMenuState.class));
    }
}