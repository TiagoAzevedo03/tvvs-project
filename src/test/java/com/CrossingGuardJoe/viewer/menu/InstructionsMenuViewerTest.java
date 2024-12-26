package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.InstructionsMenu;
import com.CrossingGuardJoe.viewer.images.defined.*;
import com.CrossingGuardJoe.viewer.images.generator.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InstructionsMenuViewerTest {
    private InstructionsMenuViewer instructionsMenuViewer;
    private InstructionsMenu instructionsMenu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        instructionsMenu = mock(InstructionsMenu.class);
        gui = mock(GUI.class);
        instructionsMenuViewer = new InstructionsMenuViewer(instructionsMenu);
    }

    @Test
    void testDrawTitle() {
        instructionsMenuViewer.drawTitle(gui);
        verify(gui).drawText(new Position(202, 17), "Instructions", "#FFFFFF");
    }

    @Test
    void testDrawInformation() {
        when(instructionsMenu.getTotalPages()).thenReturn(5);
        when(instructionsMenu.getCurrentPage()).thenReturn(3);

        instructionsMenuViewer.drawInformation(gui);

        verify(gui, times(2)).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
        verify(gui).drawText(new Position(400, 474), "Page", "#FFFFFF");
        verify(gui).drawText(new Position(454, 474), "of", "#FFFFFF");
        verify(gui).drawText(new Position(474, 474), 5, "#FFFFFF");
        verify(gui).drawText(new Position(440, 474), 3, "#FFFFFF");
    }

    @Test
    void testDrawElementsPageOne() {
        when(instructionsMenu.getCurrentPage()).thenReturn(1);

        instructionsMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(202, 17), "Instructions", "#FFFFFF");
        verify(gui, times(2)).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
        verify(gui).drawText(new Position(400, 474), "Page", "#FFFFFF");
        verify(gui).drawText(new Position(454, 474), "of", "#FFFFFF");
        verify(gui).drawText(new Position(440, 474), 1, "#FFFFFF");
        verify(gui).drawImage(new Position(20, 50), Shape.RectangleFilledGenerator(920, 410, 'K', 2, '$'));
        verify(gui).drawText(new Position(50, 100), "you are Joe", "#FFFFFF");
        verify(gui).drawText(new Position(70, 120), "a crossing guard", "#FFFFFF");
        verify(gui).drawText(new Position(50, 400), "click once to move", "#FFFFFF");
        verify(gui).drawImage(new Position(150, 190), JoeImages.getJoeStandImage());
        verify(gui).drawImage(new Position(230, 190), JoeImages.getJoeWalkleftImage());
        verify(gui).drawImage(new Position(310, 190), JoeImages.getJoeWalkrightImage());
        verify(gui).drawImage(new Position(235, 310), ToolImages.getKeyLeftImage());
        verify(gui).drawImage(new Position(315, 310), ToolImages.getKeyRightImage());
    }

    @Test
    void testDrawElementsPageTwo() {
        when(instructionsMenu.getCurrentPage()).thenReturn(2);

        instructionsMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(202, 17), "Instructions", "#FFFFFF");
        verify(gui, times(2)).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
        verify(gui).drawText(new Position(400, 474), "Page", "#FFFFFF");
        verify(gui).drawText(new Position(454, 474), "of", "#FFFFFF");
        verify(gui).drawText(new Position(440, 474), 2, "#FFFFFF");
        verify(gui).drawImage(new Position(20, 50), Shape.RectangleFilledGenerator(920, 410, 'K', 2, '$'));
        verify(gui).drawText(new Position(50, 100), "you will help", "#FFFFFF");
        verify(gui).drawText(new Position(70, 120), "the kids to cross the road", "#FFFFFF");
        verify(gui).drawText(new Position(50, 400), "click once to order", "#FFFFFF");
        verify(gui).drawImage(new Position(170, 180), JoeImages.getJoeStopImage());
        verify(gui).drawImage(new Position(290, 180), JoeImages.getJoePassImage());
        verify(gui).drawImage(new Position(200, 220), KidImages.getKidStandImage());
        verify(gui).drawImage(new Position(280, 220), KidImages.getKidWalkImage());
        verify(gui).drawImage(new Position(180, 310), ToolImages.getKeyUpImage());
        verify(gui).drawImage(new Position(295, 310), ToolImages.getKeyDownImage());
    }

    @Test
    void testDrawElementsPageThree() {
        when(instructionsMenu.getCurrentPage()).thenReturn(3);

        instructionsMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(202, 17), "Instructions", "#FFFFFF");
        verify(gui, times(2)).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
        verify(gui).drawText(new Position(400, 474), "Page", "#FFFFFF");
        verify(gui).drawText(new Position(454, 474), "of", "#FFFFFF");
        verify(gui).drawText(new Position(440, 474), 3, "#FFFFFF");
        verify(gui).drawImage(new Position(20, 50), Shape.RectangleFilledGenerator(920, 410, 'K', 2, '$'));
        verify(gui).drawText(new Position(50, 100), "be careful", "#FFFFFF");
        verify(gui).drawText(new Position(70, 120), "with rude drivers", "#FFFFFF");
        verify(gui).drawImage(new Position(150, 195), CarImage.getCarImage());
        verify(gui).drawImage(new Position(280, 195), CarImage.getCarImage());
        verify(gui).drawImage(new Position(190, 245), KidImages.getKidHitImage());
        verify(gui).drawImage(new Position(320, 215), JoeImages.getJoeHitrightImage());
    }

    @Test
    void testDrawElementsPageFour() {
        when(instructionsMenu.getCurrentPage()).thenReturn(4);

        instructionsMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(202, 17), "Instructions", "#FFFFFF");
        verify(gui, times(2)).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
        verify(gui).drawText(new Position(400, 474), "Page", "#FFFFFF");
        verify(gui).drawText(new Position(454, 474), "of", "#FFFFFF");
        verify(gui).drawText(new Position(440, 474), 4, "#FFFFFF");
        verify(gui).drawImage(new Position(20, 50), Shape.RectangleFilledGenerator(920, 410, 'K', 2, '$'));
        verify(gui).drawText(new Position(50, 100), "if you lose a kid", "#FFFFFF");
        verify(gui).drawText(new Position(70, 120), "you lose hp", "#FFFFFF");
        verify(gui).drawImage(new Position(199, 229), Shape.RectangleFilledGenerator(202, 39, ' ', 2, '$'));
        verify(gui).drawImage(new Position(200, 230), HUDImages.getHpBarSliceImage());
        verify(gui).drawImage(new Position(208, 234), HUDImages.getHPImage());
        verify(gui).drawImage(new Position(233, 234), HUDImages.getHPImage());
        verify(gui).drawImage(new Position(258, 234), HUDImages.getHPImage());
    }

    @Test
    void testDrawElementsPageFive() {
        when(instructionsMenu.getCurrentPage()).thenReturn(5);

        instructionsMenuViewer.drawElements(gui);

        verify(gui).drawText(new Position(202, 17), "Instructions", "#FFFFFF");
        verify(gui, times(2)).drawImage(new Position(4, 4), ToolImages.getKeyEscImage());
        verify(gui).drawText(new Position(400, 474), "Page", "#FFFFFF");
        verify(gui).drawText(new Position(454, 474), "of", "#FFFFFF");
        verify(gui).drawText(new Position(440, 474), 5, "#FFFFFF");
        verify(gui).drawImage(new Position(20, 50), Shape.RectangleFilledGenerator(920, 410, 'K', 2, '$'));
        verify(gui).drawText(new Position(50, 100), "try to get", "#FFFFFF");
        verify(gui).drawText(new Position(70, 120), "maximum score", "#FFFFFF");
        verify(gui).drawImage(new Position(184, 229), Shape.RectangleFilledGenerator(266, 39, ' ', 2, '$'));
        verify(gui).drawImage(new Position(185, 230), HUDImages.getScoreBarSliceImage());
        verify(gui).drawText(new Position(275, 240), 2590, "#FFFFFF");
    }

    @Test
    void testDrawInformationLastPage() {
        when(instructionsMenu.getTotalPages()).thenReturn(5);
        when(instructionsMenu.getCurrentPage()).thenReturn(5);

        instructionsMenuViewer.drawInformation(gui);

        verify(gui).drawText(new Position(440, 474), 5, "#D30000");
    }
}