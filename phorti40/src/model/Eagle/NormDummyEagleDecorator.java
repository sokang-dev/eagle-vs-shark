package model.Eagle;

import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

public class NormDummyEagleDecorator extends EagleDecorator {

    public NormDummyEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.Eagle);
    }
    // Used for debugging only - returns ANSI_RED D
    @Override
    public String toString() {
        return "\u001B[31m D \u001B[0m";
    }

    @Override
    public Piece transform() {
        Piece newForm = new AltDummyEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }
}