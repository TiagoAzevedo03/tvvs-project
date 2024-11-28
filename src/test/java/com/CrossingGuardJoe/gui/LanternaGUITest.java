package com.CrossingGuardJoe.gui;

import com.CrossingGuardJoe.model.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
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
}