package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.PauseMenu;
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
}