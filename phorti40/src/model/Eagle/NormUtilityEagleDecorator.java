package model.Eagle;

import model.interfaces.Piece;
import resources.Sprites;

public class NormUtilityEagleDecorator extends EagleDecorator {

    public NormUtilityEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.UtilityEagle);
    }

    // Used for debugging only - returns ANSI_RED U
    @Override
    public String toString() {
        return "\u001B[31m U \u001B[0m";
    }
}
