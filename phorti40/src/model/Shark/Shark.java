package model.Shark;

import model.Enums.PieceType;
import model.Piece;

public abstract class Shark extends Piece {

    protected int baseMovement = 1;

    public Shark() {
        super(PieceType.Shark);
        super.setBaseMovement(baseMovement);
    }
}
