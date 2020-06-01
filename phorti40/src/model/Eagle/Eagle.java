package model.Eagle;

import model.AbstractPiece;
import model.Enums.PieceType;

public class Eagle extends AbstractPiece {

    protected int baseMovement = 2;
    protected int health = 1;

    public Eagle() {
        super(PieceType.Eagle);
        super.setBaseMovement(baseMovement);
        super.setHealth(health);
    }
}
