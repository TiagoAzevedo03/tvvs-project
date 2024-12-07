package com.CrossingGuardJoe.states.menu;

import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.controller.menu.CustomizeMenuController;
import com.CrossingGuardJoe.model.menu.CustomizeMenu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.menu.CustomizeMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomizeMenuStateTest {

    private CustomizeMenuState customizeMenuState;

    @BeforeEach
    void setUp() {
        CustomizeMenu customizeMenu = mock(CustomizeMenu.class);
        customizeMenuState = new CustomizeMenuState(customizeMenu);
    }

    @Test
    void testGetController() {
        Controller<CustomizeMenu> controller = customizeMenuState.getController();
        assertNotNull(controller);
        assertTrue(controller instanceof CustomizeMenuController);
    }

    @Test
    void testGetViewer() {
        Viewer<CustomizeMenu> viewer = customizeMenuState.getViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof CustomizeMenuViewer);
    }
}