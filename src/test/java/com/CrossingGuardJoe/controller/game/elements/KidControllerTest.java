package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KidControllerTest {

    private KidController kidController;
    private Kid kid;
    private Car car;
    private Joe joe;
    private Road road;

    @BeforeEach
    void setUp() {
        road = mock(Road.class);
        joe = mock(Joe.class);
        kid = mock(Kid.class);
        car = mock(Car.class);
        kidController = new KidController(road);

        when(road.getJoe()).thenReturn(joe);
        when(road.getKids()).thenReturn(new ArrayList<>(Collections.singletonList(kid)));
        when(road.getCars()).thenReturn(new ArrayList<>(Collections.singletonList(car)));
    }

    @Test
    void testMoveKid() {
        Position initialPosition = new Position(100, 50);
        Position expectedPosition = new Position(97, 50);

        when(kid.getPosition()).thenReturn(initialPosition);

        kidController.moveKid(kid);

        verify(kid).setWalking();
        verify(kid).setPosition(expectedPosition);
    }

    @Test
    void testMoveKidAfterHit() {
        Position carPosition = new Position(100, 50);
        Position expectedPosition = new Position(90, 105);

        when(car.getPosition()).thenReturn(carPosition);
        when(kid.getPosition()).thenReturn(new Position(90, 50));

        kidController.moveKidAfterHit(car, kid, 90);

        verify(kid).setPosition(expectedPosition);
    }

    @Test
    void testStopKid() {
        kidController.stopKid(kid);

        verify(kid).setNotWalking();
    }

    @Test
    void testKidAction() {
        Position position = new Position(100, 50);

        kidController.KidAction(kid, position, 'p');
        verify(kid).setWalking();
        verify(kid).setPosition(position);

        kidController.KidAction(kid, position, 's');
        verify(kid).setNotWalking();
    }

    @Test
    void testIsFirstKid() {
        assertTrue(kidController.isFirstKid(kid));
    }

    @Test
    void testInMinDistance() {
        Kid kidInFront = mock(Kid.class);
        when(kidInFront.getPosition()).thenReturn(new Position(90, 50));
        when(kid.getPosition()).thenReturn(new Position(100, 50));
        when(kidInFront.getIsHit()).thenReturn(false);

        List<Kid> kids = new ArrayList<>();
        kids.add(kidInFront);
        kids.add(kid);
        when(road.getKids()).thenReturn(kids);

        assertTrue(kidController.inMinDistance(kid));
    }

    @Test
    void testCanContinueWalk() {
        Kid kidInFront = mock(Kid.class);
        when(kidInFront.getPosition()).thenReturn(new Position(90, 50));
        when(kid.getPosition()).thenReturn(new Position(100, 50));
        when(kidInFront.getIsHit()).thenReturn(false);

        List<Kid> kids = new ArrayList<>();
        kids.add(kidInFront);
        kids.add(kid);
        when(road.getKids()).thenReturn(kids);

        assertFalse(kidController.canContinueWalk(kid));
    }

    @Test
    void testCheckPoints() {
        when(kid.getPosition()).thenReturn(new Position(0, 0));
        when(kid.getPass()).thenReturn(false);

        kidController.checkPoints();
    }

    @Test
    void testCheckCountToNextLevel() {
        when(kid.getPass()).thenReturn(true);
        when(kid.getCounted()).thenReturn(false);

        kidController.checkCountToNextLevel();

        verify(kid).setCountToNextLevel();
    }

    @Test
    void testCheckLevelUp() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(road.getKids()).thenReturn(new ArrayList<>(Collections.singletonList(kid)));
        when(kid.getCounted()).thenReturn(true);

        Method checkCollisionsMethod = KidController.class.getDeclaredMethod("checkLevelUp");
        checkCollisionsMethod.setAccessible(true);
        checkCollisionsMethod.invoke(kidController);
    }
}