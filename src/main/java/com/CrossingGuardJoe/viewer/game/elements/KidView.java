package com.CrossingGuardJoe.viewer.game.elements;

import com.CrossingGuardJoe.gui.GUI;
import com.CrossingGuardJoe.model.Position;
import com.CrossingGuardJoe.model.game.elements.Kid;
import com.CrossingGuardJoe.viewer.images.defined.ToolImages;
import com.CrossingGuardJoe.viewer.images.defined.KidImages;

public class KidView extends ElementViewer<Kid> {
    @Override
    public void draw(Kid kid, GUI gui) {
        if (kid.getIsHit()) {
            gui.drawImage(kid.getPosition(), KidImages.getKidHitImage());
            return;
        }
        if (kid.isSelected()) {
            gui.drawImage(new Position(kid.getPosition().getX() + 7, kid.getPosition().getY() - 20), ToolImages.getArrowDownImage());
        }
        if (kid.getWalkingState()) {
            if (kid.isFirstHalfOfMovement()) {
                gui.drawImage(kid.getPosition(), KidImages.getKidWalkImage());
            } else {
                gui.drawImage(kid.getPosition(), KidImages.getKidStandImage());
            }
            kid.setFirstHalfOfMovement(!kid.isFirstHalfOfMovement());
        } else {
            gui.drawImage(kid.getPosition(), KidImages.getKidStandImage());
        }
    }
}
