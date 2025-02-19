package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
    }

    @Test
    public void testInitialSelectedOption() {
        assertTrue(menu.isSelectedStartGame());
    }

    @Test
    public void testNavigateUp() {
        menu.navigateUp();
        assertTrue(menu.isSelectedExit());
    }

    @Test
    public void testNavigateDown() {
        menu.navigateDown();
        assertTrue(menu.isSelectedInstructions());
    }

    @Test
    public void testGetOption() {
        Option option = menu.getOption(0);
        assertEquals("Start Game", option.name());
        assertEquals(new Position(203, 220), option.position());
    }

    @Test
    public void testGetNumberOptions() {
        assertEquals(4, menu.getNumberOptions());
    }

    @Test
    public void testIsSelectedOption() {
        assertFalse(menu.isSelectedOption(1));
        assertTrue(menu.isSelectedOption(0));
        menu.navigateDown();
        assertTrue(menu.isSelectedOption(1));
    }

    @Test
    public void testIsSelectedStartGame() {
        assertTrue(menu.isSelectedStartGame());
        menu.navigateDown();
        assertFalse(menu.isSelectedStartGame());
        menu.navigateUp();
        assertTrue(menu.isSelectedStartGame());
    }

    @Test
    public void testIsSelectedInstructions() {
        menu.navigateDown();
        assertTrue(menu.isSelectedInstructions());
        menu.navigateDown();
        assertFalse(menu.isSelectedInstructions());
        menu.navigateUp();
        assertTrue(menu.isSelectedInstructions());
    }

    @Test
    public void testIsSelectedCustomize() {
        menu.navigateDown();
        menu.navigateDown();
        assertTrue(menu.isSelectedCustomize());
        menu.navigateDown();
        assertFalse(menu.isSelectedCustomize());
        menu.navigateUp();
        assertTrue(menu.isSelectedCustomize());
    }

    @Test
    public void testIsSelectedExit() {
        menu.navigateDown();
        menu.navigateDown();
        menu.navigateDown();
        assertTrue(menu.isSelectedExit());
        menu.navigateUp();
        assertFalse(menu.isSelectedExit());
        menu.navigateDown();
        assertTrue(menu.isSelectedExit());
    }
}