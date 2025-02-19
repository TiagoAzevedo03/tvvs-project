package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InstructionsMenuTest {
    private InstructionsMenu instructionsMenu;
    private SoundsController soundsController;

    @BeforeEach
    public void setUp() {
        instructionsMenu = new InstructionsMenu();
        soundsController = mock(SoundsController.class);
    }

    @Test
    public void testInitialPage() {
        assertEquals(1, instructionsMenu.getCurrentPage());
    }

    @Test
    public void testNavigateLeftAtFirstPage() {
        instructionsMenu.navigateLeft();
        assertEquals(1, instructionsMenu.getCurrentPage());
        verify(soundsController, never()).play(Sounds.SFX.FLIPPAGE);
    }

    @Test
    public void testGetTotalPages() {
        assertEquals(5, instructionsMenu.getTotalPages());
    }

    @Test
    public void testNavigateRight() {
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            SoundsController soundsControllerMock = mock(SoundsController.class);
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            instructionsMenu.navigateRight();

            verify(soundsControllerMock).play(Sounds.SFX.FLIPPAGE);
        }
    }

    @Test
    public void testNavigateRightAtLastPage() {
        for (int i = 1; i < instructionsMenu.getTotalPages(); i++) {
            instructionsMenu.navigateRight();
        }
        assertEquals(5, instructionsMenu.getCurrentPage());
        instructionsMenu.navigateRight();
        assertEquals(5, instructionsMenu.getCurrentPage());
    }

    @Test
    public void testNavigateLeft() {
        try (MockedStatic<SoundsController> mockedStatic = mockStatic(SoundsController.class)) {
            SoundsController soundsControllerMock = mock(SoundsController.class);
            mockedStatic.when(SoundsController::getInstance).thenReturn(soundsControllerMock);

            instructionsMenu.navigateRight();
            assertEquals(2, instructionsMenu.getCurrentPage());

            instructionsMenu.navigateLeft();
            assertEquals(1, instructionsMenu.getCurrentPage());
            verify(soundsControllerMock, times(2)).play(Sounds.SFX.FLIPPAGE);
        }
    }
}