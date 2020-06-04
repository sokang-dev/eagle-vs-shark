package model.Shark;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class NormUtilitySharkDecorator extends SharkDecorator {

    public NormUtilitySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.UtilityShark);
    }


    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        // Adjacent pieces of the same team including diagonals
        Set<Tile> validSpecials = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                if (Tile.isOutOfBounds(x, y))
                    continue;

                if (board.getTile(x, y).getPiece() != null) {
                    if (board.getPiece(x, y).getPieceType() == this.getPieceType()) {
                        validSpecials.add(board.getTile(x, y));
                    }
                }
            }
        }

        validSpecials.remove(currentCoord);
        return validSpecials;
    }

    public void special(Tile destinationTile) {
        for (Tile t : super.getValidSpecials()) {
            t.getPiece().setHealth(t.getPiece().getHealth() + 1);
        }
    }

    @Override
    public Piece transform() {
        Piece newForm = new AltUtilitySharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
