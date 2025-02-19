package com.CrossingGuardJoe.viewer.menu;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.menu.Option;
import com.CrossingGuardJoe.model.menu.PauseMenu;
import com.CrossingGuardJoe.viewer.Viewer;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;

public class PauseMenuViewer extends Viewer<PauseMenu> implements MenuInformationDrawer {

    public PauseMenuViewer(PauseMenu pauseMenu) {
        super(pauseMenu);
    }

    @Override
    public void drawElements(GUI gui) {
        drawTitle(gui);

        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            Option option = getModel().getOption(i);
            Position optionPosition = option.position();

            //options text
            gui.drawText(optionPosition, option.name(), "#FFFFFF");

            if (getModel().isSelectedOption(i)) {
                gui.drawImage(new Position(optionPosition.getX() - 15, optionPosition.getY()), ToolImages.getArrowRightImage());
            }
        }
    }

    @Override
    public void drawTitle(GUI gui) {
        gui.drawText(new Position(207, 100), "Game paused", "#FFFFFF");
    }

    @Override
    public void drawInformation(GUI gui) {

    }
}
