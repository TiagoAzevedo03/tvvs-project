package com.CrossingGuardJoe.model.menu;


import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class CustomizeMenuTest {
    private CustomizeMenu customizeMenu;

    @Before
    public void setUp() {
        customizeMenu = spy(new CustomizeMenu());
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

    @Test
    public void testIsSelectedJoeCustomizeTrue() throws Exception {
        Field currentLevelField = CustomizeMenu.class.getDeclaredField("currentLevel");
        currentLevelField.setAccessible(true);
        currentLevelField.set(customizeMenu, 0);

        assertTrue(customizeMenu.isSelectedJoeCustomize());
    }

    @Test
    public void testIsSelectedJoeCustomizeFalse() throws Exception {
        Field currentLevelField = CustomizeMenu.class.getDeclaredField("currentLevel");
        currentLevelField.setAccessible(true);
        currentLevelField.set(customizeMenu, 1);

        assertFalse(customizeMenu.isSelectedJoeCustomize());
    }

    @Test
    public void testIsSelectedKidsCustomizeTrue() throws Exception {
        Field currentLevelField = CustomizeMenu.class.getDeclaredField("currentLevel");
        currentLevelField.setAccessible(true);
        currentLevelField.set(customizeMenu, 1);

        assertTrue(customizeMenu.isSelectedKidsCustomize());
    }

    @Test
    public void testIsSelectedKidsCustomizeFalse() throws Exception {
        Field currentLevelField = CustomizeMenu.class.getDeclaredField("currentLevel");
        currentLevelField.setAccessible(true);
        currentLevelField.set(customizeMenu, 0);

        assertFalse(customizeMenu.isSelectedKidsCustomize());
    }

    @Test
    public void testGetSelectedColorCharJoeCap() {
        doReturn(true).when(customizeMenu).isSelectedJoeCap();
        assertEquals('<', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharJoeClothes() {
        doReturn(true).when(customizeMenu).isSelectedJoeClothes();
        assertEquals('!', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharJoeVest() {
        doReturn(true).when(customizeMenu).isSelectedJoeVest();
        assertEquals('+', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharJoeShoes() {
        doReturn(true).when(customizeMenu).isSelectedJoeShoes();
        assertEquals('*', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharKidsShirt() {
        doReturn(true).when(customizeMenu).isSelectedKidsShirt();
        assertEquals('\'', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharKidsBackpack() {
        doReturn(true).when(customizeMenu).isSelectedKidsBackpack();
        assertEquals(')', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharKidsPants() {
        doReturn(true).when(customizeMenu).isSelectedKidsPants();
        assertEquals('(', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharKidsShoes() {
        doReturn(true).when(customizeMenu).isSelectedKidsShoes();
        assertEquals('&', customizeMenu.getSelectedColorChar());
    }

    @Test
    public void testGetSelectedColorCharCarsBody() {
        doReturn(true).when(customizeMenu).isSelectedCarsBody();
        assertEquals('@', customizeMenu.getSelectedColorChar());
    }
}