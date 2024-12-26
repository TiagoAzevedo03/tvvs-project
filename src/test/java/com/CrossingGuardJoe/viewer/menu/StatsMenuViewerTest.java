package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.StatsMenu;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class StatsMenuViewerTest {
    private StatsMenuViewer statsMenuViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        StatsMenu statsMenu = mock(StatsMenu.class);
        gui = mock(GUI.class);
        statsMenuViewer = new StatsMenuViewer(statsMenu);
    }

    @Test
    void testDrawElements() {
        statsMenuViewer.drawElements(gui);
        verify(gui).drawText(new Position(207, 100), "Game stats", "#FFFFFF");
        verify(gui).drawText(new Position(210, 210), "score", "#FFFFFF");
        verify(gui).drawText(new Position(210, 250), "level", "#FFFFFF");
        verify(gui).drawText(new Position(150, 300), "highest score", "#FFFFFF");
        verify(gui).drawText(new Position(150, 340), "highest level", "#FFFFFF");
        verify(gui).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
    }

    @Test
    void testDrawTitle() {
        statsMenuViewer.drawTitle(gui);
        verify(gui).drawText(new Position(207, 100), "Game stats", "#FFFFFF");
    }

    @Test
    void testDrawInformationCurrentLevel10() {
        StatsMenu statsMenu = mock(StatsMenu.class);
        when(statsMenu.getCurrentScore()).thenReturn(1000);
        when(statsMenu.getCurrentLevel()).thenReturn(10);
        when(statsMenu.getHighestScore()).thenReturn(2000);
        when(statsMenu.getHighestLevel()).thenReturn(5);

        statsMenuViewer = new StatsMenuViewer(statsMenu);
        statsMenuViewer.drawInformation(gui);

        verify(gui).drawText(new Position(275, 210), 1000, "#FFFFFF");
        verify(gui).drawText(new Position(275, 250), 9, "#FFFFFF");
        verify(gui).drawText(new Position(275, 300), 2000, "#FFFFFF");
        verify(gui).drawText(new Position(275, 340), 5, "#FFFFFF");
        verify(gui).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
    }

    @Test
    void testDrawInformationHighestLevel10() {
        StatsMenu statsMenu = mock(StatsMenu.class);
        when(statsMenu.getCurrentScore()).thenReturn(1000);
        when(statsMenu.getCurrentLevel()).thenReturn(5);
        when(statsMenu.getHighestScore()).thenReturn(2000);
        when(statsMenu.getHighestLevel()).thenReturn(10);

        statsMenuViewer = new StatsMenuViewer(statsMenu);
        statsMenuViewer.drawInformation(gui);

        verify(gui).drawText(new Position(275, 210), 1000, "#FFFFFF");
        verify(gui).drawText(new Position(275, 250), 5, "#FFFFFF");
        verify(gui).drawText(new Position(275, 300), 2000, "#FFFFFF");
        verify(gui).drawText(new Position(275, 340), 9, "#FFFFFF");
        verify(gui).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
    }
}