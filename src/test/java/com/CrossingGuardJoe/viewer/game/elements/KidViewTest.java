package com.CrossingGuardJoe.viewer.game.elements;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.elements.Kid;
import com.CrossingGuardJoe.viewer.images.defined.KidImages;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class KidViewTest {

    private KidView kidView;
    private Kid kid;
    private GUI gui;

    @BeforeEach
    void setUp() {
        kidView = new KidView();
        kid = mock(Kid.class);
        gui = mock(GUI.class);
    }

    @Test
    void testDrawKidHit() {
        Position position = new Position(10, 20);
        when(kid.getPosition()).thenReturn(position);
        when(kid.getIsHit()).thenReturn(true);

        kidView.draw(kid, gui);

        verify(gui).drawImage(position, KidImages.getKidHitImage());
    }

    @Test
    void testDrawKidSelected() {
        Position position = new Position(10, 20);
        when(kid.getPosition()).thenReturn(position);
        when(kid.getIsHit()).thenReturn(false);
        when(kid.isSelected()).thenReturn(true);

        kidView.draw(kid, gui);

        verify(gui).drawImage(new Position(17, 0), ToolImages.getArrowDownImage());
    }

    @Test
    void testDrawKidWalkingFirstHalf() {
        Position position = new Position(10, 20);
        when(kid.getPosition()).thenReturn(position);
        when(kid.getIsHit()).thenReturn(false);
        when(kid.isSelected()).thenReturn(false);
        when(kid.getWalkingState()).thenReturn(true);
        when(kid.isFirstHalfOfMovement()).thenReturn(true);

        kidView.draw(kid, gui);

        verify(gui).drawImage(position, KidImages.getKidWalkImage());
        verify(kid).setFirstHalfOfMovement(false);
    }

    @Test
    void testDrawKidWalkingSecondHalf() {
        Position position = new Position(10, 20);
        when(kid.getPosition()).thenReturn(position);
        when(kid.getIsHit()).thenReturn(false);
        when(kid.isSelected()).thenReturn(false);
        when(kid.getWalkingState()).thenReturn(true);
        when(kid.isFirstHalfOfMovement()).thenReturn(false);

        kidView.draw(kid, gui);

        verify(gui).drawImage(position, KidImages.getKidStandImage());
        verify(kid).setFirstHalfOfMovement(true);
    }

    @Test
    void testDrawKidStanding() {
        Position position = new Position(10, 20);
        when(kid.getPosition()).thenReturn(position);
        when(kid.getIsHit()).thenReturn(false);
        when(kid.isSelected()).thenReturn(false);
        when(kid.getWalkingState()).thenReturn(false);

        kidView.draw(kid, gui);

        verify(gui).drawImage(position, KidImages.getKidStandImage());
    }
}