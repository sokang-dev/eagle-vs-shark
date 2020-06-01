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

    // NormAttackEagle can move across unlimited tiles in one direction
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

    // NormAttackEagle's attack is tied to its movement, it will kill anything in its path.
    @Override
    public Set<Tile> getValidAttacks(Tile currentCoord, Board board) {
        return new HashSet<>();
    }

    @Override
    public void move(Tile tile) {
        int oldX = super.getTile().getX();
        int oldY = super.getTile().getY();

        int newX = tile.getX();
        int newY = tile.getY();

        super.move(tile);
    }
}
