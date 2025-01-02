package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.menu.GameOverMenu;
import com.CrossingGuardJoe.states.menu.StatsMenuState;
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

    @Test
    void testNextActionDefaultCase() throws IOException {
        gameOverMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verifyNoInteractions(gameOverMenu);
        verifyNoInteractions(game);
    }

    @Test
    void testNextActionSelectStats() throws IOException {
        when(gameOverMenu.isSelectedStats()).thenReturn(true);
        when(gameOverMenu.isSelectedExit()).thenReturn(false);

        Road mockCurrentGame = mock(Road.class);
        Joe mockJoe = mock(Joe.class);
        when(mockCurrentGame.getJoe()).thenReturn(mockJoe);
        when(mockJoe.getScore()).thenReturn(100);
        when(mockCurrentGame.getCurrentLevel()).thenReturn(1);
        when(gameOverMenu.getCurrentGame()).thenReturn(mockCurrentGame);

        gameOverMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game).setState(any(StatsMenuState.class));
    }
}