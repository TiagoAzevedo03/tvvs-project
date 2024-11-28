package com.CrossingGuardJoe.model.game;

import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoadTest {
    private Road road;
    private Joe joe;
    private List<Kid> kids;
    private List<Car> cars;

    @Before
    public void setUp() {
        road = new Road();
        joe = new Joe(5, 10);
        kids = new ArrayList<>();
        kids.add(new Kid(1, 1));
        kids.add(new Kid(2, 2));
        cars = new ArrayList<>();
        cars.add(new Car(3, 3));
        cars.add(new Car(4, 4));
    }

    @Test
    public void testInitialState() {
        assertEquals(1, road.getCurrentLevel());
        assertFalse(road.isGameEnded());
        assertNull(road.getJoe());
        assertNull(road.getKids());
        assertNull(road.getCars());
    }

    @Test
    public void testLevelUp() {
        road.levelUp();
        assertEquals(2, road.getCurrentLevel());
        assertFalse(road.isGameEnded());

        for (int i = 2; i < 10; i++) {
            road.levelUp();
        }
        assertEquals(10, road.getCurrentLevel());
        assertTrue(road.isGameEnded());
    }

    @Test
    public void testSetAndGetJoe() {
        road.setJoe(joe);
        assertEquals(joe, road.getJoe());
    }

    @Test
    public void testSetAndGetKids() {
        road.setKids(kids);
        assertEquals(kids, road.getKids());
    }

    @Test
    public void testSetAndGetCars() {
        road.setCars(cars);
        assertEquals(cars, road.getCars());
    }

    @Test
    public void testSetKidsNextLevel() {
        road.setKidsNextLevel(3);
        assertNotNull(road.getKids());
        assertEquals(3, road.getKids().size());
    }
}