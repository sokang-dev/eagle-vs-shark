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

    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
