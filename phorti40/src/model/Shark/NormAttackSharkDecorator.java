package model.Shark;

import model.Board;
import model.Enums.StatusType;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class NormAttackSharkDecorator extends SharkDecorator {

    public NormAttackSharkDecorator(Piece decoratedShark) {
        super(decoratedShark);
        super.setSprite(Sprites.AttackShark);

        // Untargetable does not decrement
        super.setStatus(StatusType.Untargetable, 0);
    }

    // Attacks normally, becoming targetable again
    @Override
    public void attack(Piece piece) {
        super.attack(piece);
        super.removeStatus(StatusType.Untargetable);
    }

    // Special range is the shark itself
    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        Set<Tile> currentTile = new HashSet<>();

        // Dont allow special if already untargetable
        if(currentCoord.getPiece().getStatus(StatusType.Untargetable) != null)
            return currentTile;

        currentTile.add(currentCoord);
        return currentTile;
    }

    @Override
    public void special(Tile tile, Board board) {
        super.setStatus(StatusType.Untargetable, 0);
    }

    @Override
    public Piece transform() {
        super.removeStatus(StatusType.Untargetable);

        Piece newForm = new AltAttackSharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
