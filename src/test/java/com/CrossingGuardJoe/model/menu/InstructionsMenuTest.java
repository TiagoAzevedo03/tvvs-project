package com.CrossingGuardJoe.model.menu;

import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InstructionsMenuTest {
    private InstructionsMenu instructionsMenu;
    private SoundsController soundsController;

    @Before
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
}