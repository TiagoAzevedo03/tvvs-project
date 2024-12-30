package com.CrossingGuardJoe.gui;

import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.viewer.Color;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class LanternaGUITest {
    private LanternaGUI lanternaGUI;
    private TextGraphics graphics;
    private Screen screen;

    @Before
    public void setUp() {
        lanternaGUI = new LanternaGUI(80, 24);
        graphics = mock(TextGraphics.class);
        screen = mock(Screen.class);
        lanternaGUI.setGraphics(graphics);
        lanternaGUI.setScreen(screen);
    }

    @Test
    public void testRefreshScreen() throws IOException {
        lanternaGUI.refreshScreen();
        verify(screen).refresh();
    }

    @Test
    public void testCloseScreen() throws IOException {
        lanternaGUI.closeScreen();
        verify(screen).close();
    }

    @Test
    public void testFillRectangle() {
        Position position = new Position(0, 0);
        lanternaGUI.fillRectangle(position, 10, 10);
        verify(graphics).fillRectangle(any(), eq(new TerminalSize(10, 10)), eq(' '));
    }

    @Test
    public void testSetColorHexaCode() {
        lanternaGUI.setColorHexaCode("#FFFFFF");
        verify(graphics).setBackgroundColor(any());
    }

    @Test
    public void testAddColorMapping() {
        lanternaGUI.addColorMapping('a', 'b');
        assertEquals('b', lanternaGUI.getMappedCharacter('a'));
    }

    @Test
    public void testClear() {
        lanternaGUI.clearScreen();
        verify(graphics).fillRectangle(any(), any(TerminalSize.class), eq(' '));
    }

    @Test
    public void testGetScreenSize() {
        TerminalSize size = new TerminalSize(80, 24);
        when(screen.getTerminalSize()).thenReturn(size);

        TerminalSize screenSize = screen.getTerminalSize();

        assertEquals(size, screenSize);
    }

    @Test
    public void testGetNextAction() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);

        when(screen.pollInput()).thenReturn(keyStroke);

        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowLeft);
        assertEquals(LanternaGUI.ACTION.LEFT, lanternaGUI.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);
        assertEquals(LanternaGUI.ACTION.RIGHT, lanternaGUI.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        assertEquals(LanternaGUI.ACTION.UP, lanternaGUI.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertEquals(LanternaGUI.ACTION.DOWN, lanternaGUI.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter);
        assertEquals(LanternaGUI.ACTION.SELECT, lanternaGUI.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.Escape);
        assertEquals(LanternaGUI.ACTION.ESC, lanternaGUI.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.EOF);
        assertEquals(LanternaGUI.ACTION.QUIT, lanternaGUI.getNextAction());

        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        assertEquals(LanternaGUI.ACTION.NONE, lanternaGUI.getNextAction());

        when(screen.pollInput()).thenReturn(null);
        assertEquals(LanternaGUI.ACTION.NONE, lanternaGUI.getNextAction());
    }

    @Test
    public void testSetColorValidCharacter() {
        char character = '<';
        Color color = Color.getColor(character);
        String colorHexCode = color.getColorHexCode();

        lanternaGUI.setColor(character);

        verify(graphics).setBackgroundColor(TextColor.Factory.fromString(colorHexCode));
    }

    @Test
    public void testSetColorInvalidCharacter() {
        char character = 'Z';

        lanternaGUI.setColor(character);

        verify(graphics, never()).setBackgroundColor(any(TextColor.class));
    }
}