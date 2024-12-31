package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.controller.game.AuxCheckRange;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class JoeControllerTest {

    private JoeController joeController;
    private Joe joe;

    @BeforeEach
    void setUp() {
        Road road = mock(Road.class);
        joe = mock(Joe.class);
        joeController = new JoeController(road);

        when(road.getJoe()).thenReturn(joe);
    }

    @Test
    void testMoveJoeLeft() {
        Position initialPosition = new Position(60, 10);
        Position expectedPosition = new Position(54, 10);

        when(joe.getPosition()).thenReturn(initialPosition);

        joeController.moveJoeLeft();

        verify(joe).setPosition(expectedPosition);
        verify(joe).startWalkingToLeft();
    }

    @Test
    void testMoveJoeRight() {
        Position initialPosition = new Position(60, 10);
        Position expectedPosition = new Position(66, 10);

        when(joe.getPosition()).thenReturn(initialPosition);

        joeController.moveJoeRight();

        verify(joe).setPosition(expectedPosition);
        verify(joe).startWalkingToRight();
    }

    @Test
    void testMoveJoeLeftHit() {
        Position initialPosition = new Position(60, 10);
        Position expectedPosition = new Position(50, 10);

        when(joe.getPosition()).thenReturn(initialPosition);

        joeController.moveJoeLeftHit();

        verify(joe).setPosition(expectedPosition);
        verify(joe).countHitPoints();
    }

    @Test
    void testMoveJoeRightHit() {
        Position initialPosition = new Position(60, 10);
        Position expectedPosition = new Position(70, 10);

        when(joe.getPosition()).thenReturn(initialPosition);

        joeController.moveJoeRightHit();

        verify(joe).setPosition(expectedPosition);
        verify(joe).countHitPoints();
    }

    @Test
    void testJoePassSign() {
        joeController.joePassSign();
        verify(joe).startRaisingPassSign();
    }

    @Test
    void testJoeStopSign() {
        joeController.joeStopSign();
        verify(joe).startRaisingStopSign();
    }

    @Test
    void testJoeAction() {
        Position position = new Position(60, 10);

        joeController.JoeAction(position, 'p');
        verify(joe).startRaisingPassSign();

        joeController.JoeAction(position, 's');
        verify(joe).startRaisingStopSign();

        joeController.JoeAction(position, 'l');
        verify(joe).setPosition(position);
        verify(joe).startWalkingToLeft();
    }

    @Test
    void testJoeNotWalking() {
        joeController.joeNotWalking();
        verify(joe).stopWalking();
    }

    @Test
    void testCanGoThrough() throws Exception {
        Method canGoThroughMethod = JoeController.class.getDeclaredMethod("canGoThrough", Position.class);
        canGoThroughMethod.setAccessible(true);

        Position positionWithinRange = new Position(100, 10);
        Position positionOutOfRange = new Position(500, 10);

        boolean resultWithinRange = (boolean) canGoThroughMethod.invoke(joeController, positionWithinRange);
        boolean resultOutOfRange = (boolean) canGoThroughMethod.invoke(joeController, positionOutOfRange);

        assertTrue(resultWithinRange);
        assertFalse(resultOutOfRange);
    }

    @Test
    void testNextAction() {
        Game game = mock(Game.class);
        Position joePosition = mock(Position.class);
        when(joe.getPosition()).thenReturn(joePosition);

        joeController.nextAction(game, GUI.ACTION.LEFT, 0);

        joeController.nextAction(game, GUI.ACTION.RIGHT, 0);

        joeController.nextAction(game, GUI.ACTION.UP, 0);
        verify(joe).startRaisingStopSign();

        joeController.nextAction(game, GUI.ACTION.DOWN, 0);
        verify(joe).startRaisingPassSign();

        joeController.nextAction(game, GUI.ACTION.NONE, 0);
    }

    @Test
    void testCarInRangeLeftCarJoe() {
        Car car = mock(Car.class);
        Game game = mock(Game.class);

        when(car.getPosition()).thenReturn(new Position(5, 10));

        joeController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verify(joe).stopWalking();
    }

    @Test
    void testCarInRangeRightCarJoe() {
        Car car = mock(Car.class);
        Game game = mock(Game.class);

        when(car.getPosition()).thenReturn(new Position(15, 10));

        joeController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verify(joe).stopWalking();
    }

    @Test
    void testCheckCollisionsCarInRangeLeft() {
        List<Car> cars = new ArrayList<>();
        Car car = mock(Car.class);
        cars.add(car);
        Joe joe = mock(Joe.class);
        Road road = mock(Road.class);

        when(road.getCars()).thenReturn(cars);
        when(road.getJoe()).thenReturn(joe);
        when(car.getPosition()).thenReturn(new Position(5, 10));
        when(joe.getPosition()).thenReturn(new Position(10, 10));

        joeController = spy(new JoeController(road));

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeLeftCarJoe(car, joe)).thenReturn(true);
            utilities.when(() -> AuxCheckRange.isInRangeRightCarJoe(car, joe)).thenReturn(false);

            joeController.checkCollisions();

            verify(joe).isHitLeft();
            verify(joeController).moveJoeLeftHit();
        }
    }

    @Test
    void testCheckCollisionsCarInRangeRight() {
        List<Car> cars = new ArrayList<>();
        Car car = mock(Car.class);
        cars.add(car);
        Joe joe = mock(Joe.class);
        Road road = mock(Road.class);

        when(road.getCars()).thenReturn(cars);
        when(road.getJoe()).thenReturn(joe);
        when(car.getPosition()).thenReturn(new Position(15, 10));
        when(joe.getPosition()).thenReturn(new Position(10, 10));

        joeController = spy(new JoeController(road));

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeLeftCarJoe(car, joe)).thenReturn(false);
            utilities.when(() -> AuxCheckRange.isInRangeRightCarJoe(car, joe)).thenReturn(true);

            joeController.checkCollisions();

            verify(joe).isHitRight();
            verify(joeController).moveJoeRightHit();
        }
    }

    @Test
    void testCheckCollisionsNoCarsInRange() {
        List<Car> cars = new ArrayList<>();
        Car car = mock(Car.class);
        cars.add(car);
        Joe joe = mock(Joe.class);
        Road road = mock(Road.class);

        when(road.getCars()).thenReturn(cars);
        when(road.getJoe()).thenReturn(joe);
        when(car.getPosition()).thenReturn(new Position(20, 10));
        when(joe.getPosition()).thenReturn(new Position(10, 10));

        joeController = spy(new JoeController(road));

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeLeftCarJoe(car, joe)).thenReturn(false);
            utilities.when(() -> AuxCheckRange.isInRangeRightCarJoe(car, joe)).thenReturn(false);

            joeController.checkCollisions();

            verify(joe, never()).isHitLeft();
            verify(joe, never()).isHitRight();
            verify(joeController, never()).moveJoeLeftHit();
            verify(joeController, never()).moveJoeRightHit();
        }
    }
}