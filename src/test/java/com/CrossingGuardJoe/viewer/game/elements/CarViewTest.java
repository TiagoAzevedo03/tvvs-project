package com.CrossingGuardJoe.viewer.game.elements;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.viewer.images.defined.CarImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CarViewTest {

    private CarView carView;
    private Car car;
    private GUI gui;

    @BeforeEach
    void setUp() {
        carView = new CarView();
        car = mock(Car.class);
        gui = mock(GUI.class);
    }

    @Test
    void testDraw() {
        Position position = new Position(10, 20);
        when(car.getPosition()).thenReturn(position);

        carView.draw(car, gui);

        verify(gui).drawImage(position, CarImage.getCarImage());
    }
}