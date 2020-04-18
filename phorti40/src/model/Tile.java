package model;

import resources.Constants;

import java.util.Objects;

public class Tile {

    private int _x, _y;
    private Piece piece;
    private Terrain terrain;

    // For instantiating empty tiles
    public Tile(int x, int y) {
        _x = x;
        _y = y;
    }

    public Tile(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return _x;
    }

    public void setX(int x) { _x = x; }

    public int getY() {
        return _y;
    }

    public void setY(int y) { _y = y; }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public boolean isOutOfBounds() {
        return (_x < 0 || _x > Constants.BOARD_WIDTH || _y < 0 || _y > Constants.BOARD_HEIGHT);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + _x +
                ", y=" + _y +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile)) return false;

        Tile tile = (Tile) obj;
        return _x == tile.getX() && _y == tile.getY();
    }
}
