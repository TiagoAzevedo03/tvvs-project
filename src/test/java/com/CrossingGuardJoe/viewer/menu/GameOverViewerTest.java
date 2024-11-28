package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.GameOverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameOverViewerTest {
    private GameOverViewer gameOverViewer;
    private GameOverMenu gameOverMenu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gameOverMenu = mock(GameOverMenu.class);
        gui = mock(GUI.class);
        gameOverViewer = new GameOverViewer(gameOverMenu);
    }

    @Test
    void testDrawTitle() {
        when(gameOverMenu.isWin()).thenReturn(true);
        gameOverViewer.drawTitle(gui);
        verify(gui).drawText(new Position(187, 80), "Congratulations", "#FFFFFF");

        when(gameOverMenu.isWin()).thenReturn(false);
        gameOverViewer.drawTitle(gui);
        verify(gui).drawText(new Position(213, 80), "game over", "#FFFFFF");
    }
}