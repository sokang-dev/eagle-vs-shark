package model.Shark;

import model.Board;
import model.Enums.StatusType;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AltUtilitySharkDecorator extends SharkDecorator {

    public AltUtilitySharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AltUtilityShark);
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormUtilitySharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
