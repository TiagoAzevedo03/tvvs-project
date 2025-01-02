package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.controller.game.AuxCheckRange;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

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
    void testKidActionPass() {
        Position position = new Position(100, 50);

        kidController.KidAction(kid, position, 'p');
        verify(kid).setWalking();
        verify(kid).setPosition(position);
    }

    @Test
    void testKidActionStop() {
        Position position = new Position(100, 50);

        kidController.KidAction(kid, position, 's');
        verify(kid).setNotWalking();
        assertFalse(kid.getWalkingState());
    }

    @Test
    void testKidActionInvalid() {
        Position position = new Position(100, 50);

        kidController.KidAction(kid, position, 'x');
        verify(kid, never()).setWalking();
        verify(kid, never()).setNotWalking();
    }

    @Test
    void testIsFirstKid() {
        assertTrue(kidController.isFirstKid(kid));
    }

    @Test
    void testInMinDistanceFirstKid() {
        when(road.getKids()).thenReturn(Collections.singletonList(kid));
        assertFalse(kidController.inMinDistance(kid));
    }

    @Test
    void testInMinDistanceGreaterThanMinDistance() {
        Kid kidInFront = mock(Kid.class);
        when(kidInFront.getPosition()).thenReturn(new Position(80, 50));
        when(kid.getPosition()).thenReturn(new Position(100, 50));
        when(kidInFront.getIsHit()).thenReturn(false);

        List<Kid> kids = new ArrayList<>();
        kids.add(kidInFront);
        kids.add(kid);
        when(road.getKids()).thenReturn(kids);

        assertFalse(kidController.inMinDistance(kid));
    }

    @Test
    void testInMinDistanceLessThanOrEqualToMinDistance() {
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
    void testInMinDistanceKidInFrontIsHit() {
        Kid kidInFront = mock(Kid.class);
        when(kidInFront.getPosition()).thenReturn(new Position(90, 50));
        when(kid.getPosition()).thenReturn(new Position(100, 50));
        when(kidInFront.getIsHit()).thenReturn(true);

        List<Kid> kids = new ArrayList<>();
        kids.add(kidInFront);
        kids.add(kid);
        when(road.getKids()).thenReturn(kids);

        assertFalse(kidController.inMinDistance(kid));
    }

    @Test
    void testCanContinueWalkFirstKid() {
        when(road.getKids()).thenReturn(Collections.singletonList(kid));
        assertTrue(kidController.canContinueWalk(kid));
    }

    @Test
    void testCanContinueWalkGreaterThanMinDistance() {
        Kid kidInFront = mock(Kid.class);
        when(kidInFront.getPosition()).thenReturn(new Position(80, 50));
        when(kid.getPosition()).thenReturn(new Position(100, 50));
        when(kidInFront.getIsHit()).thenReturn(false);

        List<Kid> kids = new ArrayList<>();
        kids.add(kidInFront);
        kids.add(kid);
        when(road.getKids()).thenReturn(kids);

        assertTrue(kidController.canContinueWalk(kid));
    }

    @Test
    void testCanContinueWalkLessThanOrEqualToMinDistance() {
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
    void testCanContinueWalkKidInFrontIsHit() {
        Kid kidInFront = mock(Kid.class);
        when(kidInFront.getPosition()).thenReturn(new Position(90, 50));
        when(kid.getPosition()).thenReturn(new Position(100, 50));
        when(kidInFront.getIsHit()).thenReturn(true);

        List<Kid> kids = new ArrayList<>();
        kids.add(kidInFront);
        kids.add(kid);
        when(road.getKids()).thenReturn(kids);

        assertTrue(kidController.canContinueWalk(kid));
    }
    @Test
    void testCheckPoints() {
        when(kid.getPosition()).thenReturn(new Position(-1, 0));
        when(kid.getPass()).thenReturn(false);

        kidController.checkPoints();
    }

    @Test
    void testCheckPointsKidPassed() {
        when(kid.getPosition()).thenReturn(new Position(-1, 0));
        when(kid.getPass()).thenReturn(true);

        kidController.checkPoints();

        verify(kid, never()).setPass();
        verify(joe, never()).addScore(anyInt());
        verify(soundsController, never()).play(any(Sounds.SFX.class));
    }

    @Test
    void testCheckPointsKidNotPassedAndXNonNegative() {
        when(kid.getPosition()).thenReturn(new Position(0, 0));
        when(kid.getPass()).thenReturn(false);

        kidController.checkPoints();

        verify(kid, never()).setPass();
        verify(joe, never()).addScore(anyInt());
        verify(soundsController, never()).play(any(Sounds.SFX.class));
    }

    @Test
    void testCheckPointsKidPassedAndXNonNegative() {
        when(kid.getPosition()).thenReturn(new Position(0, 0));
        when(kid.getPass()).thenReturn(true);

        kidController.checkPoints();

        verify(kid, never()).setPass();
        verify(joe, never()).addScore(anyInt());
        verify(soundsController, never()).play(any(Sounds.SFX.class));
    }

    @Test
    void testCheckCountToNextLevelKidPassed() {
        when(kid.getPass()).thenReturn(true);
        when(kid.getCounted()).thenReturn(false);

        kidController.checkCountToNextLevel();

        verify(kid).setCountToNextLevel();
    }

    @Test
    void testCheckCountToNextLevelKidDeadAndBeyondMaxY() {
        when(kid.getPass()).thenReturn(false);
        when(kid.getCounted()).thenReturn(false);
        when(kid.getDeathCounted()).thenReturn(true);
        when(kid.getPosition()).thenReturn(new Position(0, 1000));

        kidController.checkCountToNextLevel();

        verify(kid).setCountToNextLevel();
    }

    @Test
    void testCheckCountToNextLevelKidNotPassedAndNotDead() {
        when(kid.getPass()).thenReturn(false);
        when(kid.getCounted()).thenReturn(false);
        when(kid.getDeathCounted()).thenReturn(false);

        kidController.checkCountToNextLevel();

        verify(kid, never()).setCountToNextLevel();
    }

    @Test
    void testKidGetCountedFalse() {
        when(kid.getCounted()).thenReturn(true);

        kidController.checkCountToNextLevel();

        verify(kid, never()).setCountToNextLevel();
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
    void testCheckDeathCountAlreadyCounted() throws Exception {
        when(kid.getDeathCounted()).thenReturn(true);

        Method checkDeathCountMethod = KidController.class.getDeclaredMethod("checkDeathCount", Kid.class);
        checkDeathCountMethod.setAccessible(true);
        checkDeathCountMethod.invoke(kidController, kid);

        verify(soundsController, never()).play(Sounds.SFX.CARBREAK);
        verify(soundsController, never()).play(Sounds.SFX.KIDHIT);
        verify(joe, never()).removeHeart();
        verify(kid, never()).setDead();
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
        when(kidPosition.getX()).thenReturn(91);
        when(kid.getWalkingState()).thenReturn(false);
        when(joe.getPosition()).thenReturn(joePosition);
        when(car.getPosition()).thenReturn(carPosition);
        when(kidController.canContinueWalk(kid)).thenReturn(true);


        kidController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(kidController).stopKid(any(Kid.class));
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
    void testNextActionKidInRangePositionLessThanOrEqualToPassPoint() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kidPosition.getX()).thenReturn(90);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

            verify(kid).setNotSelected();
        }
    }

    @Test
    void testNextActionKidInRangePositionGreaterThanPassPoint() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kidPosition.getX()).thenReturn(90 + 1);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

            verify(kid).setNotSelected();
        }
    }

    @Test
    void testNextActionKidNotInRangePositionGreaterThanPassPoint() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kidPosition.getX()).thenReturn(90 + 1);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(false);

            kidController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

            verify(kid).setNotSelected();
        }
    }

    @Test
    void testNextActionKidNotInRangePositionLessThanOrEqualToPassPoint() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kidPosition.getX()).thenReturn(90);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(false);

            kidController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

            verify(kid).setNotSelected();
        }
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

    @Test
    void testCheckLevelUp() throws Exception {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        kids.add(kid2);

        when(road.getKids()).thenReturn(kids);
        Field countKidsToNextLevelField = KidController.class.getDeclaredField("countKidsToNextLevel");
        countKidsToNextLevelField.setAccessible(true);
        countKidsToNextLevelField.set(kidController, 2);

        Method checkLevelUpMethod = KidController.class.getDeclaredMethod("checkLevelUp");
        checkLevelUpMethod.setAccessible(true);
        checkLevelUpMethod.invoke(kidController);

        assertEquals(0, countKidsToNextLevelField.get(kidController));
        verify(road).levelUp();
        verify(soundsController).play(Sounds.SFX.LEVELUP);
        verify(road).setKidsNextLevel(anyInt());
        Field nextKidToMoveInQueueIndexField = KidController.class.getDeclaredField("nextKidToMoveInQueueIndex");
        nextKidToMoveInQueueIndexField.setAccessible(true);
        assertEquals(0, nextKidToMoveInQueueIndexField.get(kidController));
    }

    @Test
    void testCheckLevelUpNotReady() throws Exception {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        kids.add(kid2);

        when(road.getKids()).thenReturn(kids);
        Field countKidsToNextLevelField = KidController.class.getDeclaredField("countKidsToNextLevel");
        countKidsToNextLevelField.setAccessible(true);
        countKidsToNextLevelField.set(kidController, 1);

        Method checkLevelUpMethod = KidController.class.getDeclaredMethod("checkLevelUp");
        checkLevelUpMethod.setAccessible(true);
        checkLevelUpMethod.invoke(kidController);

        assertEquals(1, countKidsToNextLevelField.get(kidController));
        verify(road, never()).levelUp();
        verify(soundsController, never()).play(Sounds.SFX.LEVELUP);
        verify(road, never()).setKidsNextLevel(anyInt());
        Field nextKidToMoveInQueueIndexField = KidController.class.getDeclaredField("nextKidToMoveInQueueIndex");
        nextKidToMoveInQueueIndexField.setAccessible(true);
    }

    @Test
    void testNextActionUpJoeInRangeAndWalking() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(true);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());
        }
    }

    @Test
    void testNextActionUpJoeInRangeAndNotWalking() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(false);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());
        }
    }

    @Test
    void testNextActionUpJoeNotInRangeAndWalking() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(true);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(false);

            kidController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());
        }
    }

    @Test
    void testNextActionDownJoeInRangeNotHitCanContinueWalk() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(91);
        when(kid.getWalkingState()).thenReturn(false);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);
        when(kidController.canContinueWalk(kid)).thenReturn(true);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(kid).setWalking();
            verify(soundsController).play(Sounds.SFX.KIDWALK1);
        }
    }

    @Test
    void testNextActionDownJoeInRangeHitCanContinueWalk() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(true);
        when(kidPosition.getX()).thenReturn(91);
        when(kid.getWalkingState()).thenReturn(false);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);
        when(kidController.canContinueWalk(kid)).thenReturn(true);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(kid, never()).setWalking();
            verify(soundsController, never()).play(Sounds.SFX.KIDWALK1);
            verify(kid, never()).addMovesInQueueLeft(3);
        }
    }

    @Test
    void testNextActionDownJoeInRangeNotHitCannotContinueWalk() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(91);
        when(kid.getWalkingState()).thenReturn(false);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);
        when(kidController.canContinueWalk(kid)).thenReturn(false);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(kid, never()).setWalking();
            verify(soundsController, never()).play(Sounds.SFX.KIDWALK1);
            verify(kid, never()).addMovesInQueueLeft(3);
        }
    }

    @Test
    void testNextActionDownJoeNotInRangeNotHitCanContinueWalk() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(91);
        when(kid.getWalkingState()).thenReturn(false);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joePosition.getX()).thenReturn(50);
        when(kidController.canContinueWalk(kid)).thenReturn(true);

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(false);

            kidController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(kid, never()).setWalking();
            verify(soundsController, never()).play(Sounds.SFX.KIDWALK1);
            verify(kid, never()).addMovesInQueueLeft(3);
        }
    }

    @Test
    void testNextActionTimeGreaterThanKidSpeedAndKidsNotEmpty() throws Exception {
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
        when(carPosition.getX()).thenReturn(100);

        long currentTime = System.currentTimeMillis();

        Field lastUpdateTimeField = KidController.class.getDeclaredField("lastUpdateTime");
        lastUpdateTimeField.setAccessible(true);
        lastUpdateTimeField.set(kidController, currentTime - 1000);

        kidController.nextAction(game, GUI.ACTION.NONE, currentTime);

        verify(kidController).repositionQueue();
        verify(kidController).checkPoints();
        verify(kidController).checkCountToNextLevel();

        Method checkLevelUpMethod = KidController.class.getDeclaredMethod("checkLevelUp");
        checkLevelUpMethod.setAccessible(true);
        checkLevelUpMethod.invoke(kidController);
    }

    @Test
    void testNextActionTimeLessThanOrEqualToKidSpeed() throws Exception {
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
        when(carPosition.getX()).thenReturn(100);

        long currentTime = System.currentTimeMillis();

        Field lastUpdateTimeField = KidController.class.getDeclaredField("lastUpdateTime");
        lastUpdateTimeField.setAccessible(true);
        lastUpdateTimeField.set(kidController, currentTime);

        kidController.nextAction(game, GUI.ACTION.NONE, currentTime);

        verify(kidController, never()).repositionQueue();

        Method checkLevelUpMethod = KidController.class.getDeclaredMethod("checkLevelUp");
        checkLevelUpMethod.setAccessible(true);
        checkLevelUpMethod.invoke(kidController);
    }

    @Test
    void testNextActionKidsListEmpty() throws Exception {
        List<Kid> kids = new ArrayList<>();

        when(road.getKids()).thenReturn(kids);

        long currentTime = System.currentTimeMillis();

        Field lastUpdateTimeField = KidController.class.getDeclaredField("lastUpdateTime");
        lastUpdateTimeField.setAccessible(true);
        lastUpdateTimeField.set(kidController, currentTime - 1000);

        kidController.nextAction(game, GUI.ACTION.NONE, currentTime);

        verify(kidController, never()).repositionQueue();

        Method checkLevelUpMethod = KidController.class.getDeclaredMethod("checkLevelUp");
        checkLevelUpMethod.setAccessible(true);
        checkLevelUpMethod.invoke(kidController);
    }

    @Test
    void testNextActionKidWalkingAndCanContinueWalk() {
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
        when(carPosition.getX()).thenReturn(100);
        when(kidController.canContinueWalk(kid)).thenReturn(true);

        long currentTime = System.currentTimeMillis();

        kidController.nextAction(game, GUI.ACTION.NONE, currentTime);
    }

    @Test
    void testNextActionKidWalkingAndCannotContinueWalk() {
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
        when(joePosition.getX()).thenReturn(50);
        when(car.getPosition()).thenReturn(carPosition);
        when(carPosition.getX()).thenReturn(100);
        when(kidController.canContinueWalk(kid)).thenReturn(false);

        long currentTime = System.currentTimeMillis();

        kidController.nextAction(game, GUI.ACTION.NONE, currentTime);

        verify(kidController, never()).moveKid(kid);
    }

    @Test
    void testNextActionKidInRangeJoeRaisingStopSignPositionGreaterThanPassPoint() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(true);
        when(joe.getPosition()).thenReturn(joePosition);
        when(joe.getIsRaisingStopSign()).thenReturn(true);

        long currentTime = System.currentTimeMillis();

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(true);

            kidController.nextAction(game, GUI.ACTION.NONE, currentTime);

            verify(kidController).stopKid(kid);
        }
    }

    @Test
    void testNextActionKidNotInRange() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        Position kidPosition = mock(Position.class);
        Position joePosition = mock(Position.class);

        when(road.getKids()).thenReturn(kids);
        when(kid.getPosition()).thenReturn(kidPosition);
        when(kid.getIsHit()).thenReturn(false);
        when(kidPosition.getX()).thenReturn(101);
        when(kid.getWalkingState()).thenReturn(true);
        when(joe.getPosition()).thenReturn(joePosition);

        long currentTime = System.currentTimeMillis();

        try (MockedStatic<AuxCheckRange> utilities = mockStatic(AuxCheckRange.class)) {
            utilities.when(() -> AuxCheckRange.isInRangeJoeKid(joe, kid)).thenReturn(false);

            kidController.nextAction(game, GUI.ACTION.NONE, currentTime);
        }
    }

    @Test
    void testRepositionQueueCannotContinueWalk() {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        kids.add(kid2);

        when(road.getKids()).thenReturn(kids);
        when(kid.getMovesInQueueLeft()).thenReturn(2);
        when(kid2.getMovesInQueueLeft()).thenReturn(0);
        doReturn(false).when(kidController).canContinueWalk(kid);

        kidController.repositionQueue();

        verify(kidController, never()).moveKid(kid);
    }

    @Test
    void testRepositionQueueKidNotMovedInQueue() throws Exception {
        List<Kid> kids = new ArrayList<>();
        kids.add(kid);
        kids.add(kid2);

        Position position = mock(Position.class);
        when(kid.getPosition()).thenReturn(position);
        when(kid2.getPosition()).thenReturn(position);

        when(road.getKids()).thenReturn(kids);
        when(kid.getMovesInQueueLeft()).thenReturn(2);
        when(kid2.getMovesInQueueLeft()).thenReturn(0);

        Field kidMovedInQueueField = KidController.class.getDeclaredField("kidMovedInQueue");
        kidMovedInQueueField.setAccessible(true);
        kidMovedInQueueField.set(kidController, false);

        kidController.repositionQueue();
    }
}