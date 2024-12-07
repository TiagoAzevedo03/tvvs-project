package com.CrossingGuardJoe.states.menu;

import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.controller.menu.GameOverMenuController;
import com.CrossingGuardJoe.model.menu.GameOverMenu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.menu.GameOverViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameOverStateTest {

    private GameOverState gameOverState;

    @BeforeEach
    void setUp() {
        GameOverMenu gameOverMenu = mock(GameOverMenu.class);
        gameOverState = new GameOverState(gameOverMenu);
    }

    @Test
    void testGetController() {
        Controller<GameOverMenu> controller = gameOverState.getController();
        assertNotNull(controller);
        assertTrue(controller instanceof GameOverMenuController);
    }

    @Test
    void testGetViewer() {
        Viewer<GameOverMenu> viewer = gameOverState.getViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof GameOverViewer);
    }
}