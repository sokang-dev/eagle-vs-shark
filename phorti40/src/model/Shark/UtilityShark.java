package model.Shark;

import javafx.scene.image.ImageView;
import model.Board;
import model.Tile;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class UtilityShark extends Shark {

    public UtilityShark() {
        super();
        super.setSprite(Sprites.UtilityShark);
    }

    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        Set<Tile> validMoves = new HashSet<>();

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {

                // Don't get diagonal tiles
                if (Math.abs(i) == Math.abs(j))
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                if (Tile.isOutOfBounds(x, y))
                    continue;

                if (board.getTile(x, y).getPiece() == null)
                    validMoves.add(new Tile(x, y));
            }
        }

        return validMoves;
    }

    // Used for debugging only - returns ANSI_BLUE U
    @Override
    public String toString() {
        return "\u001B[34m U \u001B[0m";
    }
}
