package model.Eagle;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AltUtilityEagleDecorator extends EagleDecorator {

    public AltUtilityEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.AltUtilityEagle);
    }

    // All adjacent pieces including diagonals and allies
    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        Set<Tile> validSpecials = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                if (board.getTile(x, y) == null)
                    continue;

                if (board.getTile(x, y).getPiece() != null) {
                        validSpecials.add(board.getTile(x, y));
                }
            }
        }
        validSpecials.remove(currentCoord);
        return validSpecials;
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

    @Override
    public String toString() {
        return "\u001B[31m D \u001B[0m";
    }
}
