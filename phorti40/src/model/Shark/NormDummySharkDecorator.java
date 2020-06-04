package model.Shark;

import model.Eagle.NormAttackEagleDecorator;
import model.interfaces.Piece;
import resources.Sprites;

public class NormDummySharkDecorator extends SharkDecorator {

    public NormDummySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.Shark);
    }

    @Override
    public void attack(Piece piece) {
        super.attack(piece);
        super.setHealth(super.getHealth() + 1);
    }

    @Override
    public Piece transform() {
        Piece newForm = new AltDummySharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }


}
