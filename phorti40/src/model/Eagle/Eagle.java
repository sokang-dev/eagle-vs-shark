package model.Eagle;

import model.Piece;
import model.Tile;

public abstract class Eagle extends Piece {

    protected int baseMovement = 2;

    public Eagle(Tile tile) {
        super(tile);
        super.setBaseMovement(baseMovement);
    }
}
