package com.CrossingGuardJoe.controller.game;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.controller.game.elements.CarController;
import com.CrossingGuardJoe.controller.game.elements.JoeController;
import com.CrossingGuardJoe.controller.game.elements.KidController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.states.menu.GameOverState;
import com.CrossingGuardJoe.states.menu.PauseMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

class RoadControllerTest {

    private RoadController roadController;
    private Road road;
    private Game game;
    private JoeController joeController;
    private KidController kidController;
    private CarController carController;

    @BeforeEach
    void setUp() throws Exception {
        road = mock(Road.class);
        game = mock(Game.class);
        joeController = mock(JoeController.class);
        kidController = mock(KidController.class);
        carController = mock(CarController.class);

        Joe joe = mock(Joe.class);
        Position joePosition = mock(Position.class);
        when(joe.getPosition()).thenReturn(joePosition);
        when(road.getJoe()).thenReturn(joe);

        roadController = new RoadController(road);

        Field joeControllerField = RoadController.class.getDeclaredField("joeController");
        joeControllerField.setAccessible(true);
        joeControllerField.set(roadController, joeController);

        Field kidControllerField = RoadController.class.getDeclaredField("kidController");
        kidControllerField.setAccessible(true);
        kidControllerField.set(roadController, kidController);

        Field carControllerField = RoadController.class.getDeclaredField("carController");
        carControllerField.setAccessible(true);
        carControllerField.set(roadController, carController);
    }

    @Test
    void testNextActionLeft() throws IOException {
        long currentTime = System.currentTimeMillis();
        roadController.nextAction(game, GUI.ACTION.LEFT, currentTime);

        verify(joeController).nextAction(game, GUI.ACTION.LEFT, currentTime);
        verify(kidController).nextAction(game, GUI.ACTION.LEFT, currentTime);
        verify(carController).nextAction(game, GUI.ACTION.LEFT, currentTime);
    }

    @Test
    void testNextActionRight() throws IOException {
        long currentTime = System.currentTimeMillis();
        roadController.nextAction(game, GUI.ACTION.RIGHT, currentTime);

        verify(joeController).nextAction(game, GUI.ACTION.RIGHT, currentTime);
        verify(kidController).nextAction(game, GUI.ACTION.RIGHT, currentTime);
        verify(carController).nextAction(game, GUI.ACTION.RIGHT, currentTime);
    }

    @Test
    void testNextActionUp() throws IOException {
        long currentTime = System.currentTimeMillis();
        roadController.nextAction(game, GUI.ACTION.UP, currentTime);

        verify(joeController).nextAction(game, GUI.ACTION.UP, currentTime);
        verify(kidController).nextAction(game, GUI.ACTION.UP, currentTime);
        verify(carController).nextAction(game, GUI.ACTION.UP, currentTime);
    }

    @Test
    void testNextActionDown() throws IOException {
        long currentTime = System.currentTimeMillis();
        roadController.nextAction(game, GUI.ACTION.DOWN, currentTime);

        verify(joeController).nextAction(game, GUI.ACTION.DOWN, currentTime);
        verify(kidController).nextAction(game, GUI.ACTION.DOWN, currentTime);
        verify(carController).nextAction(game, GUI.ACTION.DOWN, currentTime);
    }

    @Test
    void testNextActionEsc() throws IOException {
        long currentTime = System.currentTimeMillis();
        Joe joe = road.getJoe();
        when(joe.getHearts()).thenReturn(1);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            roadController.nextAction(game, GUI.ACTION.ESC, currentTime);

            verify(joeController).nextAction(game, GUI.ACTION.ESC, currentTime);
            verify(kidController).nextAction(game, GUI.ACTION.ESC, currentTime);
            verify(carController).nextAction(game, GUI.ACTION.ESC, currentTime);
            verify(joe).stopWalking();
            verify(game).setState(any(PauseMenuState.class));
            verify(soundsControllerMock).pause(Sounds.SFX.GAMEBGM);
        }
    }

    @Test
    void testNextActionGameOver() throws IOException {
        long currentTime = System.currentTimeMillis();
        Joe joe = road.getJoe();
        when(joe.getHearts()).thenReturn(0);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            roadController.nextAction(game, GUI.ACTION.NONE, currentTime);

            verify(joeController).nextAction(game, GUI.ACTION.NONE, currentTime);
            verify(kidController).nextAction(game, GUI.ACTION.NONE, currentTime);
            verify(carController).nextAction(game, GUI.ACTION.NONE, currentTime);
            verify(game).popState();
            verify(game).setState(any(GameOverState.class));
            verify(soundsControllerMock).stop(Sounds.SFX.GAMEBGM);
            verify(soundsControllerMock).stop(Sounds.SFX.CARBREAK);
            verify(soundsControllerMock).play(Sounds.SFX.GAMEOVER);
        }
    }

    @Test
    void testNextActionUpdateHighestScoreAndLevel() throws IOException {
        long currentTime = System.currentTimeMillis();
        Joe joe = road.getJoe();

        when(joe.getScore()).thenReturn(200);
        when(game.getHighestScore()).thenReturn(100);
        when(road.getCurrentLevel()).thenReturn(5);
        when(game.getHighestLevel()).thenReturn(3);
        roadController.nextAction(game, GUI.ACTION.NONE, currentTime);
        verify(game).setHighestScore(200);
        verify(game).setHighestLevel(5);

        reset(game);

        when(joe.getScore()).thenReturn(100);
        when(game.getHighestScore()).thenReturn(100);
        when(road.getCurrentLevel()).thenReturn(3);
        when(game.getHighestLevel()).thenReturn(3);
        roadController.nextAction(game, GUI.ACTION.NONE, currentTime);
        verify(game, never()).setHighestScore(anyInt());
        verify(game, never()).setHighestLevel(anyInt());

        reset(game);

        when(joe.getScore()).thenReturn(50);
        when(game.getHighestScore()).thenReturn(100);
        when(road.getCurrentLevel()).thenReturn(2);
        when(game.getHighestLevel()).thenReturn(3);
        roadController.nextAction(game, GUI.ACTION.NONE, currentTime);
        verify(game, never()).setHighestScore(anyInt());
        verify(game, never()).setHighestLevel(anyInt());
    }

    @Test
    void testNextActionGameEnded() throws IOException {
        long currentTime = System.currentTimeMillis();
        when(road.isGameEnded()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            roadController.nextAction(game, GUI.ACTION.NONE, currentTime);

            verify(game, times(2)).popState();
            verify(game, times(2)).setState(any(GameOverState.class));
            verify(soundsControllerMock, times(2)).stop(Sounds.SFX.GAMEBGM);
            verify(soundsControllerMock).play(Sounds.SFX.VICTORYBGM);
        }
    }
}