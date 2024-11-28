package com.CrossingGuardJoe.model.game.elements;

import com.CrossingGuardJoe.model.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElementTest {
    private Element element;

    @Before
    public void setUp() {
        element = new Element(5, 10);
    }

    @Test
    public void testInitialPosition() {
        Position position = element.getPosition();
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    public void testSetPosition() {
        Position newPosition = new Position(15, 20);
        element.setPosition(newPosition);
        Position position = element.getPosition();
        assertEquals(15, position.getX());
        assertEquals(20, position.getY());
    }
}