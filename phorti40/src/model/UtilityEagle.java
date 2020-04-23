package model;

import javafx.scene.image.ImageView;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class UtilityEagle extends Eagle {

    private ImageView sprite;

    public UtilityEagle(Tile tile) {
        super(tile);
        this.sprite = new ImageView(Sprites.UtilityEagle);
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

                if (Tile.isOutOfBounds(x, y))
                    continue;

                if (board.getTile(x, y).getPiece() == null)
                    validMoves.add(new Tile(x, y));
            }
        }

        return validMoves;
    }
}
