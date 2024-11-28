package com.CrossingGuardJoe.model.game;

import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RoadBuilderTest {
    private RoadBuilder roadBuilder;

    @Before
    public void setUp() {
        roadBuilder = new RoadBuilder();
    }

    @Test
    public void testCreateRoad() {
        Road road = roadBuilder.createRoad();
        assertNotNull(road);
        assertNotNull(road.getJoe());
        assertNotNull(road.getKids());
        assertNotNull(road.getCars());
    }

    @Test
    public void testCreateKidsNextLevel() {
        List<Kid> kids = roadBuilder.createKidsNextLevel(5);
        assertNotNull(kids);
        assertEquals(5, kids.size());
    }
}