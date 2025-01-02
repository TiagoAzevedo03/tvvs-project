package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.menu.GameOverMenu;
import com.CrossingGuardJoe.model.menu.Option;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import com.CrossingGuardJoe.viewer.images.generator.Shape;
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

    @Test
    void testDrawInformationLevelTen() {
        Road currentGame = mock(Road.class);
        Joe joe = mock(Joe.class);
        when(joe.getScore()).thenReturn(1000);
        when(currentGame.getJoe()).thenReturn(joe);
        when(currentGame.getCurrentLevel()).thenReturn(10);

        when(gameOverMenu.getCurrentGame()).thenReturn(currentGame);

        gameOverViewer.drawInformation(gui);

        verify(gui).drawImage(new Position(170, 154), Shape.RectangleFilledGenerator(305, 70, 'K', 2, '$'));
        verify(gui).drawText(new Position(210, 165), "score", "#FFFFFF");
        verify(gui).drawText(new Position(210, 195), "level", "#FFFFFF");
        verify(gui).drawText(new Position(275, 195), 9, "#D30000");
    }

    @Test
    void testDrawInformationNotLevelTen() {
        Road currentGame = mock(Road.class);
        Joe joe = mock(Joe.class);
        when(joe.getScore()).thenReturn(1000);
        when(currentGame.getJoe()).thenReturn(joe);
        when(currentGame.getCurrentLevel()).thenReturn(5);

        when(gameOverMenu.getCurrentGame()).thenReturn(currentGame);

        gameOverViewer.drawInformation(gui);

        verify(gui).drawImage(new Position(170, 154), Shape.RectangleFilledGenerator(305, 70, 'K', 2, '$'));
        verify(gui).drawText(new Position(210, 165), "score", "#FFFFFF");
        verify(gui).drawText(new Position(210, 195), "level", "#FFFFFF");
        verify(gui).drawText(new Position(275, 195), 5, "#FFFFFF");
    }

    @Test
    void testDrawElements() {
        Road currentGame = mock(Road.class);
        Joe joe = mock(Joe.class);
        when(joe.getScore()).thenReturn(1000);
        when(currentGame.getJoe()).thenReturn(joe);
        when(currentGame.getCurrentLevel()).thenReturn(10);

        when(gameOverMenu.getCurrentGame()).thenReturn(currentGame);

        Option option1 = mock(Option.class);
        when(option1.position()).thenReturn(new Position(50, 50));
        when(option1.name()).thenReturn("Option 1");

        when(gameOverMenu.getNumberOptions()).thenReturn(1);
        when(gameOverMenu.getOption(0)).thenReturn(option1);
        when(gameOverMenu.isSelectedOption(0)).thenReturn(true);

        gameOverViewer.drawElements(gui);

        verify(gui).drawImage(new Position(170, 154), Shape.RectangleFilledGenerator(305, 70, 'K', 2, '$'));
        verify(gui).drawText(new Position(210, 165), "score", "#FFFFFF");
        verify(gui).drawText(new Position(210, 195), "level", "#FFFFFF");
        verify(gui).drawText(new Position(275, 195), 9, "#D30000");

        verify(gui).drawText(new Position(50, 50), "Option 1", "#FFFFFF");
        verify(gui).drawImage(new Position(35, 50), ToolImages.getArrowRightImage());
    }

    @Test
    void testDrawElementsOptionNotSelected() {
        Road currentGame = mock(Road.class);
        Joe joe = mock(Joe.class);
        when(joe.getScore()).thenReturn(1000);
        when(currentGame.getJoe()).thenReturn(joe);
        when(currentGame.getCurrentLevel()).thenReturn(10);

        when(gameOverMenu.getCurrentGame()).thenReturn(currentGame);

        Option option1 = mock(Option.class);
        when(option1.position()).thenReturn(new Position(50, 50));
        when(option1.name()).thenReturn("Option 1");

        when(gameOverMenu.getNumberOptions()).thenReturn(1);
        when(gameOverMenu.getOption(0)).thenReturn(option1);
        when(gameOverMenu.isSelectedOption(0)).thenReturn(false);

        gameOverViewer.drawElements(gui);

        verify(gui).drawImage(new Position(170, 154), Shape.RectangleFilledGenerator(305, 70, 'K', 2, '$'));
        verify(gui).drawText(new Position(210, 165), "score", "#FFFFFF");
        verify(gui).drawText(new Position(210, 195), "level", "#FFFFFF");
        verify(gui).drawText(new Position(275, 195), 9, "#D30000");

        verify(gui).drawText(new Position(50, 50), "Option 1", "#FFFFFF");
        verify(gui, never()).drawImage(new Position(35, 50), ToolImages.getArrowRightImage());
    }
}