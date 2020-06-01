package model.Eagle;

import model.interfaces.Piece;
import resources.Sprites;

public class NormDummyEagleDecorator extends EagleDecorator {

    public NormDummyEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.Eagle);
    }
}
