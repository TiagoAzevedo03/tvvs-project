package com.CrossingGuardJoe.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private Position position;

    @Before
    public void setUp() {
        position = new Position(5, 10);
    }

    @Test
    public void testGetX() {
        assertEquals(5, position.getX());
    }

    @Test
    public void testSetX() {
        position.setX(15);
        assertEquals(15, position.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(10, position.getY());
    }

    @Test
    public void testSetY() {
        position.setY(20);
        assertEquals(20, position.getY());
    }

    @Test
    public void testEquals() {
        Position samePosition = new Position(5, 10);
        Position differentPosition = new Position(10, 20);
        assertEquals(position, samePosition);
        assertNotEquals(position, differentPosition);
    }

    @Test
    public void testHashCode() {
        Position samePosition = new Position(5, 10);
        assertEquals(position.hashCode(), samePosition.hashCode());
    }
}