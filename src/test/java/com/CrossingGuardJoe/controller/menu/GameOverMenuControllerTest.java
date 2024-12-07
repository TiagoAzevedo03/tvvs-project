package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.GameOverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameOverMenuControllerTest {

    private GameOverMenuController gameOverMenuController;
    private GameOverMenu gameOverMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        gameOverMenu = mock(GameOverMenu.class);
        game = mock(Game.class);
        gameOverMenuController = new GameOverMenuController(gameOverMenu);
    }

    @Test
    void testNextActionUp() throws IOException {
        gameOverMenuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

        verify(gameOverMenu).navigateUp();
    }

    @Test
    void testNextActionDown() throws IOException {
        gameOverMenuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(gameOverMenu).navigateDown();
    }

    @Test
    void testNextActionSelectExit() throws IOException {
        when(gameOverMenu.isSelectedExit()).thenReturn(true);

        gameOverMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game).popState();
    }
}