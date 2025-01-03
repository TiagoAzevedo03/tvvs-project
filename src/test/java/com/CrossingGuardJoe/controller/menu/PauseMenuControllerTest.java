package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.menu.PauseMenu;
import com.CrossingGuardJoe.states.menu.StatsMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.Mockito.*;

class PauseMenuControllerTest {

    private PauseMenuController pauseMenuController;
    private PauseMenu pauseMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        pauseMenu = mock(PauseMenu.class);
        game = mock(Game.class);
        pauseMenuController = new PauseMenuController(pauseMenu);
    }

    @Test
    void testNextActionUp() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            pauseMenuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

            verify(pauseMenu).navigateUp();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionDown() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            pauseMenuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(pauseMenu).navigateDown();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionSelectResume() throws IOException {
        when(pauseMenu.isSelectedResume()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            pauseMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game).popState();
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
            verify(soundsControllerMock).play(Sounds.SFX.GAMEBGM);
        }
    }

    @Test
    void testNextActionSelectExit() throws IOException {
        when(pauseMenu.isSelectedExit()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            pauseMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game, times(2)).popState();
            verify(soundsControllerMock).stop(Sounds.SFX.GAMEBGM);
            verify(soundsControllerMock).play(Sounds.SFX.MENUBGM);
        }
    }

    @Test
    void testNextActionSelectStats() throws IOException {
        when(pauseMenu.isSelectedStats()).thenReturn(true);
        when(pauseMenu.isSelectedExit()).thenReturn(false);

        Road mockCurrentGame = mock(Road.class);
        Joe mockJoe = mock(Joe.class);
        when(mockCurrentGame.getJoe()).thenReturn(mockJoe);
        when(mockJoe.getScore()).thenReturn(100);
        when(mockCurrentGame.getCurrentLevel()).thenReturn(1);
        when(pauseMenu.getCurrentGame()).thenReturn(mockCurrentGame);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            pauseMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game).setState(any(StatsMenuState.class));
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
        }
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        pauseMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verifyNoInteractions(pauseMenu);
        verifyNoInteractions(game);
    }
}