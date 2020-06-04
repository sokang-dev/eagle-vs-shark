package model.Eagle;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.Set;

public class AltUtilityEagleDecorator extends EagleDecorator {

    public AltUtilityEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.UtilityEagle);
    }

    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        return super.calcValidSpecials(currentCoord, board);
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormUtilityEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[31m D \u001B[0m";
    }
}
