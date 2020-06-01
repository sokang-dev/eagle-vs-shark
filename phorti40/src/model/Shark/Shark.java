package model.Shark;

import model.AbstractPiece;
import model.Enums.PieceType;

public abstract class Shark extends AbstractPiece {

    protected int baseMovement = 1;
    protected int health = 2;

    public Shark() {
        super(PieceType.Shark);
        super.setBaseMovement(baseMovement);
        super.setHealth(health);
    }
}
