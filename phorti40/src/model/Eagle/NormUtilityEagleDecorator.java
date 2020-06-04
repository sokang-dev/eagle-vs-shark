package model.Eagle;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.Set;

public class NormUtilityEagleDecorator extends EagleDecorator {

    public NormUtilityEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.UtilityEagle);
    }

    // NormUtilityEagle special has the same range and target as its attack
    @Override
    public Set<Tile> getValidSpecials(Tile currentCoord, Board board) {
        return super.getValidAttacks(currentCoord, board);
    }

    @Override
    public boolean hasSpecial() {
        return true;
    }

    @Override
    public void special() {
        System.out.println("Bushhh");
    }
}
