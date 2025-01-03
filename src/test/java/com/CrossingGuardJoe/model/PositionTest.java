package com.CrossingGuardJoe.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
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
    void testEqualsSelf() {
        assertTrue(position.equals(position));
    }

    @Test
    void testEqualsNull() {
        assertFalse(position.equals(null));
    }

    @Test
    void testEqualsDifferentType() {
        assertFalse(position.equals("Not a Position"));
    }

    @Test
    void testEqualsSameValues() {
        Position samePosition = new Position(5, 10);
        assertTrue(position.equals(samePosition));
    }

    @Test
    void testEqualsDifferentX() {
        Position differentX = new Position(6, 10);
        assertFalse(position.equals(differentX));
    }

    @Test
    void testEqualsDifferentY() {
        Position differentY = new Position(5, 11);
        assertFalse(position.equals(differentY));
    }

    @Test
    void testEqualsDifferentPosition() {
        Position differentPosition = new Position(10, 20);
        assertFalse(position.equals(differentPosition));
    }

    @Test
    void testHashCode() {
        Position samePosition = new Position(5, 10);
        Position differentPosition = new Position(10, 20);

        assertEquals(position.hashCode(), samePosition.hashCode());

        assertNotEquals(position.hashCode(), differentPosition.hashCode());
    }
}