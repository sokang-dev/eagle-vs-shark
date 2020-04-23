package model.Shark;

import model.Piece;
import model.Tile;

public abstract class Shark extends Piece {

    protected int baseMovement = 1;

    public Shark(Tile tile) {
        super(tile);
        super.setBaseMovement(baseMovement);
    }
}
