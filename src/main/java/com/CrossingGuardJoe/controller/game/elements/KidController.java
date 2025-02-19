package com.CrossingGuardJoe.controller.game.elements;

import com.CrossingGuardJoe.controller.game.GameController;
import com.CrossingGuardJoe.controller.Sounds;
import com.CrossingGuardJoe.controller.SoundsController;
import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.Road;
import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Joe;
import com.CrossingGuardJoe.model.game.elements.Kid;
import com.CrossingGuardJoe.Game;

import java.util.*;

import static com.CrossingGuardJoe.controller.game.AuxCheckRange.isInRangeCarKid;
import static com.CrossingGuardJoe.controller.game.AuxCheckRange.isInRangeJoeKid;

public class KidController extends GameController {
    private static final int KID_STEP = 3;
    private static final double KID_SPEED = 0.005;
    private static final int MIN_KID_DISTANCE = 9;
    private static final int PASS_POINT = 90;
    private static final int MIN_Y_DISTANCE = 0;
    private static final int MAX_Y_DISTANCE = 500;
    private static final int Y_AFTER_HIT = 55;
    private long lastUpdateTime;
    private final Joe joe = getModel().getJoe();
    private Kid selectedKid;
    private final List<Kid> sentKids = new ArrayList<>();
    private int nextKidToMoveInQueueIndex;
    private boolean kidMovedInQueue = false;
    private int countKidsToNextLevel = 0;

    public KidController(Road road) {
        super(road);
        lastUpdateTime = System.currentTimeMillis();
    }

    public void moveKid(Kid kid) {
        KidAction(kid, new Position(kid.getPosition().getX() - KID_STEP, kid.getPosition().getY()), 'p');
    }

    public void moveKidAfterHit(Car car, Kid kid, int hitX) {
        kid.setPosition(new Position(hitX, car.getPosition().getY() + Y_AFTER_HIT));
    }

    public void stopKid(Kid kid) {
        KidAction(kid, kid.getPosition(), 's');
    }

    public void KidAction(Kid kid, Position position, char passOrStop) {
        if (passOrStop == 'p') {
            kid.setWalking();
            kid.setPosition(position);
        } else if (passOrStop == 's') {
            kid.setNotWalking();
        }
    }

    public boolean isFirstKid(Kid kid) {
        List<Kid> kids = getModel().getKids();
        return kids.indexOf(kid) == 0;
    }

    public boolean inMinDistance(Kid kid) {
        List<Kid> kids = getModel().getKids();
        if (!isFirstKid(kid)) {
            Kid kidInFront = kids.get(kids.indexOf(kid) - 1);
            if (!kidInFront.getIsHit()) {
                return kid.getPosition().getX() - kidInFront.getPosition().getX() <= MIN_KID_DISTANCE + 1;
            }
        }
        return false;
    }

    public boolean canContinueWalk(Kid kid) {
        if (!isFirstKid(kid) && inMinDistance(kid)) {
            stopKid(kid);
            return false;
        }
        return true;
    }

    public void repositionQueue() {
        List<Kid> kids = getModel().getKids();

        for (int i = nextKidToMoveInQueueIndex; i < kids.size(); i++) {
            Kid kidToMoveInQueue = kids.get(i);
            int movesLeft = kidToMoveInQueue.getMovesInQueueLeft();

            if (movesLeft > 0) {
                if (canContinueWalk(kidToMoveInQueue)) {
                    moveKid(kidToMoveInQueue);
                    kidMovedInQueue = true;
                }
                if (kidMovedInQueue) {
                    kidToMoveInQueue.addMovesInQueueLeft(-1);
                    kidMovedInQueue = false;
                }
            } else {
                stopKid(kidToMoveInQueue);
                nextKidToMoveInQueueIndex++;
            }
        }
    }

    @Override
    public void nextAction(Game game, GUI.ACTION action, long time) {
        List<Kid> kids = getModel().getKids();

        boolean joeInRange = false;

        for (Kid kid : kids) {
            if (isInRangeJoeKid(joe, kid) && kid.getPosition().getX() > PASS_POINT) {
                joeInRange = true;
                selectedKid = kid;
            }
            kid.setNotSelected();
        }

        if (joeInRange) {
            selectedKid.setSelected();
        }

        if (action == GUI.ACTION.DOWN && joeInRange && !selectedKid.getIsHit() && canContinueWalk(selectedKid)) {
            selectedKid.setWalking();
            SoundsController.getInstance().play(Sounds.SFX.KIDWALK1);
            if (!sentKids.contains(selectedKid)) {
                sentKids.add(selectedKid);
                nextKidToMoveInQueueIndex = kids.indexOf(selectedKid) + 1;

                for (int i = nextKidToMoveInQueueIndex; i < kids.size(); i++) {
                    Kid kid = kids.get(i);
                    stopKid(kid);
                    kid.addMovesInQueueLeft(MIN_KID_DISTANCE / KID_STEP);
                }
            }
        }

        if (action == GUI.ACTION.UP && joeInRange && selectedKid.getWalkingState()) {
            selectedKid.setNotWalking();
            SoundsController.playRandom(Sounds.SFX.KIDSTOP1, Sounds.SFX.KIDSTOP2);
        }

        if (time - lastUpdateTime > KID_SPEED && !kids.isEmpty()) {
            repositionQueue();
            for (Kid kid : sentKids) {
                if (kid.getWalkingState() && canContinueWalk(kid)) {
                    moveKid(kid);
                }
                if (isInRangeJoeKid(joe, kid) && joe.getIsRaisingStopSign() && kid.getPosition().getX() > PASS_POINT) {
                    stopKid(kid);
                }
            }
            lastUpdateTime = time;
        }

        checkCollisions();
        checkPoints();
        checkCountToNextLevel();
        checkLevelUp();
    }

    private void checkCollisions() {
        List<Car> cars = getModel().getCars();
        List<Kid> kids = getModel().getKids();
        int hitX;

        for (Kid kid : kids) {
            for (Car car : cars) {
                if (isInRangeCarKid(car, kid)) {
                    hitX = kid.getPosition().getX();
                    kid.isHit();
                    kid.setNotWalking();
                    checkDeathCount(kid);
                    moveKidAfterHit(car, kid, hitX);
                }
            }
        }
    }

    private void checkDeathCount(Kid kid) {
        if (!kid.getDeathCounted()) {
            SoundsController.getInstance().play(Sounds.SFX.CARBREAK);
            SoundsController.getInstance().play(Sounds.SFX.KIDHIT);
            joe.removeHeart();
            kid.setDead();
        }
    }

    public void checkPoints() {
        for (Kid kid : getModel().getKids()) {
            if (kid.getPosition().getX() < MIN_Y_DISTANCE && !kid.getPass()) {
                kid.setPass();
                joe.addScore(kid.getPoints());
                SoundsController.getInstance().play(Sounds.SFX.KIDSCORE);
            }
        }
    }

    public void checkCountToNextLevel() {
        for (Kid kid : getModel().getKids()) {
            if (!kid.getCounted()) {
                if (kid.getPass() || (kid.getDeathCounted() && kid.getPosition().getY() >= MAX_Y_DISTANCE)) {
                    countKidsToNextLevel++;
                    kid.setCountToNextLevel();
                }
            }
        }
    }

    public int nextLevelNumberKids(int currentLevel) {
        return switch (currentLevel) {
            case 2 -> 4;
            case 3 -> 5;
            case 4 -> 6;
            case 5 -> 7;
            case 6 -> 8;
            case 7 -> 9;
            case 8 -> 10;
            case 9 -> 11;
            default -> 12;
        };
    }

    private void checkLevelUp() {
        if (countKidsToNextLevel == getModel().getKids().size()) {
            countKidsToNextLevel = 0;
            sentKids.clear();
            getModel().levelUp();
            SoundsController.getInstance().play(Sounds.SFX.LEVELUP);
            getModel().setKidsNextLevel(nextLevelNumberKids(getModel().getCurrentLevel()));
            nextKidToMoveInQueueIndex = 0;
        }
    }
}

