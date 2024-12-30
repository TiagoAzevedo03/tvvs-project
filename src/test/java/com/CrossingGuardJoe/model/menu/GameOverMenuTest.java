package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.model.game.Road;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class GameOverMenuTest {
    private GameOverMenu gameOverMenu;
    private Road road;

    @BeforeEach
    public void setUp() {
        road = new Road();
        gameOverMenu = new GameOverMenu(true, road);
    }

    @Test
    public void testInitialState() {
        assertNotNull(gameOverMenu.getOption(0));
        assertNotNull(gameOverMenu.getOption(1));
        assertEquals(2, gameOverMenu.getNumberOptions());
        assertEquals(0, gameOverMenu.getOptionSelected());
    }

    @Test
    public void testNavigateUp() {
        gameOverMenu.navigateUp();
        assertEquals(1, gameOverMenu.getOptionSelected());
    }

    @Test
    public void testNavigateDown() {
        gameOverMenu.navigateDown();
        assertEquals(1, gameOverMenu.getOptionSelected());
    }

    @Test
    public void testIsSelectedOption() {
        assertFalse(gameOverMenu.isSelectedOption(1));
        assertTrue(gameOverMenu.isSelectedOption(0));
        gameOverMenu.navigateDown();
        assertTrue(gameOverMenu.isSelectedOption(1));
    }

    @Test
    public void testIsSelectedStats() {
        assertTrue(gameOverMenu.isSelectedStats());
        gameOverMenu.navigateDown();
        assertFalse(gameOverMenu.isSelectedStats());
    }

    @Test
    public void testIsSelectedExit() {
        assertFalse(gameOverMenu.isSelectedExit());
        gameOverMenu.navigateDown();
        assertTrue(gameOverMenu.isSelectedExit());
    }

    @Test
    public void testIsWin() {
        assertTrue(gameOverMenu.isWin());
    }

    @Test
    public void testGetCurrentGame() {
        assertEquals(road, gameOverMenu.getCurrentGame());
    }

    @Test
    public void testGetOption() {
        Option option = gameOverMenu.getOption(0);
        assertEquals("Stats", option.name());
    }

    @Test
    public void testGetNumberOptions() {
        assertEquals(2, gameOverMenu.getNumberOptions());
    }

    @Test
    public void testGetOptionSelected() {
        assertEquals(0, gameOverMenu.getOptionSelected());
        gameOverMenu.navigateDown();
        assertEquals(1, gameOverMenu.getOptionSelected());
    }
}