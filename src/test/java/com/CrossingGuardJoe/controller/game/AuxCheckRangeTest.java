package com.CrossingGuardJoe.controller.game;

import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuxCheckRangeTest {

    @Test
    void testIsInRangeJoeKid() {
        Joe joe = new Joe(0, 0);
        Kid kid = new Kid(0, 0);

        joe.setPosition(new Position(10, 0));
        kid.setPosition(new Position(15, 0));
        assertTrue(AuxCheckRange.isInRangeJoeKid(joe, kid));

        joe.setPosition(new Position(30, 0));
        kid.setPosition(new Position(15, 0));
        assertFalse(AuxCheckRange.isInRangeJoeKid(joe, kid));

        joe.setPosition(new Position(10, 0));
        kid.setPosition(new Position(70, 0));
        assertFalse(AuxCheckRange.isInRangeJoeKid(joe, kid));

        joe.setPosition(new Position(24, 0));
        kid.setPosition(new Position(15, 0));
        assertTrue(AuxCheckRange.isInRangeJoeKid(joe, kid));

        joe.setPosition(new Position(-6, 0));
        kid.setPosition(new Position(15, 0));
        assertTrue(AuxCheckRange.isInRangeJoeKid(joe, kid));

        joe.setPosition(new Position(25, 0));
        kid.setPosition(new Position(15, 0));
        assertFalse(AuxCheckRange.isInRangeJoeKid(joe, kid));

        joe.setPosition(new Position(-7, 0));
        kid.setPosition(new Position(15, 0));
        assertFalse(AuxCheckRange.isInRangeJoeKid(joe, kid));
    }

    @Test
    void testIsInRangeCarKid() {
        Car car = new Car(0, 0);
        Kid kid = new Kid(0, 0);

        car.setPosition(new Position(20, 10));
        kid.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(40, 10));
        kid.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(10, 10));
        kid.setPosition(new Position(70, 15));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(20, 40));
        kid.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(20, 5));
        kid.setPosition(new Position(25, 75));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(35, 10));
        kid.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(-27, 10));
        kid.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(20, 30));
        kid.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(20, -50));
        kid.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(36, 10));
        kid.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(-28, 10));
        kid.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(20, 31));
        kid.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));

        car.setPosition(new Position(20, -51));
        kid.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeCarKid(car, kid));
    }

    @Test
    void testIsInRangeLeftCarJoe() {
        Car car = new Car(0, 0);
        Joe joe = new Joe(0, 0);

        car.setPosition(new Position(20, 10));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(50, 10));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(10, 10));
        joe.setPosition(new Position(70, 15));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(20, 40));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(20, 5));
        joe.setPosition(new Position(25, 75));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(46, 10));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(7, 10));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(20, 30));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(20, -50));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(47, 10));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(6, 10));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(20, 31));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));

        car.setPosition(new Position(20, -51));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeLeftCarJoe(car, joe));
    }

    @Test
    void testIsInRangeRightCarJoe() {
        Car car = new Car(0, 0);
        Joe joe = new Joe(0, 0);

        car.setPosition(new Position(20, 10));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(50, 10));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(10, 10));
        joe.setPosition(new Position(70, 15));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(20, 40));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(20, 5));
        joe.setPosition(new Position(25, 75));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(45, 10));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(-27, 10));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(20, 30));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(20, -50));
        joe.setPosition(new Position(25, 15));
        assertTrue(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(46, 10));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(-28, 10));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(20, 31));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));

        car.setPosition(new Position(20, -51));
        joe.setPosition(new Position(25, 15));
        assertFalse(AuxCheckRange.isInRangeRightCarJoe(car, joe));
    }
}