package model;

import resources.Constants;

import java.util.Objects;

public class Tile {

    private int x, y;
    private Piece piece;
    private Terrain terrain;

    // For instantiating empty tiles
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) { this.x = x; }

    public int getY() {
        return this.y;
    }

    public void setY(int y) { this.y = y; }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    public static boolean isOutOfBounds(int x, int y) {
        return (x < 0 || x > Constants.BOARD_WIDTH - 1 || y < 0 || y > Constants.BOARD_HEIGHT - 1);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile)) return false;

        Tile tile = (Tile) obj;
        return this.x == tile.getX() && this.y == tile.getY();
    }
}
