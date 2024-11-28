package com.CrossingGuardJoe.model.menu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatsMenuTest {
    private StatsMenu statsMenu;

    @Before
    public void setUp() {
        statsMenu = new StatsMenu(100, 5, 200, 10);
    }

    @Test
    public void testGetCurrentScore() {
        assertEquals(100, statsMenu.getCurrentScore());
    }

    @Test
    public void testGetCurrentLevel() {
        assertEquals(5, statsMenu.getCurrentLevel());
    }

    @Test
    public void testGetHighestScore() {
        assertEquals(200, statsMenu.getHighestScore());
    }

    @Test
    public void testGetHighestLevel() {
        assertEquals(10, statsMenu.getHighestLevel());
    }
}