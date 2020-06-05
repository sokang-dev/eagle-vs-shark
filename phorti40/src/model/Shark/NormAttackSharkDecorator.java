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
        super.setSprite(Sprites.AttackSharkUnderwater);

        // Untargetable does not decrement
        super.setStatus(StatusType.Untargetable, 0);
    }

    // Attacks normally, becoming targetable again
    @Override
    public void attack(Tile tile) {
        super.attack(tile);
        super.setSprite(Sprites.AttackShark);
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
        super.setSprite(Sprites.AttackSharkUnderwater);
    }

    // Used for debugging only - returns ANSI_BLUE A
    @Override
    public String toString() {
        return "\u001B[34m A \u001B[0m";
    }
    @Override
    public Piece transform() {
        super.removeStatus(StatusType.Untargetable);

        Piece newForm = new AltAttackSharkDecorator(this.decoratedShark);
        super.getTile().setPiece(newForm);

        return newForm;
    }
}
