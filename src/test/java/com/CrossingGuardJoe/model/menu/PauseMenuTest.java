package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.model.game.Road;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
public class PauseMenuTest {
    private PauseMenu pauseMenu;
    private Road road;

    @BeforeEach
    public void setUp() {
        road = new Road();
        pauseMenu = new PauseMenu(road);
    }

    @Test
    public void testInitialState() {
        assertNotNull(pauseMenu.getOption(0));
        assertNotNull(pauseMenu.getOption(1));
        assertNotNull(pauseMenu.getOption(2));
        assertEquals(3, pauseMenu.getNumberOptions());
    }

    @Test
    public void testIsSelectedOption() {
        assertTrue(pauseMenu.isSelectedOption(0));
        pauseMenu.navigateDown();
        assertTrue(pauseMenu.isSelectedOption(1));
        pauseMenu.navigateUp();
        assertFalse(pauseMenu.isSelectedOption(1));
    }

    @Test
    public void testIsSelectedResume() {
        assertTrue(pauseMenu.isSelectedResume());
        pauseMenu.navigateDown();
        assertFalse(pauseMenu.isSelectedResume());
    }

    @Test
    public void testIsSelectedStats() {
        assertFalse(pauseMenu.isSelectedStats());
        pauseMenu.navigateDown();
        assertTrue(pauseMenu.isSelectedStats());
    }

    @Test
    public void testIsSelectedExit() {
        assertFalse(pauseMenu.isSelectedExit());
        pauseMenu.navigateDown();
        pauseMenu.navigateDown();
        assertTrue(pauseMenu.isSelectedExit());
    }

    @Test
    public void testGetCurrentGame() {
        assertEquals(road, pauseMenu.getCurrentGame());
    }

    @Test
    public void testGetOption() {
        Option option = pauseMenu.getOption(0);
        assertEquals("Resume", option.name());
    }

    @Test
    public void testGetNumberOptions() {
        assertEquals(3, pauseMenu.getNumberOptions());
    }
}