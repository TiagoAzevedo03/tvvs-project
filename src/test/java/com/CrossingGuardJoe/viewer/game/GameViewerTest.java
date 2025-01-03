package com.CrossingGuardJoe.viewer.game;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import com.CrossingGuardJoe.viewer.images.defined.HUDImages;
import com.CrossingGuardJoe.viewer.images.defined.RoadItemsImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;

class GameViewerTest {

    private GameViewer gameViewer;
    private Road road;
    private GUI gui;
    private Joe joe;

    @BeforeEach
    void setUp() {
        road = mock(Road.class);
        gui = mock(GUI.class);
        joe = mock(Joe.class);
        Kid kid = mock(Kid.class);
        Car car = mock(Car.class);
        gameViewer = new GameViewer(road);

        when(road.getJoe()).thenReturn(joe);
        when(road.getKids()).thenReturn(new ArrayList<>(Collections.singletonList(kid)));
        when(road.getCars()).thenReturn(new ArrayList<>(Collections.singletonList(car)));
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

        int ROAD_START_Y = 38;
        int iniX = 167;
        for (int i = 0; i < 834 - iniX - 15; i += 24) {
            verify(gui).fillRectangle(new Position(iniX + i, 368), 16, 50);
        }

        verify(gui).fillRectangle(new Position(0, ROAD_START_Y), 150, 500 - ROAD_START_Y);
        verify(gui).fillRectangle(new Position(850, ROAD_START_Y), 150, 500 - ROAD_START_Y);
        verify(gui).fillRectangle(new Position(326, ROAD_START_Y), 4, 302);
        verify(gui).fillRectangle(new Position(150, ROAD_START_Y), 2, 500 - ROAD_START_Y);
    }

    @Test
    void testDrawRoadLines2() {
        gameViewer.drawRoadLines(gui);

        verify(gui).setColorHexaCode("#C0BBB1");
        verify(gui, times(2)).fillRectangle(any(Position.class), eq(150), eq(462));
        verify(gui, times(3)).fillRectangle(any(Position.class), eq(4), eq(302));
        verify(gui).setColorHexaCode("#3D3638");
        verify(gui, times(4)).fillRectangle(any(Position.class), eq(2), eq(462));
    }

    @Test
    void testDrawRoadItems() {
        gameViewer.drawRoadItems(gui);
        verify(gui, times(2)).drawImage(any(Position.class), any());
    }

    @Test
    void testDrawElements() {
        gameViewer.drawElements(gui);

        verify(gui).drawImage(new Position(426, 258), RoadItemsImages.getSignalImage());
        verify(gui).drawImage(new Position(55, 258), RoadItemsImages.getSignalImage());
        verify(gui).drawText(new Position(164, 10), joe.getScore(), "#FFFFFF");
        verify(gui).drawText(new Position(45, 10), road.getCurrentLevel(), "#FFFFFF");
    }

    @Test
    void testDrawHUD() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(joe.getScore()).thenReturn(100);
        when(joe.getHearts()).thenReturn(3);

        Method drawHUD = GameViewer.class.getDeclaredMethod("drawHUD", GUI.class);
        drawHUD.setAccessible(true);
        drawHUD.invoke(gameViewer, gui);

        verify(gui).drawImage(new Position(0, 0), HUDImages.getGameHudImage());
        verify(gui).drawText(new Position(164, 10), 100, "#FFFFFF");

        int heartIniX = 246;
        for (int i = 0; i < 3; i++) {
            verify(gui).drawImage(new Position(heartIniX, 4), HUDImages.getHPImage());
            heartIniX += 25;
        }
    }

    @Test
    void testDrawHUDWithZeroHearts() throws Exception {
        when(road.getJoe()).thenReturn(joe);
        when(joe.getHearts()).thenReturn(0);
        when(joe.getScore()).thenReturn(100);

        Method drawHUD = GameViewer.class.getDeclaredMethod("drawHUD", GUI.class);
        drawHUD.setAccessible(true);
        drawHUD.invoke(gameViewer, gui);

        verify(gui).drawImage(new Position(0, 0), HUDImages.getGameHudImage());
        verify(gui).drawText(new Position(164, 10), 100, "#FFFFFF");

        verify(gui, never()).drawImage(new Position(246, 4), HUDImages.getHPImage());
    }

    @Test
    void testDrawHUDWithMaxHearts() throws Exception {
        when(road.getJoe()).thenReturn(joe);
        when(joe.getHearts()).thenReturn(10);
        when(joe.getScore()).thenReturn(100);

        Method drawHUD = GameViewer.class.getDeclaredMethod("drawHUD", GUI.class);
        drawHUD.setAccessible(true);
        drawHUD.invoke(gameViewer, gui);

        verify(gui).drawImage(new Position(0, 0), HUDImages.getGameHudImage());
        verify(gui).drawText(new Position(164, 10), 100, "#FFFFFF");

        int heartIniX = 246;
        for (int i = 0; i < 10; i++) {
            verify(gui).drawImage(new Position(heartIniX, 4), HUDImages.getHPImage());
            heartIniX += 25;
        }
    }

    @Test
    void testDrawScore() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(road.getCurrentLevel()).thenReturn(5);

        Method drawScoreMethod = GameViewer.class.getDeclaredMethod("drawScore", GUI.class);
        drawScoreMethod.setAccessible(true);
        drawScoreMethod.invoke(gameViewer, gui);

        verify(gui).drawText(new Position(45, 10), 5, "#FFFFFF");
    }
}