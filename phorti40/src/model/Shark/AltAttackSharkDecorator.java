package model.Shark;

import model.Eagle.NormAttackEagleDecorator;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

public class AltAttackSharkDecorator extends SharkDecorator{

    public AltAttackSharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AttackShark);
    }

    // Attacks normally, but additionally gains movement on killing a piece
    @Override
    public void attack(Tile tile) {
        super.attack(tile);

        if (tile.getPiece() == null)
            super.setBaseMovement(super.getBaseMovement() + 1);
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormAttackSharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }
}
