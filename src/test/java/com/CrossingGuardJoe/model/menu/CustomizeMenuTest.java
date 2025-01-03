package com.CrossingGuardJoe.model.menu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomizeMenuTest {

    private CustomizeMenu customizeMenu;

    @BeforeEach
    void setUp() {
        customizeMenu = new CustomizeMenu();
    }

    @Test
    void testNavigateLeft() {
        assertTrue(customizeMenu.isSelectedJoeCustomize());

        customizeMenu.navigateRight();
        assertTrue(customizeMenu.isSelectedKidsCustomize());
        customizeMenu.navigateLeft();
        assertTrue(customizeMenu.isSelectedJoeCustomize());

        customizeMenu.navigateLeft();
        assertFalse(customizeMenu.isSelectedCarsCustomize());
    }

    @Test
    void testNavigateRight() {
        assertTrue(customizeMenu.isSelectedJoeCustomize());

        customizeMenu.navigateRight();
        assertTrue(customizeMenu.isSelectedKidsCustomize());
        customizeMenu.navigateRight();
        assertTrue(customizeMenu.isSelectedCarsCustomize());
        customizeMenu.navigateRight();
        assertFalse(customizeMenu.isSelectedJoeCustomize());
    }

    @Test
    void testNavigateUpAndDown() {
        customizeMenu.navigateDown();
        assertFalse(customizeMenu.isSelectedJoeCap());

        customizeMenu.navigateDown();
        assertFalse(customizeMenu.isSelectedJoeClothes());

        customizeMenu.navigateUp();
        assertFalse(customizeMenu.isSelectedJoeCap());
    }

    @Test
    void testNavigateDown() {
        assertTrue(customizeMenu.isSelectedJoeCustomize());

        customizeMenu.navigateDown();
        assertFalse(customizeMenu.isSelectedKidsCustomize());
        customizeMenu.navigateDown();
        assertFalse(customizeMenu.isSelectedCarsCustomize());
        customizeMenu.navigateDown();
        assertTrue(customizeMenu.isSelectedJoeCustomize());
    }

    @Test
    void testIsSelectedJoeCustomize() {
        assertTrue(customizeMenu.isSelectedJoeCustomize());
        customizeMenu.navigateRight();
        assertFalse(customizeMenu.isSelectedJoeCustomize());
    }

    @Test
    void testIsSelectedJoeCap() {
        customizeMenu.navigateDown();
        assertFalse(customizeMenu.isSelectedJoeCap());
    }

    @Test
    void testGetSelectedColorChar() {
        assertEquals('<', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateDown();
        assertEquals('!', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateDown();
        assertEquals('+', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateDown();
        assertEquals('*', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateRight();

        assertEquals('&', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateUp();
        assertEquals(')', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateUp();
        assertEquals('(', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateUp();
        assertEquals('\'', customizeMenu.getSelectedColorChar());

        customizeMenu.navigateRight();
        assertEquals('@', customizeMenu.getSelectedColorChar());
    }

    @Test
    void testDefinedColors() {
        List<Option> definedColors = customizeMenu.getDefinedColors();
        assertNotNull(definedColors);
        assertEquals(9, definedColors.size());
    }

    @Test
    void testColorPaletteSelection() {
        assertFalse(customizeMenu.isColorPaletteSelected());
        customizeMenu.setColorPaletteSelected(true);
        assertTrue(customizeMenu.isColorPaletteSelected());
    }

    @Test
    void testSetColorChange() {
        customizeMenu.setColorChange('A', 'B');
        assertEquals('A', customizeMenu.getOldColor());
        assertEquals('B', customizeMenu.getNewColor());
    }

    @Test
    void testGetMenuLevels() {
        List<List<Option>> menuLevels = customizeMenu.getMenuLevels();
        assertNotNull(menuLevels);
        assertEquals(3, menuLevels.size());
    }

    @Test
    void testIsSelectedOption() {
        customizeMenu.navigateDown();
        assertFalse(customizeMenu.isSelectedOption(0, 1)); // Joe Cap

        customizeMenu.navigateRight();
        customizeMenu.navigateDown();
        assertFalse(customizeMenu.isSelectedOption(1, 1)); // Kids Shirt
    }

    @Test
    void testIsSelectedKidsCustomize() {
        customizeMenu.navigateRight();
        assertTrue(customizeMenu.isSelectedKidsCustomize());
        assertFalse(customizeMenu.isSelectedJoeCustomize());
        assertFalse(customizeMenu.isSelectedCarsCustomize());
    }

    @Test
    void testIsSelectedCarsCustomize() {
        customizeMenu.navigateRight();
        customizeMenu.navigateRight();
        assertTrue(customizeMenu.isSelectedCarsCustomize());
        assertFalse(customizeMenu.isSelectedJoeCustomize());
        assertFalse(customizeMenu.isSelectedKidsCustomize());
    }

    @Test
    void testGetColorPaletteMenu() {
        ColorPaletteMenu colorPaletteMenu = customizeMenu.getColorPaletteMenu();
        assertNotNull(colorPaletteMenu);
    }

    @Test
    void testConstructorInitialState() {
        assertTrue(customizeMenu.isSelectedJoeCustomize());
        assertFalse(customizeMenu.isSelectedKidsCustomize());
        assertFalse(customizeMenu.isSelectedCarsCustomize());
    }
}
