package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.StatsMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class StatsMenuControllerTest {

    private StatsMenuController statsMenuController;
    private Game game;

    @BeforeEach
    void setUp() {
        StatsMenu statsMenu = mock(StatsMenu.class);
        game = mock(Game.class);
        statsMenuController = new StatsMenuController(statsMenu);
    }

    @Test
    void testNextActionEsc() throws IOException {
        statsMenuController.nextAction(game, GUI.ACTION.ESC, System.currentTimeMillis());

        verify(game).popState();
    }
}