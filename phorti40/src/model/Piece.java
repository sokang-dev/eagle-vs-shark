package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    private Tile tile;
    protected int baseMovement;

    protected Piece(Tile tile) {
        this.tile = tile;
        this.tile.setPiece(this);
    }

    public Tile getTile() {
        return this.tile;
    }

    public int getBaseMovement() {
        return this.baseMovement;
    }

    protected void setBaseMovement(int baseMovement) {
        this.baseMovement = baseMovement;
    }

    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {

        Set<Tile> validMoves = new HashSet<>();

        if (movement == 0) {
            validMoves.add(currentCoord);
        } else {
            // Add adjacent Tiles to validMoves with some rules
            addAdjacentTiles(currentCoord, validMoves, board);
            Set<Tile> recursiveValidMoves = new HashSet<>();

            for (Tile validMove : validMoves) {
                // TODO: Corner tiles being added here are duplicate fsr
                recursiveValidMoves.addAll(getValidMoves(validMove, movement - 1, board));
            }
            validMoves.addAll(recursiveValidMoves);
        }

        return validMoves;
    }

    public void move(Tile tile) {
        this.tile.removePiece();
        this.tile = tile;
        this.tile.setPiece(this);
    }

    private void addAdjacentTiles(Tile currentCoord, Set<Tile> validMoves, Board board) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Don't get diagonal Tiles
                if (Math.abs(i) == Math.abs(j))
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                // Don't add out of bounds Tiles
                if (Tile.isOutOfBounds(x, y))
                    continue;

                // Add only unoccupied Tiles
                if (board.getTile(x, y).getPiece() == null)
                    validMoves.add(new Tile(x, y));
            }
        }
    }
}
