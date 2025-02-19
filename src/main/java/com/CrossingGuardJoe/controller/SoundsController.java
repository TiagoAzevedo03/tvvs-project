package com.CrossingGuardJoe.controller;

import java.util.Random;

import static com.CrossingGuardJoe.controller.Sounds.SFX;

public class SoundsController {
    private final Sounds menuBgm, gameBgm, customizeBgm, instructionsBgm;
    private final Sounds select, enter, flipPage;
    private final Sounds levelUp;
    private final Sounds joePass1, joePass2, joeStop, joeHit;
    private final Sounds kidWalk1, kidStop1, kidStop2, kidHit, kidScore;
    private final Sounds carBreak;
    private final Sounds gameOver, victoryBgm;
    private static final float defaultVolume = 0.5f;
    private static SoundsController soundsController;

    private SoundsController() {
        menuBgm = new Sounds("sounds/bgm/MENUBGM.wav");
        gameBgm = new Sounds("sounds/bgm/GAMEBGM.wav");
        customizeBgm = new Sounds("sounds/bgm/CUSTOMIZEBGM.wav");
        instructionsBgm = new Sounds("sounds/bgm/INSTRUCTIONSBGM.wav");

        select = new Sounds("sounds/menu/SELECT.wav");
        enter = new Sounds("sounds/menu/ENTER.wav");
        flipPage = new Sounds("sounds/menu/FLIPPAGE.wav");

        levelUp = new Sounds("sounds/game/LEVELUP.wav");

        joePass1 = new Sounds("sounds/game/joe/JOEPASS1.wav");
        joePass2 = new Sounds("sounds/game/joe/JOEPASS2.wav");
        joeStop = new Sounds("sounds/game/joe/JOESTOP.wav");
        joeHit = new Sounds("sounds/game/joe/JOEHIT.wav");

        kidWalk1 = new Sounds("sounds/game/kid/KIDWALK1.wav");
        kidStop1 = new Sounds("sounds/game/kid/KIDSTOP1.wav");
        kidStop2 = new Sounds("sounds/game/kid/KIDSTOP2.wav");
        kidHit = new Sounds("sounds/game/kid/KIDHIT.wav");
        kidScore = new Sounds("sounds/game/kid/KIDSCORE.wav");

        carBreak = new Sounds("sounds/game/car/CARBREAK.wav");

        gameOver = new Sounds("sounds/game/GAMEOVER.wav");
        victoryBgm = new Sounds("sounds/bgm/VICTORYBGM.wav");
    }

    public static SoundsController getInstance() {
        if (soundsController == null) {
            soundsController = new SoundsController();
        }
        return soundsController;
    }

    public void play(SFX sfx) {
        switch (sfx) {
            case MENUBGM -> menuBgm.loop(0.2f);
            case GAMEBGM -> gameBgm.loop(0.15f);
            case CUSTOMIZEBGM -> customizeBgm.loop(1f);
            case INSTRUCTIONSBGM -> instructionsBgm.loop(0.5f);
            case SELECT -> select.play(defaultVolume);
            case ENTER -> enter.play(defaultVolume);
            case FLIPPAGE -> flipPage.play(0.7f);
            case LEVELUP -> levelUp.play(1f);
            case JOEPASS1 -> joePass1.play(defaultVolume);
            case JOEPASS2 -> joePass2.play(defaultVolume);
            case JOESTOP -> joeStop.play(defaultVolume);
            case JOEHIT -> joeHit.play(defaultVolume);
            case KIDWALK1 -> kidWalk1.play(defaultVolume);
            case KIDSTOP1 -> kidStop1.play(defaultVolume);
            case KIDSTOP2 -> kidStop2.play(defaultVolume);
            case KIDHIT -> kidHit.play(defaultVolume);
            case KIDSCORE -> kidScore.play(0.8f);
            case CARBREAK -> carBreak.play(defaultVolume);
            case GAMEOVER -> gameOver.play(1f);
            case VICTORYBGM -> victoryBgm.loop(1f);
        }
    }

    public void pause(SFX sfx) {
        switch (sfx) {
            case GAMEBGM -> gameBgm.pause();
            case VICTORYBGM -> victoryBgm.pause();
            default -> {}
        }
    }

    public void stop(SFX sfx) {
        switch(sfx) {
            case MENUBGM -> menuBgm.stop();
            case GAMEBGM -> gameBgm.stop();
            case CUSTOMIZEBGM -> customizeBgm.stop();
            case INSTRUCTIONSBGM -> instructionsBgm.stop();
            case CARBREAK -> carBreak.stop();
            case VICTORYBGM -> victoryBgm.stop();
            default -> {}
        }
    }

    public static void playRandom(SFX sfx1, SFX sfx2) {
        Random random = new Random();
        if (random.nextBoolean()) {
            getInstance().play(sfx1);
        } else {
            getInstance().play(sfx2);
        }
    }
}
