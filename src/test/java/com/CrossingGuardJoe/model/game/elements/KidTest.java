package com.CrossingGuardJoe.model.game.elements;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KidTest {
    private Kid kid;

    @Before
    public void setUp() {
        kid = new Kid(5, 10);
    }

    @Test
    public void testInitialState() {
        assertFalse(kid.getIsHit());
        assertFalse(kid.isSelected());
        assertTrue(kid.isFirstHalfOfMovement());
        assertFalse(kid.getDeathCounted());
        assertFalse(kid.getPass());
        assertFalse(kid.getCounted());
        assertEquals(0, kid.getMovesInQueueLeft());
        assertFalse(kid.getWalkingState());
        assertEquals(100, kid.getPoints());
    }

    @Test
    public void testSetWalking() {
        kid.setWalking();
        assertTrue(kid.getWalkingState());
    }

    @Test
    public void testSetNotWalking() {
        kid.setWalking();
        kid.setNotWalking();
        assertFalse(kid.getWalkingState());
        assertTrue(kid.isFirstHalfOfMovement());
    }

    @Test
    public void testIsFirstHalfOfMovement() {
        assertTrue(kid.isFirstHalfOfMovement());
    }

    @Test
    public void testSetFirstHalfOfMovement() {
        kid.setFirstHalfOfMovement(false);
        assertFalse(kid.isFirstHalfOfMovement());
    }

    @Test
    public void testIsSelected() {
        assertFalse(kid.isSelected());
    }

    @Test
    public void testSetSelected() {
        kid.setSelected();
        assertTrue(kid.isSelected());
    }

    @Test
    public void testSetNotSelected() {
        kid.setSelected();
        kid.setNotSelected();
        assertFalse(kid.isSelected());
    }

    @Test
    public void testIsHit() {
        kid.isHit();
        assertTrue(kid.getIsHit());
        assertFalse(kid.isSelected());
    }

    @Test
    public void testSetDead() {
        kid.setDead();
        assertTrue(kid.getDeathCounted());
    }

    @Test
    public void testAddMovesInQueueLeft() {
        kid.addMovesInQueueLeft(5);
        assertEquals(5, kid.getMovesInQueueLeft());
    }

    @Test
    public void testGetMovesInQueueLeft() {
        assertEquals(0, kid.getMovesInQueueLeft());
        kid.addMovesInQueueLeft(3);
        assertEquals(3, kid.getMovesInQueueLeft());
    }

    @Test
    public void testSetPass() {
        kid.setPass();
        assertTrue(kid.getPass());
    }

    @Test
    public void testSetCountToNextLevel() {
        kid.setCountToNextLevel();
        assertTrue(kid.getCounted());
    }

    @Test
    public void testGetPoints() {
        assertEquals(100, kid.getPoints());
    }
}