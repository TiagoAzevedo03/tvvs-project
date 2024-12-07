package com.CrossingGuardJoe.states.menu;

import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.controller.menu.InstructionsMenuController;
import com.CrossingGuardJoe.model.menu.InstructionsMenu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.menu.InstructionsMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstructionsMenuStateTest {

    private InstructionsMenuState instructionsMenuState;

    @BeforeEach
    void setUp() {
        InstructionsMenu instructionsMenu = mock(InstructionsMenu.class);
        instructionsMenuState = new InstructionsMenuState(instructionsMenu);
    }

    @Test
    void testGetController() {
        Controller<InstructionsMenu> controller = instructionsMenuState.getController();
        assertNotNull(controller);
        assertTrue(controller instanceof InstructionsMenuController);
    }

    @Test
    void testGetViewer() {
        Viewer<InstructionsMenu> viewer = instructionsMenuState.getViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof InstructionsMenuViewer);
    }
}