package com.CrossingGuardJoe.states.menu;

import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.controller.menu.MenuController;
import com.CrossingGuardJoe.model.menu.Menu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuStateTest {

    private MenuState menuState;

    @BeforeEach
    void setUp() {
        Menu menu = mock(Menu.class);
        menuState = new MenuState(menu);
    }

    @Test
    void testGetController() {
        Controller<Menu> controller = menuState.getController();
        assertNotNull(controller);
        assertTrue(controller instanceof MenuController);
    }

    @Test
    void testGetViewer() {
        Viewer<Menu> viewer = menuState.getViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof MenuViewer);
    }
}