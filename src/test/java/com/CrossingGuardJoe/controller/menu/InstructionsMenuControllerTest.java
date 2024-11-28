package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.InstructionsMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class InstructionsMenuControllerTest {

    private InstructionsMenuController instructionsMenuController;
    private InstructionsMenu instructionsMenu;
    private Game game;

    @BeforeEach
    void setUp() {
        instructionsMenu = mock(InstructionsMenu.class);
        game = mock(Game.class);
        mock(SoundsController.class);
        instructionsMenuController = new InstructionsMenuController(instructionsMenu);
    }

    @Test
    void testNextActionNavigateRight() throws IOException {
        instructionsMenuController.nextAction(game, GUI.ACTION.RIGHT, System.currentTimeMillis());

        verify(instructionsMenu).navigateRight();
    }

    @Test
    void testNextActionNavigateLeft() throws IOException {
        instructionsMenuController.nextAction(game, GUI.ACTION.LEFT, System.currentTimeMillis());

        verify(instructionsMenu).navigateLeft();
    }

    @Test
    void testNextActionEsc() throws IOException {
        instructionsMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

        verify(game).popState();
    }
}