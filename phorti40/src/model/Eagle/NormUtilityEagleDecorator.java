package model.Eagle;

import model.Board;
import model.Enums.PieceType;
import model.Enums.StatusType;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class NormUtilityEagleDecorator extends EagleDecorator {

    public NormUtilityEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.UtilityEagle);
    }

    // NormUtilityEagle special range is global
    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        Set<Tile> validSpecials = new HashSet<>();
        Set<Piece> sharkPieces = board.getPiecesByType(PieceType.Shark);

        for (Piece piece : sharkPieces) {
            validSpecials.add(piece.getTile());
        }

        return validSpecials;
    }


    // Stun the target
    @Override
    public void special(Tile tile, Board board) {
        tile.getPiece().setStatus(StatusType.Stun, 1);
    }

    @Override
    public Piece transform() {
        Piece newForm = new AltUtilityEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    // Used for debugging only - returns ANSI_RED U
    @Override
    public String toString() {
        return "\u001B[31m U \u001B[0m";
    }
}
