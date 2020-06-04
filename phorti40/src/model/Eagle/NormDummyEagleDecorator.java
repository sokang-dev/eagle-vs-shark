package model.Eagle;

import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

public class NormDummyEagleDecorator extends EagleDecorator {

    public NormDummyEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.Eagle);
    }

    @Override
    public Piece transform() {
        Piece newForm = new AltDummyEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[31m D \u001B[0m";
    }
}