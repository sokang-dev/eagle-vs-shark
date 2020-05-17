package model.Shark;

import model.Enums.PieceType;
import model.Piece;

public abstract class Shark extends Piece {

    protected int baseMovement = 1;
    protected int health = 2;

    public Shark() {
        super(PieceType.Shark);
        super.setBaseMovement(baseMovement);
        super.setHealth(health);
    }
}
