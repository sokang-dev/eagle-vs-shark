package model.Shark;

import model.Enums.PieceType;
import model.Piece;
import model.Tile;

public abstract class Shark extends Piece {

    protected int baseMovement = 1;

    public Shark(Tile tile) {
        super(tile, PieceType.Shark);
        super.setBaseMovement(baseMovement);
    }
}
