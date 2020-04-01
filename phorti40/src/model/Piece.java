package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    private Coord coord;
    private Tile tile;

    protected Piece(Tile tile) {
        this.tile = tile;
    }

    protected Coord getCoord() {
        return this.tile.getCoord();
    }

    protected Set<Coord> getValidMoves(Coord currentCoord, int movement) {

        Set<Coord> validMoves = new HashSet<>();

        try {
            if (movement == 0) {
                validMoves.add(this.coord);
            } else {
                validMoves.add(new Coord(this.coord.getX() + 1, this.coord.getY()));
                validMoves.add(new Coord(this.coord.getX(), this.coord.getY() + 1));
                validMoves.add(new Coord(this.coord.getX() - 1, this.coord.getY()));
                validMoves.add(new Coord(this.coord.getX(), this.coord.getY() - 1));

                Set<Coord> recursiveValidMoves = new HashSet<>();

                for (Coord validMove : validMoves) {
                    recursiveValidMoves.addAll(getValidMoves(validMove, movement - 1));
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
}
