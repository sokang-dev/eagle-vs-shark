package model.Eagle;

import model.Board;
import model.Enums.StatusType;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.Set;

public class AltAttackEagleDecorator extends EagleDecorator {

    public AltAttackEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        // TODO: Change to AltAttackEagle sprite
        super.setSprite(Sprites.AttackEagle);
    }

    // AltAttackEagle's special range is the same as its attack
    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        return super.getValidAttacks(currentCoord, board);
    }

    @Override
    public void special(Tile tile, Board board) {
        for (Tile t : super.getValidSpecials()) {
            t.getPiece().die();
        }

        super.setStatus(StatusType.Stun, 2);
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormAttackEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }
}
