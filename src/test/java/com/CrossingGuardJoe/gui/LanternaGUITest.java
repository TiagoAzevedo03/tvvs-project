package com.CrossingGuardJoe.gui;

import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.viewer.ColorCustomize;
import com.CrossingGuardJoe.viewer.images.Font.FontImageFactory;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LanternaGUITest {

    private LanternaGUI lanternaGUI;
    private Screen mockScreen;
    private TextGraphics mockGraphics;

    @BeforeEach
    void setUp() {
        mockScreen = mock(Screen.class);
        mockGraphics = mock(TextGraphics.class);

        lanternaGUI = new LanternaGUI(80, 24);
        lanternaGUI.setScreen(mockScreen);
        lanternaGUI.setGraphics(mockGraphics);
    }

    @Test
    void testClearScreen() {
        lanternaGUI.clearScreen();
        verify(mockGraphics, times(1)).setBackgroundColor(TextColor.Factory.fromString("#7F7976"));
        verify(mockGraphics, times(1)).fillRectangle(any(), any(), eq(' '));
    }

    @Test
    void testRefreshScreen() throws IOException {
        lanternaGUI.refreshScreen();
        verify(mockScreen, times(1)).refresh();
    }

    @Test
    void testCloseScreen() throws IOException {
        lanternaGUI.closeScreen();
        verify(mockScreen, times(1)).close();
    }

    @Test
    void testSetBackgroundColor() {
        String colorHexCode = "#123456";
        lanternaGUI.setBackgroundColor(colorHexCode);
        verify(mockGraphics, times(1)).setBackgroundColor(TextColor.Factory.fromString(colorHexCode));
        verify(mockGraphics, times(1)).fillRectangle(any(), any(), eq(' '));
    }

    @Test
    void testFillRectangle() {
        Position position = new Position(0, 0);
        lanternaGUI.fillRectangle(position, 10, 5);
        verify(mockGraphics, times(1)).fillRectangle(eq(new com.googlecode.lanterna.TerminalPosition(0, 0)),
                eq(new com.googlecode.lanterna.TerminalSize(10, 5)), eq(' '));
    }

    @Test
    void testDrawImage() {
        Position position = new Position(2, 2);
        String[] image = {"123", "456"};
        lanternaGUI.drawImage(position, image);

        verify(mockGraphics, times(6)).fillRectangle(any(), eq(new com.googlecode.lanterna.TerminalSize(1, 1)), eq(' '));
    }

    @Test
    void testGetNextAction() throws IOException {
        KeyStroke mockKeyStroke = mock(KeyStroke.class);

        assertEquals(GUI.ACTION.NONE, lanternaGUI.getNextAction());

        when(mockScreen.pollInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowLeft);

        GUI.ACTION action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.LEFT, action);

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.Enter);
        action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.SELECT, action);

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.Escape);
        action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.ESC, action);

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);
        action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.RIGHT, action);

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.UP, action);

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
        action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.DOWN, action);

        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.EOF);
        action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.QUIT, action);
    }

    @Test
    void testGetNextActionDefaultCase() throws IOException {
        KeyStroke mockKeyStroke = mock(KeyStroke.class);

        when(mockScreen.pollInput()).thenReturn(mockKeyStroke);
        when(mockKeyStroke.getKeyType()).thenReturn(KeyType.Character);

        GUI.ACTION action = lanternaGUI.getNextAction();
        assertEquals(GUI.ACTION.NONE, action);
    }

    @Test
    void testDrawImageCustomColor() {
        Position position = new Position(1, 1);
        String[] image = {"XX", "YY"};
        String colorHexCode = "#FF0000";

        lanternaGUI.drawImageCustomColor(position, image, colorHexCode);

        verify(mockGraphics, times(4)).setBackgroundColor(TextColor.Factory.fromString(colorHexCode));
        verify(mockGraphics, times(4)).fillRectangle(any(), eq(new com.googlecode.lanterna.TerminalSize(1, 1)), eq(' '));
    }

    @Test
    void testDrawLineWithSpaceCharacter() {
        int x = 0;
        int y = 0;
        String imageLine = "     ";

        lanternaGUI.drawLine(x, y, imageLine);

        verify(mockGraphics, never()).setCharacter(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawLineCustomColorWithSpaceCharacter() {
        int x = 0;
        int y = 5;
        String imageLine = "     ";
        String colorHexCode = "#FF0000";

        lanternaGUI.drawLineCustomColor(x, y, imageLine, colorHexCode);

        verify(mockGraphics, never()).setCharacter(anyInt(), anyInt(), any());
    }

    @Test
    void testDrawText() {
        Position position = new Position(5, 5);
        String text = "Hello";
        String colorHexCode = "#FF0000";
        String[] textImage = new FontImageFactory().getImageRepresentation(text);

        LanternaGUI lanternaGUISpy = spy(lanternaGUI);
        doNothing().when(lanternaGUISpy).drawImageCustomColor(any(Position.class), any(String[].class), anyString());

        lanternaGUISpy.drawText(position, text, colorHexCode);

        verify(lanternaGUISpy).drawImageCustomColor(new Position(position.getX() + 1, position.getY() + 1), textImage, "#000000");
        verify(lanternaGUISpy).drawImageCustomColor(position, textImage, colorHexCode);
    }
}