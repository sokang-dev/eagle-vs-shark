package model.Shark;

import model.interfaces.Piece;
import resources.Sprites;

public class NormUtilitySharkDecorator extends SharkDecorator {

    public NormUtilitySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.UtilityShark);
    }

    @Override
    public Piece transform() {
        return null;
    }
}
