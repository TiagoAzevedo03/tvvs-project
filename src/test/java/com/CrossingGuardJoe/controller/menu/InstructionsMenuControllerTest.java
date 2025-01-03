package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.InstructionsMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;

import static org.mockito.Mockito.*;

class InstructionsMenuControllerTest {

    private InstructionsMenuController instructionsMenuController;
    private InstructionsMenu instructionsMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        instructionsMenu = mock(InstructionsMenu.class);
        game = mock(Game.class);
        mock(SoundsController.class);
        instructionsMenuController = new InstructionsMenuController(instructionsMenu);
    }

    @Test
    void testNextActionNavigateRight() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            instructionsMenuController.nextAction(game, GUI.ACTION.RIGHT, System.currentTimeMillis());

            verify(instructionsMenu).navigateRight();
        }
    }

    @Test
    void testNextActionNavigateLeft() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            instructionsMenuController.nextAction(game, GUI.ACTION.LEFT, System.currentTimeMillis());

            verify(instructionsMenu).navigateLeft();
        }
    }

    @Test
    void testNextActionEsc() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            instructionsMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

            verify(game).popState();
            verify(soundsControllerMock).stop(Sounds.SFX.INSTRUCTIONSBGM);
            verify(soundsControllerMock).play(Sounds.SFX.MENUBGM);
        }
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            instructionsMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

            verifyNoInteractions(instructionsMenu);
            verifyNoInteractions(game);
            verifyNoInteractions(soundsControllerMock);
        }
    }
}