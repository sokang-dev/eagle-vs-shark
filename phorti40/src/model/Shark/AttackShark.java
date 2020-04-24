package model.Shark;
import model.Board;
import model.Tile;
import resources.Constants;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AttackShark extends Shark {

    public AttackShark() {
        super();
        super.setSprite(Sprites.AttackShark);
    }

    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        Set<Tile> validMoves = new HashSet<>();

        for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
            for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {

                // Add only unoccupied Tiles
                if (board.getTile(i, j).getPiece() == null)
                    validMoves.add(new Tile(i, j));
            }
        }

        return validMoves;
    }

    // Used for debugging only - returns ANSI_BLUE A
    @Override
    public String toString() {
        return "\u001B[34m A \u001B[0m";
    }
}
