package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.InstructionsMenu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.images.defined.*;
import com.CrossingGuardJoe.viewer.images.generator.Shape;

public class InstructionsMenuViewer extends Viewer<InstructionsMenu> implements MenuInformationDrawer {
    public InstructionsMenuViewer(InstructionsMenu model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        drawTitle(gui);
        drawInformation(gui);

        gui.drawImage(new Position(20, 50), Shape.RectangleFilledGenerator(920, 410, 'K', 2, '$'));

        switch (getModel().getCurrentPage()) {
            case 1:
                drawPageOne(gui);
                break;
            case 2:
                drawPageTwo(gui);
                break;
            case 3:
                drawPageThree(gui);
                break;
            case 4:
                drawPageFour(gui);
                break;
            case 5:
                drawPageFive(gui);
                break;
        }
    }

    @Override
    public void drawTitle(GUI gui) {
        gui.drawText(new Position(202, 17), "Instructions", "#FFFFFF");
    }

    @Override
    public void drawInformation(GUI gui) {
        gui.drawImage(new Position(4, 4), ToolImages.getKeyEscImage());

        int PAGES_INI_X = 400;
        int PAGES_Y = 474;

        gui.drawImage(new Position(4, 4), ToolImages.getKeyEscImage());

        //text
        gui.drawText(new Position(PAGES_INI_X, PAGES_Y), "Page", "#FFFFFF");
        gui.drawText(new Position(PAGES_INI_X + 54, PAGES_Y), "of", "#FFFFFF");
        gui.drawText(new Position(PAGES_INI_X + 74, PAGES_Y), getModel().getTotalPages(), "#FFFFFF");

        if (getModel().getCurrentPage() == getModel().getTotalPages()) {
            gui.drawText(new Position(PAGES_INI_X + 40, PAGES_Y), getModel().getCurrentPage(), "#D30000");
        } else {
            gui.drawText(new Position(PAGES_INI_X + 40, PAGES_Y), getModel().getCurrentPage(), "#FFFFFF");
        }
    }

    private void drawPageOne(GUI gui) {
        //TEXT
        gui.drawText(new Position(50, 100), "you are Joe", "#FFFFFF");
        gui.drawText(new Position(70, 120), "a crossing guard", "#FFFFFF");
        gui.drawText(new Position(50, 400), "click once to move", "#FFFFFF");

        //JOE
        int JOE_Y = 190;
        gui.drawImage(new Position(150, JOE_Y), JoeImages.getJoeStandImage());
        gui.drawImage(new Position(230, JOE_Y), JoeImages.getJoeWalkleftImage());
        gui.drawImage(new Position(310, JOE_Y), JoeImages.getJoeWalkrightImage());

        //KEY
        int KEY_Y = 310;
        gui.drawImage(new Position(235, KEY_Y), ToolImages.getKeyLeftImage());
        gui.drawImage(new Position(315, KEY_Y), ToolImages.getKeyRightImage());
    }

    private void drawPageTwo(GUI gui) {
        //TEXT
        gui.drawText(new Position(50, 100), "you will help", "#FFFFFF");
        gui.drawText(new Position(70, 120), "the kids to cross the road", "#FFFFFF");
        gui.drawText(new Position(50, 400), "click once to order", "#FFFFFF");

        //JOE
        int JOE_Y = 200;
        gui.drawImage(new Position(170, JOE_Y - 20), JoeImages.getJoeStopImage());
        gui.drawImage(new Position(290, JOE_Y - 20), JoeImages.getJoePassImage());

        //KID
        gui.drawImage(new Position(200, JOE_Y + 20), KidImages.getKidStandImage());
        gui.drawImage(new Position(280, JOE_Y + 20), KidImages.getKidWalkImage());

        //KEY
        int KEY_Y = 310;
        gui.drawImage(new Position(180, KEY_Y), ToolImages.getKeyUpImage());
        gui.drawImage(new Position(295, KEY_Y), ToolImages.getKeyDownImage());
    }

    private void drawPageThree(GUI gui) {
        //TEXT
        gui.drawText(new Position(50, 100), "be careful", "#FFFFFF");
        gui.drawText(new Position(70, 120), "with rude drivers", "#FFFFFF");

        //CAR
        int JOE_Y = 215;
        gui.drawImage(new Position(150, JOE_Y - 20), CarImage.getCarImage());
        gui.drawImage(new Position(280, JOE_Y - 20), CarImage.getCarImage());

        //KID
        gui.drawImage(new Position(190, JOE_Y + 30), KidImages.getKidHitImage());

        //JOE
        gui.drawImage(new Position(320, JOE_Y), JoeImages.getJoeHitrightImage());
    }

    private void drawPageFour(GUI gui) {
        //TEXT
        gui.drawText(new Position(50, 100), "if you lose a kid", "#FFFFFF");
        gui.drawText(new Position(70, 120), "you lose hp", "#FFFFFF");

        int HP_BAR_Y = 230;
        gui.drawImage(new Position(199, HP_BAR_Y - 1), Shape.RectangleFilledGenerator(202, 39, ' ', 2, '$'));
        gui.drawImage(new Position(200, HP_BAR_Y), HUDImages.getHpBarSliceImage());

        int HP_iniX = 208;
        for (int i = 0; i < 3; i++) {
            gui.drawImage(new Position(HP_iniX, HP_BAR_Y + 4), HUDImages.getHPImage());
            HP_iniX += 25;
        }
    }

    private void drawPageFive(GUI gui) {
        //TEXT
        gui.drawText(new Position(50, 100), "try to get", "#FFFFFF");
        gui.drawText(new Position(70, 120), "maximum score", "#FFFFFF");

        int SCORE_BAR_X = 185;
        int SCORE_BAR_Y = 230;

        gui.drawImage(new Position(SCORE_BAR_X - 1, SCORE_BAR_Y - 1), Shape.RectangleFilledGenerator(266, 39, ' ', 2, '$'));

        gui.drawImage(new Position(SCORE_BAR_X, SCORE_BAR_Y), HUDImages.getScoreBarSliceImage());
        gui.drawText(new Position(SCORE_BAR_X + 90, SCORE_BAR_Y + 10), 2590, "#FFFFFF");
    }
}
