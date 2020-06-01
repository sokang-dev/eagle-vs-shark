package model.Eagle;

import model.Board;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class NormAttackEagleDecorator extends EagleDecorator {

    public NormAttackEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.AttackEagle);
    }

    // NormAttackEagleDecorator can move across unlimited tiles in one direction
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

    // Used for debugging only - returns ANSI_RED A
    @Override
    public String toString() {
        return "\u001B[31m A \u001B[0m";
    }
}
