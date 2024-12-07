package com.CrossingGuardJoe.states.menu;

import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.controller.menu.PauseMenuController;
import com.CrossingGuardJoe.model.menu.PauseMenu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.menu.PauseMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PauseMenuStateTest {

    private PauseMenuState pauseMenuState;

    @BeforeEach
    void setUp() {
        PauseMenu pauseMenu = mock(PauseMenu.class);
        pauseMenuState = new PauseMenuState(pauseMenu);
    }

    @Test
    void testGetController() {
        Controller<PauseMenu> controller = pauseMenuState.getController();
        assertNotNull(controller);
        assertTrue(controller instanceof PauseMenuController);
    }

    @Test
    void testGetViewer() {
        Viewer<PauseMenu> viewer = pauseMenuState.getViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof PauseMenuViewer);
    }
}