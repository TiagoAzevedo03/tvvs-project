package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.ColorPaletteMenu;
import com.CrossingGuardJoe.model.menu.CustomizeMenu;
import com.CrossingGuardJoe.viewer.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

class CustomizeMenuControllerTest {

    private CustomizeMenuController customizeMenuController;
    private CustomizeMenu customizeMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        customizeMenu = mock(CustomizeMenu.class);
        game = mock(Game.class);
        customizeMenuController = new CustomizeMenuController(customizeMenu);

        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);

        Color color = mock(Color.class);
        when(color.getCharacter()).thenReturn('B');
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
    }

    @Test
    void testNextActionLeft() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.LEFT, System.currentTimeMillis());

            verify(customizeMenu).navigateLeft();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionRight() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.RIGHT, System.currentTimeMillis());

            verify(customizeMenu).navigateRight();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionUp() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

            verify(customizeMenu).navigateUp();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionDown() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

            verify(customizeMenu).navigateDown();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionEsc() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

            verify(game).popState();
            verify(soundsControllerMock).stop(Sounds.SFX.CUSTOMIZEBGM);
            verify(soundsControllerMock).play(Sounds.SFX.MENUBGM);
        }
    }

    @Test
    void testNextActionSelect() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);
        when(customizeMenu.getSelectedColorChar()).thenReturn('R');

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(customizeMenu).setColorPaletteSelected(true);
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
        }
    }

    @Test
    void testNextActionColorPaletteLeft() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.LEFT, System.currentTimeMillis());

            verify(customizeMenu.getColorPaletteMenu()).navigateLeft();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionColorPaletteRight() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.RIGHT, System.currentTimeMillis());

            verify(customizeMenu.getColorPaletteMenu()).navigateRight();
            verify(soundsControllerMock).play(Sounds.SFX.SELECT);
        }
    }

    @Test
    void testNextActionColorPaletteEsc() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

            verify(customizeMenu).setColorPaletteSelected(false);
        }
    }

    @Test
    void testNextActionColorPaletteSelect() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);
        ColorPaletteMenu colorPaletteMenu = customizeMenu.getColorPaletteMenu();
        when(colorPaletteMenu.getSelectedColorIndex()).thenReturn(0);

        SoundsController soundsControllerMock = mock(SoundsController.class);
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            customizeMenuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

            verify(customizeMenu).setColorPaletteSelected(false);
            verify(soundsControllerMock).play(Sounds.SFX.ENTER);
        }
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

         verify(customizeMenu, never()).navigateLeft();
        verify(customizeMenu, never()).navigateRight();
        verify(customizeMenu, never()).navigateUp();
        verify(customizeMenu, never()).navigateDown();
        verify(customizeMenu, never()).setColorPaletteSelected(anyBoolean());
        verify(game, never()).popState();
    }

    @Test
    void testNextActionDefaultCaseWithColorPaletteSelected() throws IOException {
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verify(customizeMenu, never()).navigateLeft();
        verify(customizeMenu, never()).navigateRight();
        verify(customizeMenu, never()).navigateUp();
        verify(customizeMenu, never()).navigateDown();
        verify(customizeMenu, never()).setColorPaletteSelected(anyBoolean());
        verify(game, never()).popState();
    }
}