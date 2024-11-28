package com.CrossingGuardJoe.viewer.game.elements;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.viewer.images.defined.JoeImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class JoeViewTest {

    private JoeView joeView;
    private Joe joe;
    private GUI gui;

    @BeforeEach
    void setUp() {
        joeView = new JoeView();
        joe = mock(Joe.class);
        gui = mock(GUI.class);
    }

    @Test
    void testDrawJoeHitLeft() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsHit()).thenReturn(true);
        when(joe.getHitLeft()).thenReturn(true);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoeHitleftImage());
        verify(joe).isNotHit();
    }

    @Test
    void testDrawJoeHitRight() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsHit()).thenReturn(true);
        when(joe.getHitLeft()).thenReturn(false);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoeHitrightImage());
        verify(joe).isNotHit();
    }

    @Test
    void testDrawJoeWalkingLeftFirstHalf() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsWalkingState()).thenReturn(true);
        when(joe.isFirstHalfOfMovement()).thenReturn(true);
        when(joe.getIsWalkingToLeft()).thenReturn(true);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoeWalkleftImage());
        verify(joe).setFirstHalfOfMovement(false);
    }

    @Test
    void testDrawJoeWalkingRightFirstHalf() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsWalkingState()).thenReturn(true);
        when(joe.isFirstHalfOfMovement()).thenReturn(true);
        when(joe.getIsWalkingToLeft()).thenReturn(false);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoeWalkrightImage());
        verify(joe).setFirstHalfOfMovement(false);
    }

    @Test
    void testDrawJoeWalkingSecondHalf() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsWalkingState()).thenReturn(true);
        when(joe.isFirstHalfOfMovement()).thenReturn(false);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoeWalksecondhalfImage());
        verify(joe).setFirstHalfOfMovement(true);
    }

    @Test
    void testDrawJoeRaisingStopSign() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsRaisingStopSign()).thenReturn(true);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoeStopImage());
    }

    @Test
    void testDrawJoePassSign() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsPassSign()).thenReturn(true);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoePassImage());
    }

    @Test
    void testDrawJoeStand() {
        Position position = new Position(10, 20);
        when(joe.getPosition()).thenReturn(position);
        when(joe.getIsHit()).thenReturn(false);
        when(joe.getIsWalkingState()).thenReturn(false);
        when(joe.getIsRaisingStopSign()).thenReturn(false);
        when(joe.getIsPassSign()).thenReturn(false);

        joeView.draw(joe, gui);

        verify(gui).drawImage(position, JoeImages.getJoeStandImage());
    }
}