package model.Eagle;

import model.Board;
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

    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        return super.getValidMoves(currentCoord, movement, board);
    }

    @Override
    public Set<Tile> getValidAttacks(Tile currentCoord, Board board) {
        return super.getValidAttacks(currentCoord, board);
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormAttackEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }
}
