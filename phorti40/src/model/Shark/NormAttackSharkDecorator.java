package model.Shark;

import model.interfaces.Piece;
import resources.Sprites;

public class NormAttackSharkDecorator extends SharkDecorator {

    public NormAttackSharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AttackShark);
    }

    @Override
    public Piece transform() {
        return null;
    }
}
