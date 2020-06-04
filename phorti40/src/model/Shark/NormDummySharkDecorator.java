package model.Shark;

import model.interfaces.Piece;
import resources.Sprites;

public class NormDummySharkDecorator extends SharkDecorator {

    public NormDummySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.Shark);
    }

    @Override
    public Piece transform() {
        return null;
    }
}
