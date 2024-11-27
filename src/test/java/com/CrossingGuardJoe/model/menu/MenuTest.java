package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuTest {
    private Menu menu;

    @Before
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
        assertTrue(menu.isSelectedOption(0));
        menu.navigateDown();
        assertTrue(menu.isSelectedOption(1));
    }

    @Test
    public void testIsSelectedStartGame() {
        assertTrue(menu.isSelectedStartGame());
    }

    @Test
    public void testIsSelectedInstructions() {
        menu.navigateDown();
        assertTrue(menu.isSelectedInstructions());
    }

    @Test
    public void testIsSelectedCustomize() {
        menu.navigateDown();
        menu.navigateDown();
        assertTrue(menu.isSelectedCustomize());
    }

    @Test
    public void testIsSelectedExit() {
        menu.navigateDown();
        menu.navigateDown();
        menu.navigateDown();
        assertTrue(menu.isSelectedExit());
    }
}