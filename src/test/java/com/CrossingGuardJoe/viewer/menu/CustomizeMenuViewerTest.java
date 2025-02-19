package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.ColorPaletteMenu;
import com.CrossingGuardJoe.model.menu.CustomizeMenu;
import com.CrossingGuardJoe.model.menu.Option;
import com.CrossingGuardJoe.viewer.Color;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import com.CrossingGuardJoe.viewer.images.generator.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class CustomizeMenuViewerTest {
    private CustomizeMenuViewer customizeMenuViewer;
    private GUI gui;
    private CustomizeMenu customizeMenu;

    @BeforeEach
    void setUp() {
        customizeMenu = mock(CustomizeMenu.class);
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

    @Test
    void testDrawSelectionBox() throws Exception {
        Method drawSelectionBoxMethod = CustomizeMenuViewer.class.getDeclaredMethod("drawSelectionBox", GUI.class);
        drawSelectionBoxMethod.setAccessible(true);

        drawSelectionBoxMethod.invoke(customizeMenuViewer, gui);

        verify(gui).drawImage(new Position(40, 70), Shape.RectangleFilledGenerator(235, 300, 'K', 2, '$'));
        verify(gui).drawImage(new Position(195, 70), Shape.RectangleFilledGenerator(235, 300, 'K', 2, '$'));
        verify(gui).drawImage(new Position(350, 70), Shape.RectangleFilledGenerator(235, 300, 'K', 2, '$'));
    }

    @Test
    void testDrawElementsColorSquare() throws Exception {
        List<Option> definedColors = Arrays.asList(
                new Option("Red", new Position(10, 10), Shape.RectangleFilledGenerator(10, 10, 'R', 1, ' ')),
                new Option("Green", new Position(20, 20), Shape.RectangleFilledGenerator(10, 10, 'G', 1, ' ')),
                new Option("Blue", new Position(30, 30), Shape.RectangleFilledGenerator(10, 10, 'B', 1, ' '))
        );

        when(customizeMenu.getDefinedColors()).thenReturn(definedColors);

        Method drawElementsColorSquareMethod = CustomizeMenuViewer.class.getDeclaredMethod("drawElementsColorSquare", GUI.class);
        drawElementsColorSquareMethod.setAccessible(true);

        drawElementsColorSquareMethod.invoke(customizeMenuViewer, gui);

        for (Option colorSquare : definedColors) {
            verify(gui).drawImage(colorSquare.position(), colorSquare.image());
        }
    }

    @Test
    void testDrawElements() throws Exception {
        Option option1 = mock(Option.class);
        when(option1.position()).thenReturn(new Position(50, 50));
        when(option1.image()).thenReturn(new String[]{"image1"});
        when(option1.name()).thenReturn("Option 1");

        List<Option> levelOptions = Collections.singletonList(option1);
        when(customizeMenu.getMenuLevels()).thenReturn(Collections.singletonList(levelOptions));

        when(customizeMenu.isSelectedJoeCustomize()).thenReturn(true);
        when(customizeMenu.isSelectedOption(0, 0)).thenReturn(true);

        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(true);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(180, 28), "CUSTOMIZE YOUR GAME", "#FFFFFF");
        verify(gui).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());

        verify(gui).drawImage(new Position(40, 70), Shape.RectangleFilledGenerator(235, 300, 'K', 2, '$'));
        verify(gui).drawImage(new Position(195, 70), Shape.RectangleFilledGenerator(235, 300, 'K', 2, '$'));
        verify(gui).drawImage(new Position(350, 70), Shape.RectangleFilledGenerator(235, 300, 'K', 2, '$'));

        verify(gui).drawImage(new Position(40, 70), Shape.RectangleFilledGenerator(235, 300, ' ', 2, 'G'));

        verify(gui).drawImage(new Position(50, 50), new String[]{"image1"});
        verify(gui).drawImage(new Position(20, 50), ToolImages.getArrowRightImage());

        verify(gui).setColorHexaCode("#FFFFFF");
        verify(gui).fillRectangle(new Position(105, 426), 25, 30);
        verify(gui).drawImage(new Position(52, 426), Shape.RectangleFilledGenerator(25, 30, ' ', 1, '$'));
        verify(gui).drawImage(new Position(55, 405), ToolImages.getArrowDownImage());

        reset(gui);
        when(customizeMenu.isSelectedJoeCustomize()).thenReturn(false);
        when(customizeMenu.isSelectedKidsCustomize()).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

        verify(gui).drawImage(new Position(195, 70), Shape.RectangleFilledGenerator(235, 300, ' ', 2, 'G'));

        reset(gui);
        when(customizeMenu.isSelectedKidsCustomize()).thenReturn(false);
        when(customizeMenu.isSelectedCarsCustomize()).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

        List<Option> definedColors = Arrays.asList(
                new Option("Red", new Position(10, 10), Shape.RectangleFilledGenerator(10, 10, 'R', 1, ' ')),
                new Option("Green", new Position(20, 20), Shape.RectangleFilledGenerator(10, 10, 'G', 1, ' ')),
                new Option("Blue", new Position(30, 30), Shape.RectangleFilledGenerator(10, 10, 'B', 1, ' '))
        );

        when(customizeMenu.getDefinedColors()).thenReturn(definedColors);

        Method drawElementsColorSquareMethod = CustomizeMenuViewer.class.getDeclaredMethod("drawElementsColorSquare", GUI.class);
        drawElementsColorSquareMethod.setAccessible(true);

        drawElementsColorSquareMethod.invoke(customizeMenuViewer, gui);

        for (Option colorSquare : definedColors) {
            verify(gui).drawImage(colorSquare.position(), colorSquare.image());
        }

        verify(gui).drawImage(new Position(350, 70), Shape.RectangleFilledGenerator(235, 300, ' ', 2, 'G'));
    }

    @Test
    void testDrawElementsOptionSelected() {
        Option option = mock(Option.class);
        when(option.position()).thenReturn(new Position(50, 50));
        when(option.image()).thenReturn(new String[]{"image1"});
        when(option.name()).thenReturn("Option 1");

        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(true);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        List<Option> levelOptions = Collections.singletonList(option);
        when(customizeMenu.getMenuLevels()).thenReturn(Collections.singletonList(levelOptions));
        when(customizeMenu.isSelectedOption(0, 0)).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

        verify(gui).drawImage(new Position(20, 50), ToolImages.getArrowRightImage());
    }

    @Test
    void testDrawElementsColorPaletteSelected() {
        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(true);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

        verify(gui).drawImage(new Position(55, 405), ToolImages.getArrowDownImage());
    }

    @Test
    void testDrawElementsColorPaletteNotSelected() {
        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(false);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(false);

        customizeMenuViewer.drawElements(gui);

        verify(gui, never()).drawImage(new Position(55, 405), ToolImages.getArrowDownImage());
    }

    @Test
    void testDrawElementsOptionText() {
        Option option = mock(Option.class);
        when(option.position()).thenReturn(new Position(50, 50));
        when(option.name()).thenReturn("Option 1");

        List<Option> levelOptions = Collections.singletonList(option);
        when(customizeMenu.getMenuLevels()).thenReturn(Collections.singletonList(levelOptions));

        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(true);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

    }

    @Test
    void testDrawElementsSelectionArrow() {
        Option option = mock(Option.class);
        when(option.position()).thenReturn(new Position(50, 50));

        List<Option> levelOptions = Collections.singletonList(option);
        when(customizeMenu.getMenuLevels()).thenReturn(Collections.singletonList(levelOptions));
        when(customizeMenu.isSelectedOption(0, 0)).thenReturn(true);

        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(true);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

        verify(gui).drawImage(new Position(20, 50), ToolImages.getArrowRightImage());
    }

    @Test
    void testDrawElementsOptionTextWhenJGreaterThanZero() {
        Option option = mock(Option.class);
        when(option.position()).thenReturn(new Position(50, 50));
        when(option.name()).thenReturn("Option 1");

        List<Option> levelOptions = Arrays.asList(mock(Option.class), option); // Ensure j > 0
        when(customizeMenu.getMenuLevels()).thenReturn(Collections.singletonList(levelOptions));

        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(true);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        customizeMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(49, 49), "Option 1", "#FFFFFF");
    }

    @Test
    void testDrawColorsPaletteColorSelected() throws Exception {
        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(true);

        Method method = CustomizeMenuViewer.class.getDeclaredMethod("drawColorsPalette", GUI.class);
        method.setAccessible(true);
        method.invoke(customizeMenuViewer, gui);

        verify(gui).setColorHexaCode("#FFFFFF");
        verify(gui).drawImage(any(Position.class), any());
    }

    @Test
    void testDrawColorsPaletteColorPaletteSelectedButNoColorSelected() throws Exception {
        ColorPaletteMenu colorPaletteMenu = mock(ColorPaletteMenu.class);
        when(customizeMenu.getColorPaletteMenu()).thenReturn(colorPaletteMenu);
        Color color = mock(Color.class);
        when(color.getColorHexCode()).thenReturn("#FFFFFF");
        when(colorPaletteMenu.getColorPalette()).thenReturn(Collections.singletonList(color));
        when(colorPaletteMenu.isColorSelected(0)).thenReturn(false);
        when(customizeMenu.isColorPaletteSelected()).thenReturn(true);

        Method method = CustomizeMenuViewer.class.getDeclaredMethod("drawColorsPalette", GUI.class);
        method.setAccessible(true);
        method.invoke(customizeMenuViewer, gui);

        verify(gui, never()).drawImage(new Position(55, 405), ToolImages.getArrowDownImage());
    }

    @Test
    void testAddColorMap() throws Exception {
        when(customizeMenu.getOldColor()).thenReturn('A');
        when(customizeMenu.getNewColor()).thenReturn('B');

        Method method = CustomizeMenuViewer.class.getDeclaredMethod("addColorMap", GUI.class);
        method.setAccessible(true);
        method.invoke(customizeMenuViewer, gui);

        verify(gui).addColorMapping('A', 'B');
    }

    @Test
    void testAddColorMapOldColorFFFF() throws Exception {
        when(customizeMenu.getOldColor()).thenReturn('\uFFFF');
        when(customizeMenu.getNewColor()).thenReturn('B');

        Method method = CustomizeMenuViewer.class.getDeclaredMethod("addColorMap", GUI.class);
        method.setAccessible(true);
        method.invoke(customizeMenuViewer, gui);

        verify(gui, never()).addColorMapping(anyChar(), anyChar());
    }
}