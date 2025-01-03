package com.CrossingGuardJoe.model.game;

import com.CrossingGuardJoe.model.game.elements.Car;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AuxCarCheckTest {
    private List<Car> cars;

    @Before
    public void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car(0, 10));
        cars.add(new Car(0, 20));
        cars.add(new Car(0, 30));
    }

    @Test
    public void testIsCarOverlapping() {
        cars.add(new Car(0, 10));
        cars.add(new Car(0, 20));
        cars.add(new Car(0, 30));

        assertTrue(AuxCarCheck.isAnyCarOverlapping(15, cars, 10));

        assertTrue(AuxCarCheck.isAnyCarOverlapping(20, cars, 10));

        assertTrue(AuxCarCheck.isAnyCarOverlapping(35, cars, 10));

        cars.add(new Car(0, -10));
        assertTrue(AuxCarCheck.isAnyCarOverlapping(-5, cars, 10));

        assertTrue(AuxCarCheck.isAnyCarOverlapping(-10, cars, 10));

        assertFalse(AuxCarCheck.isAnyCarOverlapping(-20, cars, 10));
    }

    @Test
    public void testIsAnyCarOverlapping() {
        assertTrue(AuxCarCheck.isAnyCarOverlapping(15, cars, 10));
    }
}