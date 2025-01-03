package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.PauseMenu;
import com.CrossingGuardJoe.model.menu.Option;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PauseMenuViewerTest {
    private PauseMenuViewer pauseMenuViewer;
    private PauseMenu pauseMenu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        pauseMenu = mock(PauseMenu.class);
        gui = mock(GUI.class);
        pauseMenuViewer = spy(new PauseMenuViewer(pauseMenu));
    }

    @Test
    void testDrawElements() {
        Option option = mock(Option.class);
        when(pauseMenu.getNumberOptions()).thenReturn(1);
        when(pauseMenu.getOption(0)).thenReturn(option);
        when(option.position()).thenReturn(new Position(100, 100));
        when(option.name()).thenReturn("Option");
        when(pauseMenu.isSelectedOption(0)).thenReturn(true);

        pauseMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(100, 100), "Option", "#FFFFFF");
        verify(gui).drawImage(new Position(85, 100), ToolImages.getArrowRightImage());
    }

    @Test
    void testDrawElementsCallsDrawTitle() {
        pauseMenuViewer.drawElements(gui);
        verify(pauseMenuViewer, times(1)).drawTitle(gui);
    }

    @Test
    void testDrawTitle() {
        pauseMenuViewer.drawTitle(gui);
        verify(gui).drawText(new Position(207, 100), "Game paused", "#FFFFFF");
    }

    @Test
    void testDrawElementsOptionNotSelected() {
        Option option = mock(Option.class);
        when(pauseMenu.getNumberOptions()).thenReturn(1);
        when(pauseMenu.getOption(0)).thenReturn(option);
        when(option.position()).thenReturn(new Position(100, 100));
        when(option.name()).thenReturn("Option");
        when(pauseMenu.isSelectedOption(0)).thenReturn(false);

        pauseMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(100, 100), "Option", "#FFFFFF");
    }

    @Test
    void testDrawInformation() {
        pauseMenuViewer.drawInformation(gui);
        verifyNoInteractions(gui);
    }
}