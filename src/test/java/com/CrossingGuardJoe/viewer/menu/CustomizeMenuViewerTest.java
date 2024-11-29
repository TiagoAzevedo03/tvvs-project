package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.CustomizeMenu;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CustomizeMenuViewerTest {
    private CustomizeMenuViewer customizeMenuViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        CustomizeMenu customizeMenu = mock(CustomizeMenu.class);
        gui = mock(GUI.class);
        customizeMenuViewer = new CustomizeMenuViewer(customizeMenu);
    }

    @Test
    void testDrawTitle() {
        customizeMenuViewer.drawTitle(gui);
        verify(gui).drawText(new Position(180, 28), "CUSTOMIZE YOUR GAME", "#FFFFFF");
    }

    @Test
    void testDrawInformation() {
        customizeMenuViewer.drawInformation(gui);
        verify(gui).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
    }
}