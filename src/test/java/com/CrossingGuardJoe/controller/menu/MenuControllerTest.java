package com.CrossingGuardJoe.controller.menu;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.menu.Menu;
import com.CrossingGuardJoe.states.GameState;
import com.CrossingGuardJoe.states.menu.CustomizeMenuState;
import com.CrossingGuardJoe.states.menu.InstructionsMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    private MenuController menuController;
    private Menu menu;
    private Game game;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        game = mock(Game.class);
        menuController = new MenuController(menu);
    }

    @Test
    void testNextActionUp() throws IOException {
        menuController.nextAction(game, GUI.ACTION.UP, System.currentTimeMillis());

        verify(menu).navigateUp();
    }

    @Test
    void testNextActionDown() throws IOException {
        menuController.nextAction(game, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(menu).navigateDown();
    }

    @Test
    void testNextActionSelectStartGame() throws IOException {
        when(menu.isSelectedStartGame()).thenReturn(true);

        menuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game).setState(any(GameState.class));
    }

    @Test
    void testNextActionSelectInstructions() throws IOException {
        when(menu.isSelectedInstructions()).thenReturn(true);

        menuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game).setState(any(InstructionsMenuState.class));
    }

    @Test
    void testNextActionSelectCustomize() throws IOException {
        when(menu.isSelectedCustomize()).thenReturn(true);

        menuController.nextAction(game, GUI.ACTION.SELECT, System.currentTimeMillis());

        verify(game).setState(any(CustomizeMenuState.class));
    }

    @Test
    void testNextActionDefaultCase() throws IOException {
        menuController.nextAction(game, GUI.ACTION.NONE, System.currentTimeMillis());

        verifyNoInteractions(menu);
        verifyNoInteractions(game);
    }
}