package com.CrossingGuardJoe.controller.game;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.game.elements.CarController;
import com.CrossingGuardJoe.controller.game.elements.JoeController;
import com.CrossingGuardJoe.controller.game.elements.KidController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.menu.GameOverMenu;
import com.CrossingGuardJoe.model.menu.PauseMenu;
import com.CrossingGuardJoe.states.menu.GameOverState;
import com.CrossingGuardJoe.states.menu.PauseMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        roadController.nextAction(game, GUI.ACTION.ESC, currentTime);

        verify(joeController).nextAction(game, GUI.ACTION.ESC, currentTime);
        verify(kidController).nextAction(game, GUI.ACTION.ESC, currentTime);
        verify(carController).nextAction(game, GUI.ACTION.ESC, currentTime);
        verify(joe).stopWalking();
        verify(game).setState(any(PauseMenuState.class));
    }

    @Test
    void testNextActionGameOver() throws IOException {
        long currentTime = System.currentTimeMillis();
        Joe joe = road.getJoe();
        when(joe.getHearts()).thenReturn(0);

        roadController.nextAction(game, GUI.ACTION.NONE, currentTime);

        verify(joeController).nextAction(game, GUI.ACTION.NONE, currentTime);
        verify(kidController).nextAction(game, GUI.ACTION.NONE, currentTime);
        verify(carController).nextAction(game, GUI.ACTION.NONE, currentTime);
        verify(game).popState();
        verify(game).setState(any(GameOverState.class));
    }
}