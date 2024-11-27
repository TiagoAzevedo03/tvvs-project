package com.CrossingGuardJoe.states;

import com.CrossingGuardJoe.controller.game.RoadController;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.viewer.game.GameViewer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {
    private GameState gameState;
    private Road road;

    @Before
    public void setUp() {
        road = new Road();
        gameState = new GameState(road);
    }

    @Test
    public void testGetController() {
        assertTrue(gameState.getController() instanceof RoadController);
    }

    @Test
    public void testGetViewer() {
        assertTrue(gameState.getViewer() instanceof GameViewer);
    }

    @Test
    public void testGetModel() {
        assertEquals(road, gameState.getModel());
    }
}