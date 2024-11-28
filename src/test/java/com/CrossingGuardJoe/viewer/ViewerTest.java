package com.CrossingGuardJoe.viewer;

import com.CrossingGuardJoe.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ViewerTest {

    private Viewer<Object> viewer;
    private Object model;
    private GUI gui;

    @BeforeEach
    void setUp() {
        model = new Object();
        viewer = new Viewer<>(model) {
            @Override
            public void drawElements(GUI gui) {}
        };
        gui = mock(GUI.class);
    }

    @Test
    void testGetModel() {
        assertEquals(model, viewer.getModel());
    }

    @Test
    void testDraw() throws IOException {
        viewer.draw(gui);
        verify(gui).clearScreen();
        verify(gui).refreshScreen();
    }
}