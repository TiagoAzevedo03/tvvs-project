package com.CrossingGuardJoe.states;

import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.controller.game.RoadController;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.viewer.Viewer;
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
        Controller<Road> controller = gameState.getController();
        assertNotNull(controller);
        assertTrue(controller instanceof RoadController);
    }

    @Test
    public void testGetViewer() {
        Viewer<Road> viewer = gameState.getViewer();
        assertNotNull(viewer);
        assertTrue(viewer instanceof GameViewer);
    }

    @Test
    public void testGetModel() {
        assertEquals(road, gameState.getModel());
    }

    @Test
    public void testGetControllerNotNull() {
        assertNotNull(gameState.getController());
    }

    @Test
    public void testGetViewerNotNull() {
        assertNotNull(gameState.getViewer());
    }
}