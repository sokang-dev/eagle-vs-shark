package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    protected int baseMovement;
    private Coord coord;
    private Tile tile;

    protected Piece(Tile tile) {
        this.tile = tile;
        this.tile.setPiece(this);
    }

    public int getBaseMovement() {
        return this.baseMovement;
    }

    protected void setBaseMovement(int baseMovement) {
        this.baseMovement = baseMovement;
    }

    public Coord getCoord() {
        return this.tile.getCoord();
    }

    public Set<Coord> getValidMoves(Coord currentCoord, int movement, Set<Coord> pieceCoords) {

        Set<Coord> validMoves = new HashSet<>();

        try {
            if (movement == 0) {
                validMoves.add(currentCoord);
            } else {
                // Add adjacent coordinates to validMoves with some rules
                addAdjacentCoords(currentCoord, validMoves, pieceCoords);
                Set<Coord> recursiveValidMoves = new HashSet<>();

                for (Coord validMove : validMoves) {
                    recursiveValidMoves.addAll(getValidMoves(validMove, movement - 1, pieceCoords));
                }

                validMoves.addAll(recursiveValidMoves);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}

        return validMoves;
    }

    protected void move(Tile tile) {
        this.tile.removePiece();
        this.tile = tile;
        this.tile.setPiece(this);
    }

    private void addAdjacentCoords(Coord currentCoord, Set<Coord> validMoves, Set<Coord> pieceCoords) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // don't get diagonal coordinates
                if (Math.abs(i) == Math.abs(j))
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                // don't add occupied coordinates
                if (!pieceCoords.contains(new Coord(x, y)))
                    validMoves.add(new Coord(x, y));
            }
        }
    }
}
