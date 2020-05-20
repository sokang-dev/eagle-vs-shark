package model.Eagle;

import model.Board;
import model.Tile;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class UtilityEagle extends Eagle {

    public UtilityEagle() {
        super();
        super.setSprite(Sprites.UtilityEagle);
    }

    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        Set<Tile> validMoves = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (Math.abs(i) != Math.abs(j))
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                // Add only unoccupied Tiles that are in bounds
                if (board.getTile(x, y) != null && board.getTile(x, y).getPiece() == null)
                    validMoves.add(new Tile(x, y));
            }
        }

        return validMoves;
    }

    // Used for debugging only - returns ANSI_RED U
    @Override
    public String toString() {
        return "\u001B[31m U \u001B[0m";
    }
}
