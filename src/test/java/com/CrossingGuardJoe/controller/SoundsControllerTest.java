package com.CrossingGuardJoe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static com.CrossingGuardJoe.controller.Sounds.SFX;

class SoundsControllerTest {

    @Mock
    private Sounds menuBgm, gameBgm, customizeBgm, instructionsBgm;
    @Mock
    private Sounds select, enter, flipPage;
    @Mock
    private Sounds levelUp;
    @Mock
    private Sounds joePass1, joePass2, joeStop, joeHit;
    @Mock
    private Sounds kidWalk1, kidStop1, kidStop2, kidHit, kidScore;
    @Mock
    private Sounds carBreak, gameOver, victoryBgm;
    private SoundsController soundsController;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        Constructor<SoundsController> constructor = SoundsController.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        soundsController = constructor.newInstance();

        Field menuBgmField = SoundsController.class.getDeclaredField("menuBgm");
        menuBgmField.setAccessible(true);
        menuBgmField.set(soundsController, menuBgm);

        Field gameBgmField = SoundsController.class.getDeclaredField("gameBgm");
        gameBgmField.setAccessible(true);
        gameBgmField.set(soundsController, gameBgm);

        Field joePass1Field = SoundsController.class.getDeclaredField("joePass1");
        joePass1Field.setAccessible(true);
        joePass1Field.set(soundsController, joePass1);

        Field joePass2Field = SoundsController.class.getDeclaredField("joePass2");
        joePass2Field.setAccessible(true);
        joePass2Field.set(soundsController, joePass2);

        Field customizeBgmField = SoundsController.class.getDeclaredField("customizeBgm");
        customizeBgmField.setAccessible(true);
        customizeBgmField.set(soundsController, customizeBgm);

        Field instructionsBgmField = SoundsController.class.getDeclaredField("instructionsBgm");
        instructionsBgmField.setAccessible(true);
        instructionsBgmField.set(soundsController, instructionsBgm);

        Field selectField = SoundsController.class.getDeclaredField("select");
        selectField.setAccessible(true);
        selectField.set(soundsController, select);

        Field enterField = SoundsController.class.getDeclaredField("enter");
        enterField.setAccessible(true);
        enterField.set(soundsController, enter);

        Field flipPageField = SoundsController.class.getDeclaredField("flipPage");
        flipPageField.setAccessible(true);
        flipPageField.set(soundsController, flipPage);

        Field levelUpField = SoundsController.class.getDeclaredField("levelUp");
        levelUpField.setAccessible(true);
        levelUpField.set(soundsController, levelUp);

        Field joeStopField = SoundsController.class.getDeclaredField("joeStop");
        joeStopField.setAccessible(true);
        joeStopField.set(soundsController, joeStop);

        Field joeHitField = SoundsController.class.getDeclaredField("joeHit");
        joeHitField.setAccessible(true);
        joeHitField.set(soundsController, joeHit);

        Field kidWalk1Field = SoundsController.class.getDeclaredField("kidWalk1");
        kidWalk1Field.setAccessible(true);
        kidWalk1Field.set(soundsController, kidWalk1);

        Field kidStop1Field = SoundsController.class.getDeclaredField("kidStop1");
        kidStop1Field.setAccessible(true);
        kidStop1Field.set(soundsController, kidStop1);

        Field kidStop2Field = SoundsController.class.getDeclaredField("kidStop2");
        kidStop2Field.setAccessible(true);
        kidStop2Field.set(soundsController, kidStop2);

        Field kidHitField = SoundsController.class.getDeclaredField("kidHit");
        kidHitField.setAccessible(true);
        kidHitField.set(soundsController, kidHit);

        Field kidScoreField = SoundsController.class.getDeclaredField("kidScore");
        kidScoreField.setAccessible(true);
        kidScoreField.set(soundsController, kidScore);

        Field carBreakField = SoundsController.class.getDeclaredField("carBreak");
        carBreakField.setAccessible(true);
        carBreakField.set(soundsController, carBreak);

        Field gameOverField = SoundsController.class.getDeclaredField("gameOver");
        gameOverField.setAccessible(true);
        gameOverField.set(soundsController, gameOver);

        Field victoryBgmField = SoundsController.class.getDeclaredField("victoryBgm");
        victoryBgmField.setAccessible(true);
        victoryBgmField.set(soundsController, victoryBgm);
    }

    @Test
    void testPlayBgmSounds() {
        soundsController.play(SFX.MENUBGM);
        verify(menuBgm).loop(0.2f);

        soundsController.play(SFX.GAMEBGM);
        verify(gameBgm).loop(0.15f);

        soundsController.play(SFX.CUSTOMIZEBGM);
        verify(customizeBgm).loop(1f);

        soundsController.play(SFX.INSTRUCTIONSBGM);
        verify(instructionsBgm).loop(0.5f);

        soundsController.play(SFX.VICTORYBGM);
        verify(victoryBgm).loop(1f);
    }

    @Test
    void testPlayMenuSounds() {
        soundsController.play(SFX.SELECT);
        verify(select).play(0.5f);

        soundsController.play(SFX.ENTER);
        verify(enter).play(0.5f);

        soundsController.play(SFX.FLIPPAGE);
        verify(flipPage).play(0.7f);
    }

    @Test
    void testPlayGameSounds() {
        soundsController.play(SFX.LEVELUP);
        verify(levelUp).play(1f);

        soundsController.play(SFX.JOEPASS1);
        verify(joePass1).play(0.5f);

        soundsController.play(SFX.JOEPASS2);
        verify(joePass2).play(0.5f);

        soundsController.play(SFX.JOESTOP);
        verify(joeStop).play(0.5f);

        soundsController.play(SFX.JOEHIT);
        verify(joeHit).play(0.5f);

        soundsController.play(SFX.KIDWALK1);
        verify(kidWalk1).play(0.5f);

        soundsController.play(SFX.KIDSTOP1);
        verify(kidStop1).play(0.5f);

        soundsController.play(SFX.KIDSTOP2);
        verify(kidStop2).play(0.5f);

        soundsController.play(SFX.KIDHIT);
        verify(kidHit).play(0.5f);

        soundsController.play(SFX.KIDSCORE);
        verify(kidScore).play(0.8f);

        soundsController.play(SFX.CARBREAK);
        verify(carBreak).play(0.5f);

        soundsController.play(SFX.GAMEOVER);
        verify(gameOver).play(1f);
    }

    @Test
    void testPauseMethod() {
        soundsController.pause(SFX.GAMEBGM);
        verify(gameBgm).pause();

        soundsController.pause(SFX.VICTORYBGM);
        verify(victoryBgm).pause();
    }
    @Test
    void testPauseDefaultCase() {
        soundsController.pause(SFX.MENUBGM);

        verifyNoInteractions(gameBgm, victoryBgm);
    }

    @Test
    void testStopMethod() {
        soundsController.stop(SFX.MENUBGM);
        verify(menuBgm).stop();

        soundsController.stop(SFX.GAMEBGM);
        verify(gameBgm).stop();

        soundsController.stop(SFX.CUSTOMIZEBGM);
        verify(customizeBgm).stop();

        soundsController.stop(SFX.INSTRUCTIONSBGM);
        verify(instructionsBgm).stop();

        soundsController.stop(SFX.CARBREAK);
        verify(carBreak).stop();

        soundsController.stop(SFX.VICTORYBGM);
        verify(victoryBgm).stop();
    }

    @Test
    void testStopDefaultCase() {
        soundsController.stop(SFX.KIDSCORE);

        verifyNoInteractions(menuBgm, gameBgm, customizeBgm, instructionsBgm,
                carBreak, victoryBgm);
    }

    @Test
    void testPlayRandomMultipleRuns() {
        boolean joePass1Played = false;
        boolean joePass2Played = false;

        for (int i = 0; i < 100; i++) {
            soundsController.playRandom(SFX.JOEPASS1, SFX.JOEPASS2);

            try {
                verify(joePass1, atMostOnce()).play(anyFloat());
                joePass1Played = true;
            } catch (AssertionError ignored) {}

            try {
                verify(joePass2, atMostOnce()).play(anyFloat());
                joePass2Played = true;
            } catch (AssertionError ignored) {}

            reset(joePass1, joePass2);
        }

        assertTrue(joePass1Played, "Expected SFX.JOEPASS1 to be played at least once.");
        assertTrue(joePass2Played, "Expected SFX.JOEPASS2 to be played at least once.");
    }

    @Test
    void testSingletonInstance() {
        SoundsController instance1 = SoundsController.getInstance();
        SoundsController instance2 = SoundsController.getInstance();

        assertSame(instance1, instance2);
    }
}
