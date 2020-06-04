package model.Shark;

import model.interfaces.Piece;
import resources.Sprites;


public class NormUtilitySharkDecorator extends SharkDecorator {

    public NormUtilitySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.UtilityShark);
    }

    // Used for debugging only - returns ANSI_BLUE U
    @Override
    public String toString() {
        return "\u001B[34m U \u001B[0m";
    }
    @Override
    public Piece transform() {
        return null;
    }
}
