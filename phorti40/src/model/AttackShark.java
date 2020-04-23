package model;

import javafx.scene.image.ImageView;
import resources.Constants;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class AttackShark extends Shark {

    public AttackShark(Tile tile) {
        super(tile);
        super.setSprite(new ImageView(Sprites.AttackShark));
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
}
