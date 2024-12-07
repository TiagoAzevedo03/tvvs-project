package com.CrossingGuardJoe.states.menu;

import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.controller.menu.StatsMenuController;
import com.CrossingGuardJoe.model.menu.StatsMenu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.menu.StatsMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatsMenuStateTest {

    private StatsMenuState statsMenuState;

    @BeforeEach
    void setUp() {
        StatsMenu statsMenu = mock(StatsMenu.class);
        statsMenuState = new StatsMenuState(statsMenu);
    }

    @Test
    void testGetController() {
        Controller<StatsMenu> controller = statsMenuState.getController();
        assertNotNull(controller);
        assertTrue(controller instanceof StatsMenuController);
    }

    @Test
    void testGetViewer() {
        Viewer<StatsMenu> viewer = statsMenuState.getViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof StatsMenuViewer);
    }
}