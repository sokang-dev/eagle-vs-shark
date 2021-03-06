package model.Shark;

import model.Board;
import model.Enums.StatusType;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AltUtilitySharkDecorator extends SharkDecorator {

    public AltUtilitySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AltUtilityShark);
    }

    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        // Adjacent pieces of the same team including diagonals
        Set<Tile> validSpecials = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                if (board.getTile(x, y) == null)
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

    @Override
    public void special(Tile destinationTile, Board board) {
        destinationTile.getPiece().setBaseMovement(destinationTile.getPiece().getBaseMovement() + 1);
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormUtilitySharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
