package model.Eagle;

import model.Board;
import model.Enums.StatusType;
import model.Terrain;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AltDummyEagleDecorator extends EagleDecorator {

    public AltDummyEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.AltEagle);
    }

    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        Set<Tile> validSpecials = new HashSet<>();

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                if (board.getTile(x, y) == null)
                    continue;

                if (board.getTile(x, y).getPiece() == null)
                    validSpecials.add(board.getTile(x, y));
            }
        }
        validSpecials.remove(currentCoord);
        return validSpecials;
    }

    public void special(Tile destinationTile) {
        System.out.println("Create terrain");

        // create new tile
        destinationTile.setTerrain(new Terrain());
    }

    @Override
    public Piece transform() {
        Piece newForm = new NormDummyEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[31m D \u001B[0m";
    }
}
