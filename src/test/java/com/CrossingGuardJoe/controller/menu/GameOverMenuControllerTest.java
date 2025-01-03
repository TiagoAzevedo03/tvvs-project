package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.menu.GameOverMenu;
import com.CrossingGuardJoe.states.menu.StatsMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameOverMenuControllerTest {

    private GameOverMenuController gameOverMenuController;
    private GameOverMenu gameOverMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        gameOverMenu = mock(GameOverMenu.class);
        game = mock(Game.class);
        gameOverMenuController = new GameOverMenuController(gameOverMenu);
    }

    @Test
    void testNextActionUp() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            gameOverMenuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

            verify(gameOverMenu).navigateUp();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionDown() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            gameOverMenuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(gameOverMenu).navigateDown();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionSelectExit() throws IOException {
        when(gameOverMenu.isSelectedExit()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            gameOverMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game).popState();
            verify(soundsControllerMock).stop(Sounds.SFX.VICTORYBGM);
            verify(soundsControllerMock).play(Sounds.SFX.MENUBGM);
        }
    }

    @Test
    void testNextActionSelectStats() throws IOException {
        when(gameOverMenu.isSelectedStats()).thenReturn(true);
        when(gameOverMenu.isSelectedExit()).thenReturn(false);

        Road mockCurrentGame = mock(Road.class);
        Joe mockJoe = mock(Joe.class);
        when(mockCurrentGame.getJoe()).thenReturn(mockJoe);
        when(mockJoe.getScore()).thenReturn(100);
        when(mockCurrentGame.getCurrentLevel()).thenReturn(1);
        when(gameOverMenu.getCurrentGame()).thenReturn(mockCurrentGame);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            gameOverMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game).setState(any(StatsMenuState.class));
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
        }
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        gameOverMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verifyNoInteractions(gameOverMenu);
        verifyNoInteractions(game);
    }
}