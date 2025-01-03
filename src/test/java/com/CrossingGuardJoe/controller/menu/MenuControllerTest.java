package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.Menu;
import com.CrossingGuardJoe.states.GameState;
import com.CrossingGuardJoe.states.menu.CustomizeMenuState;
import com.CrossingGuardJoe.states.menu.InstructionsMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    private MenuController menuController;
    private Menu menu;
    private Game game;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        game = mock(Game.class);
        menuController = new MenuController(menu);
    }

    @Test
    void testNextActionUp() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            menuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

            verify(menu).navigateUp();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionDown() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            menuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(menu).navigateDown();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionSelectStartGame() throws IOException {
        when(menu.isSelectedStartGame()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            menuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game).setState(any(GameState.class));
            verify(soundsControllerMock).stop(Sounds.SFX.MENUBGM);
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
        }
    }

    @Test
    void testNextActionSelectInstructions() throws IOException {
        when(menu.isSelectedInstructions()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            menuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game).setState(any(InstructionsMenuState.class));
            verify(soundsControllerMock).stop(Sounds.SFX.MENUBGM);
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
        }
    }

    @Test
    void testNextActionSelectCustomize() throws IOException {
        when(menu.isSelectedCustomize()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            menuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(game).setState(any(CustomizeMenuState.class));
            verify(soundsControllerMock).stop(Sounds.SFX.MENUBGM);
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
        }
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        menuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verifyNoInteractions(menu);
        verifyNoInteractions(game);
    }
}