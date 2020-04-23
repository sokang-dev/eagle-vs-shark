package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AttackEagle extends Eagle {

    public AttackEagle(Tile tile) {
        super(tile);
        super.setSprite(new ImageView(Sprites.UtilityEagle));
    }

    // AttackEagle can move across unlimited tiles in one direction
    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        Set<Tile> validMoves = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                // Disregard current coordinates
                if (i == 0 && j == 0)
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                while (!Tile.isOutOfBounds(x, y)) {
                    // Add only unoccupied Tiles
                    if (board.getTile(x, y).getPiece() == null)
                        validMoves.add(new Tile(x, y));

                    x += i;
                    y += j;
                }
            }
        }

        return validMoves;
    }
}
