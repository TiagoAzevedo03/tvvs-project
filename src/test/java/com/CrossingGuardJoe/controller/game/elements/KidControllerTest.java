package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import com.CrossingGuardJoe.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;

class KidControllerTest {

    private KidController kidController;
    private Kid kid;
    private Car car;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Road road = mock(Road.class);
        mock(Game.class);
        Joe joe = mock(Joe.class);
        kid = mock(Kid.class);
        car = mock(Car.class);
        kidController = new KidController(road);

        when(road.getJoe()).thenReturn(joe);
        when(road.getKids()).thenReturn(new ArrayList<>(Collections.singletonList(kid)));
        when(road.getCars()).thenReturn(new ArrayList<>(Collections.singletonList(car)));

        Field lastUpdateTimeField = KidController.class.getDeclaredField("lastUpdateTime");
        lastUpdateTimeField.setAccessible(true);
        lastUpdateTimeField.set(kidController, System.currentTimeMillis() - 2);
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
    void testCheckCountToNextLevel() {
        when(kid.getPass()).thenReturn(true);
        when(kid.getCounted()).thenReturn(false);

        kidController.checkCountToNextLevel();

        verify(kid).setCountToNextLevel();
    }
}
