package com.CrossingGuardJoe.viewer.game;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.viewer.images.defined.HUDImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class GameViewerTest {

    private GameViewer gameViewer;
    private Road road;
    private GUI gui;

    @BeforeEach
    void setUp() {
        road = mock(Road.class);
        gui = mock(GUI.class);
        gameViewer = new GameViewer(road);
    }

    @Test
    void testDrawRoad() {
        gameViewer.drawRoad(gui);
        verify(gui, atLeastOnce()).fillRectangle(any(Position.class), anyInt(), anyInt());
        verify(gui, atLeastOnce()).drawImage(any(Position.class), any());
    }

    @Test
    void testDrawRoadLines() {
        gameViewer.drawRoadLines(gui);
        verify(gui, atLeastOnce()).fillRectangle(any(Position.class), anyInt(), anyInt());
    }

    @Test
    void testDrawRoadItems() {
        gameViewer.drawRoadItems(gui);
        verify(gui, times(2)).drawImage(any(Position.class), any());
    }
}