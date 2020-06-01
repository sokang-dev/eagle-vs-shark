package model.Shark;

import model.interfaces.Piece;
import resources.Sprites;

public class NormDummySharkDecorator extends SharkDecorator {

    public NormDummySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.Shark);
    }

    // Used for debugging only - returns ANSI_BLUE D
    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
