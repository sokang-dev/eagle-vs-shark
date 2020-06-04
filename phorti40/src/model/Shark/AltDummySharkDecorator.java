package model.Shark;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AltDummySharkDecorator extends SharkDecorator {

    public AltDummySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AltShark);
    }

    // AltAttackEagle's special range is the same as its attack
    @Override
    public Set<Tile> getValidSpecials(Tile currentCoord, Board board) {
        Set<Tile> validSpecials = new HashSet<>();

        validSpecials.add(currentCoord);

        return validSpecials;
    }

    @Override
    public void special(Set<Tile> validSpecials) {
        for (Tile tile : validSpecials) {
            super.setHealth(super.getHealth() + 1);
        }
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormDummySharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }
}
