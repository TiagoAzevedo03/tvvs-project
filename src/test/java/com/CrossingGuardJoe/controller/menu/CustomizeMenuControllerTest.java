package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.ColorPaletteMenu;
import com.CrossingGuardJoe.model.menu.CustomizeMenu;
import com.CrossingGuardJoe.viewer.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

class CustomizeMenuControllerTest {

    private CustomizeMenuController customizeMenuController;
    private CustomizeMenu customizeMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        customizeMenu = mock(CustomizeMenu.class);
        game = mock(Game.class);
        customizeMenuController = new CustomizeMenuController(customizeMenu);

        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);

        Color color = mock(Color.class);
        when(color.getCharacter()).thenReturn('B');
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
    }

    @Test
    void testNextActionLeft() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuController.nextAction(game, GUI.ACTION.LEFT, System.currentTimeMillis());

        verify(customizeMenu).navigateLeft();
    }

    @Test
    void testNextActionRight() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuController.nextAction(game, GUI.ACTION.RIGHT, System.currentTimeMillis());

        verify(customizeMenu).navigateRight();
    }

    @Test
    void testNextActionUp() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

        verify(customizeMenu).navigateUp();
    }

    @Test
    void testNextActionDown() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(customizeMenu).navigateDown();
    }

    @Test
    void testNextActionEsc() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

        verify(game).popState();
    }

    @Test
    void testNextActionSelect() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);
        when(customizeMenu.getSelectedColorChar()).thenReturn('R');

        customizeMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(customizeMenu).setColorPaletteSelected(true);
    }

    @Test
    void testNextActionColorPaletteLeft() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuController.nextAction(game, GUI.ACTION.LEFT, System.currentTimeMillis());

        verify(customizeMenu.getColorPaletteMenu()).navigateLeft();
    }

    @Test
    void testNextActionColorPaletteRight() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuController.nextAction(game, GUI.ACTION.RIGHT, System.currentTimeMillis());

        verify(customizeMenu.getColorPaletteMenu()).navigateRight();
    }

    @Test
    void testNextActionColorPaletteEsc() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

        verify(customizeMenu).setColorPaletteSelected(false);
    }

    @Test
    void testNextActionColorPaletteSelect() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);
        ColorPaletteMenu colorPaletteMenu = customizeMenu.getColorPaletteMenu();
        when(colorPaletteMenu.getSelectedColorIndex()).thenReturn(0);

        customizeMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(customizeMenu).setColorPaletteSelected(false);
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        // Verify that no interactions occurred with customizeMenu or game
        verify(customizeMenu, never()).navigateLeft();
        verify(customizeMenu, never()).navigateRight();
        verify(customizeMenu, never()).navigateUp();
        verify(customizeMenu, never()).navigateDown();
        verify(customizeMenu, never()).setColorPaletteSelected(anyBoolean());
        verify(game, never()).popState();
    }
}