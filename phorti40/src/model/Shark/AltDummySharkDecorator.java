package model.Shark;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AltDummySharkDecorator extends SharkDecorator {

    public AltDummySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AltShark);
    }

    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        Set<Tile> validSpecials = new HashSet<>();
        validSpecials.add(currentCoord);

        return validSpecials;
    }

        public void special(Tile destinationTile) {
            super.setHealth(super.getHealth() + 1);
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormDummySharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
