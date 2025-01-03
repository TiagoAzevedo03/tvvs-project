package com.CrossingGuardJoe.states;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.controller.Controller;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.viewer.Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class StateTest {

    private State<Object> state;
    private Game game;
    private GUI gui;
    private Controller<Object> controller;
    private Viewer<Object> viewer;
    private Object model;

    @BeforeEach
    void setUp() {
        model = new Object();
        controller = mock(Controller.class);
        viewer = mock(Viewer.class);
        game = mock(Game.class);
        gui = mock(GUI.class);

        state = new State<>(model) {
            @Override
            public Controller<Object> getController() {
                return controller;
            }

            @Override
            public Viewer<Object> getViewer() {
                return viewer;
            }
        };
    }

    @Test
    void testStep() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);

        state.step(game, gui, 100L);

        verify(controller).nextAction(game, GUI.ACTION.NONE, 100L);
        verify(viewer).draw(gui);
    }
}