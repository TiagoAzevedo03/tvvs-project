package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.Menu;
import com.CrossingGuardJoe.model.menu.Option;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import com.CrossingGuardJoe.viewer.images.defined.LogoImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuViewerTest {
    private MenuViewer menuViewer;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        gui = mock(GUI.class);
        menuViewer = new MenuViewer(menu);
    }

    @Test
    void testDrawElements() {
        Option option = mock(Option.class);
        when(menu.getNumberOptions()).thenReturn(1);
        when(menu.getOption(0)).thenReturn(option);
        when(option.position()).thenReturn(new Position(100, 100));
        when(option.name()).thenReturn("Option");
        when(menu.isSelectedOption(0)).thenReturn(true);

        menuViewer.drawElements(gui);

        verify(gui).drawText(new Position(100, 100), "Option", "#FFFFFF");
        verify(gui).drawImage(new Position(85, 100), ToolImages.getArrowRightImage());
    }

    @Test
    void testDrawTitle() {
        menuViewer.drawTitle(gui);
        verify(gui).drawImage(new Position(130, 50), LogoImages.getLogoGameImage());
    }
}