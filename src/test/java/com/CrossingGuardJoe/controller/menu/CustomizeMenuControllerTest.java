package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.CustomizeMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class CustomizeMenuControllerTest {

    private CustomizeMenuController customizeMenuController;
    private CustomizeMenu customizeMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        customizeMenu = mock(CustomizeMenu.class);
        game = mock(Game.class);
        mock(SoundsController.class);
        customizeMenuController = new CustomizeMenuController(customizeMenu);
    }

    @Test
    void testNextActionColorPaletteEsc() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

        verify(customizeMenu).setColorPaletteSelected(false);
    }
}