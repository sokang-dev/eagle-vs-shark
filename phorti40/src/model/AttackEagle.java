package model;

import java.util.HashSet;
import java.util.Set;

public class AttackEagle extends Eagle {
    public AttackEagle(Tile tile) {
        super(tile);
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

                Tile validTile = new Tile(currentCoord.getX() + i, currentCoord.getY() + j);

                while (!validTile.isOutOfBounds()) {
                    validMoves.add(new Tile(validTile.getX(), validTile.getY()));

                    validTile.setX(validTile.getX() + i);
                    validTile.setY(validTile.getY() + j);
                }
            }
        }

        return validMoves;
    }
}
