package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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
    private Kid kid2;
    private Road road;
    private SoundsController soundsController;
    private Game game;

    @BeforeEach
    void setUp() throws Exception {
        game = mock(Game.class);
        road = mock(Road.class);
        joe = mock(Joe.class);
        kid = mock(Kid.class);
        kid2 = mock(Kid.class);
        car = mock(Car.class);
        kidController = spy(new KidController(road));
        soundsController = mock(SoundsController.class);

        Field instanceField = SoundsController.class.getDeclaredField("soundsController");
        instanceField.setAccessible(true);
        instanceField.set(null, soundsController);

        Field joeField = KidController.class.getDeclaredField("joe");
        joeField.setAccessible(true);
        joeField.set(kidController, joe);

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
    void testNextLevelNumberKids() {
        for (int level = 2; level <= 10; level++){
            int expectedNumberOfKids = 2 + level;
            int actualNumberOfKids = kidController.nextLevelNumberKids(level);
            assertEquals(expectedNumberOfKids, actualNumberOfKids);
        }
    }

    @Test
    void testRepositionQueueWithMovesLeft() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        kids.add(kid2);

        Position position = mock(Position.class);
        when(kid.getPosition()).thenReturn(position);
        when(kid2.getPosition()).thenReturn(position);

        when(road.getKids()).thenReturn(kids);
        when(kid.getMovesInQueueLeft()).thenReturn(2);
        when(kid2.getMovesInQueueLeft()).thenReturn(0);
        doReturn(true).when(kidController).canContinueWalk(kid);

        kidController.repositionQueue();

        verify(kidController).moveKid(kid);
        verify(kid).addMovesInQueueLeft(-1);
    }

    @Test
    void testRepositionQueueNoMovesLeft() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        kids.add(kid2);

        when(road.getKids()).thenReturn(kids);
        when(kid.getMovesInQueueLeft()).thenReturn(0);
        when(kid2.getMovesInQueueLeft()).thenReturn(0);

        kidController.repositionQueue();

        verify(kid).setNotWalking();
        verify(kid2).setNotWalking();
    }

    @Test
    void testCheckDeathCount() throws Exception {
        when(kid.getDeathCounted()).thenReturn(false);

        Method checkDeathCountMethod = KidController.class.getDeclaredMethod("checkDeathCount", Kid.class);
        checkDeathCountMethod.setAccessible(true);
        checkDeathCountMethod.invoke(kidController, kid);

        verify(soundsController).play(Sounds.SFX.CARBREAK);
        verify(soundsController).play(Sounds.SFX.KIDHIT);
        verify(joe).removeHeart();
        verify(kid).setDead();
    }

    @Test
    void testCheckCollisions() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);

        Position carPosition = mock(Position.class);
        Position kidPosition = mock(Position.class);

        when(road.getCars()).thenReturn(cars);
        when(road.getKids()).thenReturn(kids);
        when(car.getPosition()).thenReturn(carPosition);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getDeathCounted()).thenReturn(false);

        Method checkCollisionsMethod = KidController.class.getDeclaredMethod("checkCollisions");
        checkCollisionsMethod.setAccessible(true);
        checkCollisionsMethod.invoke(kidController);

        verify(kid).isHit();
        verify(kid).setNotWalking();
        verify(kidController).moveKidAfterHit(car, kid, 0);
    }

    @Test
    void testNextActionDown() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);
        Position carPosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(false);
        when(joe.getPosition()).thenReturn(joePosition);
        when(car.getPosition()).thenReturn(carPosition);

        kidController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());
    }

    @Test
    void testNextActionUp() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);
        Position carPosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(true);
        when(joe.getPosition()).thenReturn(joePosition);
        when(car.getPosition()).thenReturn(carPosition);

        kidController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

        verify(kid).setNotWalking();
    }

    @Test
    void testNextActionTimeGreaterThanKidSpeed() throws Exception {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);
        Position carPosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(true);
        when(joe.getPosition()).thenReturn(joePosition);
        when(car.getPosition()).thenReturn(carPosition);

        long currentTime = System.currentTimeMillis();

        Field lastUpdateTimeField = KidController.class.getDeclaredField("lastUpdateTime");
        lastUpdateTimeField.setAccessible(true);
        lastUpdateTimeField.set(kidController, currentTime - 1000);

        kidController.nextAction(game, GUI.ACTION.NONE, currentTime);

        verify(kidController).repositionQueue();
        verify(kidController).checkPoints();
        verify(kidController).checkCountToNextLevel();
    }

}