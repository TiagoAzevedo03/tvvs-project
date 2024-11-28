package com.CrossingGuardJoe.model.menu;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomizeMenuTest {
    private CustomizeMenu customizeMenu;

    @Before
    public void setUp() {
        customizeMenu = new CustomizeMenu();
        customizeMenu.getColorPaletteMenu().resetSelectedColorIndex();
    }

    @Test
    public void testInitialState() {
        assertNotNull(customizeMenu.getMenuLevels());
    }

    @Test
    public void testIsSelectedJoeCap() {
        assertTrue(customizeMenu.isSelectedJoeCap());
    }

    @Test
    public void testIsSelectedJoeClothes() {
        customizeMenu.navigateDown();
        assertTrue(customizeMenu.isSelectedJoeClothes());
    }

    @Test
    public void testIsSelectedJoeVest() {
        customizeMenu.navigateDown();
        customizeMenu.navigateDown();
        assertTrue(customizeMenu.isSelectedJoeVest());
    }

    @Test
    public void testIsSelectedJoeShoes() {
        customizeMenu.navigateDown();
        customizeMenu.navigateDown();
        customizeMenu.navigateDown();
        assertTrue(customizeMenu.isSelectedJoeShoes());
    }

    @Test
    public void testIsSelectedKidsShirt() {
        customizeMenu.navigateRight();
        assertTrue(customizeMenu.isSelectedKidsShirt());
    }

    @Test
    public void testIsSelectedKidsPants() {
        customizeMenu.navigateRight();
        customizeMenu.navigateDown();
        assertTrue(customizeMenu.isSelectedKidsPants());
    }

    @Test
    public void testIsSelectedKidsBackpack() {
        customizeMenu.navigateRight();
        customizeMenu.navigateDown();
        customizeMenu.navigateDown();
        assertTrue(customizeMenu.isSelectedKidsBackpack());
    }

    @Test
    public void testIsSelectedKidsShoes() {
        customizeMenu.navigateRight();
        customizeMenu.navigateDown();
        customizeMenu.navigateDown();
        customizeMenu.navigateDown();
        assertTrue(customizeMenu.isSelectedKidsShoes());
    }

    @Test
    public void testIsSelectedCarsBody() {
        customizeMenu.navigateRight();
        customizeMenu.navigateRight();
        assertTrue(customizeMenu.isSelectedCarsBody());
    }

    @Test
    public void testGetSelectedColorChar() {
        assertEquals('<', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testSetColorChange() {
        customizeMenu.setColorChange('A', 'B');
        assertEquals('A', customizeMenu.getOldColor());
        assertEquals('B', customizeMenu.getNewColor());
    }

    @Test
    public void testIsColorPaletteSelected() {
        assertFalse(customizeMenu.isColorPaletteSelected());
        customizeMenu.setColorPaletteSelected(true);
        assertTrue(customizeMenu.isColorPaletteSelected());
    }
}