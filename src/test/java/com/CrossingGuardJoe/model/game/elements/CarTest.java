package com.CrossingGuardJoe.model.game.elements;

import com.CrossingGuardJoe.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {
    private Car car;

    @Before
    public void setUp() {
        car = new Car(5, 10);
    }

    @Test
    public void testSetPosition() {
        car.setPosition(new Position(15, 20));
        assertEquals(15, car.getPosition().getX());
        assertEquals(20, car.getPosition().getY());
    }
}