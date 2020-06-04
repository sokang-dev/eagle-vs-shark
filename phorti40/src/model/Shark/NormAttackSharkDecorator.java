package model.Shark;

import model.interfaces.Piece;
import resources.Sprites;

public class NormAttackSharkDecorator extends SharkDecorator {

    public NormAttackSharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AttackShark);
    }

    // Used for debugging only - returns ANSI_BLUE A
    @Override
    public String toString() {
        return "\u001B[34m A \u001B[0m";
    }
}
