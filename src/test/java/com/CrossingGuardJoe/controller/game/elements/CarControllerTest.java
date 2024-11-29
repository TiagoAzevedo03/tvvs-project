package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

class CarControllerTest {

    private CarController carController;
    private Road road;
    private Game game;
    private Car car;

    @BeforeEach
    void setUp() {
        road = mock(Road.class);
        game = mock(Game.class);
        car = mock(Car.class);
        carController = new CarController(road);
    }

    @Test
    void testMoveCar() {
        Position initialPosition = new Position(10, 10);
        Position expectedPosition = new Position(10, 25);

        when(car.getPosition()).thenReturn(initialPosition);

        carController.moveCar(car, initialPosition);

        verify(car).setPosition(expectedPosition);
    }

    @Test
    void testNextAction() {
        when(road.getCars()).thenReturn(Collections.singletonList(car));
        Position initialPosition = new Position(10, 10);
        when(car.getPosition()).thenReturn(initialPosition);

        carController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verify(car).setPosition(new Position(10, 25));
    }

    @Test
    void testNextActionWithMultipleCars() {
        Car car2 = mock(Car.class);
        when(road.getCars()).thenReturn(Arrays.asList(car, car2));
        Position initialPosition1 = new Position(10, 10);
        Position initialPosition2 = new Position(20, 20);
        when(car.getPosition()).thenReturn(initialPosition1);
        when(car2.getPosition()).thenReturn(initialPosition2);

        carController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verify(car).setPosition(new Position(10, 25));
        verify(car2).setPosition(new Position(20, 35));
    }
}