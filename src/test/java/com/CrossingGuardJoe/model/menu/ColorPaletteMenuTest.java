package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.viewer.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static org.mockito.Mockito.*;

public class ColorPaletteMenuTest {
    private ColorPaletteMenu colorPaletteMenu;

    @BeforeEach
    public void setUp() {
        colorPaletteMenu = new ColorPaletteMenu();
        mock(SoundsController.class);
    }

    @Test
    public void testInitialState() {
        List<Color> colors = colorPaletteMenu.getColorPalette();
        assertNotNull(colors);
        assertFalse(colors.isEmpty());
        assertEquals(0, colorPaletteMenu.getSelectedColorIndex());
    }

    @Test
    public void testIsColorSelected() {
        assertTrue(colorPaletteMenu.isColorSelected(0));
        colorPaletteMenu.navigateRight();
        assertFalse(colorPaletteMenu.isColorSelected(0));
        assertTrue(colorPaletteMenu.isColorSelected(1));
    }

    @Test
    public void testGetSelectedColorIndex() {
        assertEquals(0, colorPaletteMenu.getSelectedColorIndex());
        colorPaletteMenu.navigateRight();
        assertEquals(1, colorPaletteMenu.getSelectedColorIndex());
    }

    @Test
    public void testResetSelectedColorIndex() {
        colorPaletteMenu.navigateRight();
        colorPaletteMenu.resetSelectedColorIndex();
        assertEquals(0, colorPaletteMenu.getSelectedColorIndex());
    }
}