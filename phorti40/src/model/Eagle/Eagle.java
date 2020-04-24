package model.Eagle;

import model.Enums.PieceType;
import model.Piece;
import model.Tile;

public abstract class Eagle extends Piece {

    protected int baseMovement = 2;

    public Eagle() {
        super(PieceType.Eagle);
        super.setBaseMovement(baseMovement);
    }
}
