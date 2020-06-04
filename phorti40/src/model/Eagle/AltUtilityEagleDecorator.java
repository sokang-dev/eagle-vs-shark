package model.Eagle;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.Set;

public class AltUtilityEagleDecorator extends EagleDecorator {

    public AltUtilityEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        // TODO: Change sprite to Alt Utility Eagle
        super.setSprite(Sprites.UtilityEagle);
    }

    // AltUtilityEagle's special has the same range as attack
    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        return super.getValidAttacks(currentCoord, board);
    }

    @Override
    public void special(Tile tile, Board board) {
        int currentX = super.getTile().getX();
        int currentY = super.getTile().getY();

        for (Tile t : super.getValidSpecials()) {
            int diffX = t.getX() - currentX;
            int diffY = t.getY() - currentY;

            int newX = t.getX() + diffX;
            int newY = t.getY() + diffY;

            if (board.getTile(newX, newY) == null)
                continue;

            // If tile is not occupied, move the piece to the new tile
            Tile newTile = board.getTile(newX, newY);
            if (newTile.getPiece() == null)
                t.getPiece().move(newTile, board);
        }
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormUtilityEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }
}
