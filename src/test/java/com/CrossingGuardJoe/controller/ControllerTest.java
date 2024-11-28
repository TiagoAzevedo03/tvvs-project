package com.CrossingGuardJoe.controller;

import com.CrossingGuardJoe.Game;
import com.CrossingGuardJoe.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    private Controller<Object> controller;
    private Object model;

    @BeforeEach
    void setUp() {
        model = new Object();
        controller = new Controller(model) {
            @Override
            public void nextAction(Game game, GUI.ACTION action, long time) {}
        };
    }

    @Test
    void testGetModel() {
        assertEquals(model, controller.getModel());
    }
}