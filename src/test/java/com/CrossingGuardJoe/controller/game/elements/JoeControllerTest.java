package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class JoeControllerTest {

    private JoeController joeController;
    private Joe joe;

    @BeforeEach
    void setUp() {
        Road road = mock(Road.class);
        mock(Game.class);
        joe = mock(Joe.class);
        mock(Car.class);
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
    void testJoePassSign() {
        joeController.joePassSign();
        verify(joe).startRaisingPassSign();
    }

    @Test
    void testJoeStopSign() {
        joeController.joeStopSign();
        verify(joe).startRaisingStopSign();
    }
}