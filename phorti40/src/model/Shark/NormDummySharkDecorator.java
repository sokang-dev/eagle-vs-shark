package model.Shark;

import model.Eagle.NormAttackEagleDecorator;
import model.Enums.PieceType;
import model.Terrain;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

public class NormDummySharkDecorator extends SharkDecorator {

    public NormDummySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.Shark);
    }

    @Override
    public void attack(Tile tile) {
        // Don't heal if you destroy terrain
        if(tile.getTerrain() == null) {
            super.setHealth(super.getHealth() + 1);
        }
        super.attack(tile);
    }

    @Override
    public Piece transform() {
        Piece newForm = new AltDummySharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    // Used for debugging only - returns ANSI_BLUE D
    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
