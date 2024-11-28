package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.InstructionsMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InstructionsMenuViewerTest {
    private InstructionsMenuViewer instructionsMenuViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        InstructionsMenu instructionsMenu = mock(InstructionsMenu.class);
        gui = mock(GUI.class);
        instructionsMenuViewer = new InstructionsMenuViewer(instructionsMenu);
    }

    @Test
    void testDrawTitle() {
        instructionsMenuViewer.drawTitle(gui);
        verify(gui).drawText(new Position(202, 17), "Instructions", "#FFFFFF");
    }
}