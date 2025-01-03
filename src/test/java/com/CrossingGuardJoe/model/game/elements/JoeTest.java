package com.CrossingGuardJoe.model.game.elements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JoeTest {
    private Joe joe;

    @BeforeEach
    public void setUp() {
        joe = spy(new Joe(5, 10));
    }

    @Test
    public void testInitialState() {
        assertEquals(0, joe.getScore());
        assertEquals(10, joe.getHearts());
        assertFalse(joe.getIsWalkingState());
        assertTrue(joe.getIsWalkingToLeft());
        assertFalse(joe.getIsRaisingStopSign());
        assertFalse(joe.getIsPassSign());
        assertTrue(joe.isFirstHalfOfMovement());
        assertFalse(joe.getIsHit());
        assertTrue(joe.getHitLeft());
    }

    @Test
    public void testAddScore() {
        joe.addScore(5);
        assertEquals(5, joe.getScore());
    }

    @Test
    public void testRemoveHeartWhenHeartsGreaterThanZero() {
        joe.removeHeart();
        assertEquals(9, joe.getHearts());
    }

    @Test
    public void testRemoveHeartWhenHeartsIsZero() {
        for (int i = 0; i < 10; i++) {
            joe.removeHeart();
        }
        joe.removeHeart();
        assertEquals(0, joe.getHearts());
    }

    @Test
    public void testStartWalkingToLeft() {
        joe.startWalkingToLeft();
        assertTrue(joe.getIsWalkingState());
        assertTrue(joe.getIsWalkingToLeft());
        assertFalse(joe.getIsRaisingStopSign());
        assertFalse(joe.getIsPassSign());
    }

    @Test
    public void testStartWalkingToRight() {
        joe.startWalkingToRight();
        assertTrue(joe.getIsWalkingState());
        assertFalse(joe.getIsWalkingToLeft());
        assertFalse(joe.getIsRaisingStopSign());
        assertFalse(joe.getIsPassSign());
    }

    @Test
    public void testStopWalking() {
        joe.startWalkingToLeft();
        joe.stopWalking();
        assertFalse(joe.getIsWalkingState());
        assertFalse(joe.getIsRaisingStopSign());
        assertFalse(joe.getIsPassSign());
        assertTrue(joe.isFirstHalfOfMovement());
    }

    @Test
    public void testStartRaisingStopSign() {
        joe.startRaisingStopSign();
        assertTrue(joe.getIsRaisingStopSign());
        assertFalse(joe.getIsWalkingState());
        assertFalse(joe.getIsPassSign());
        assertTrue(joe.isFirstHalfOfMovement());
    }

    @Test
    public void testStartRaisingPassSign() {
        joe.startRaisingPassSign();
        assertTrue(joe.getIsPassSign());
        assertFalse(joe.getIsWalkingState());
        assertFalse(joe.getIsRaisingStopSign());
        assertTrue(joe.isFirstHalfOfMovement());
    }

    @Test
    public void testIsNotHit() {
        joe.isHitLeft();
        joe.isNotHit();
        assertFalse(joe.getIsHit());
    }

    @Test
    public void testIsHitLeft() {
        joe.isHitLeft();
        assertTrue(joe.getIsHit());
        assertTrue(joe.getHitLeft());
    }

    @Test
    public void testIsHitRight() {
        joe.isHitRight();
        assertTrue(joe.getIsHit());
        assertFalse(joe.getHitLeft());
    }

    @Test
    public void testCountHitPoints() {
        joe.addScore(10);
        joe.countHitPoints();
        assertEquals(8, joe.getScore());
    }

    @Test
    public void testCountHitPointsScoreGreaterThanZero() {
        when(joe.getScore()).thenReturn(5);

        joe.countHitPoints();

        verify(joe).addScore(-2);
    }

    @Test
    public void testCountHitPointsScoreZeroOrLess() {
        when(joe.getScore()).thenReturn(0);

        joe.countHitPoints();

        verify(joe, never()).addScore(-2);
    }

    @Test
    void testIsFirstHalfOfMovement() {
        assertTrue(joe.isFirstHalfOfMovement());

        joe.setFirstHalfOfMovement(false);
        assertFalse(joe.isFirstHalfOfMovement());

        joe.setFirstHalfOfMovement(true);
        assertTrue(joe.isFirstHalfOfMovement());
    }
}