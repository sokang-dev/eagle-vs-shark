package model;

public class Tile {

    private int x, y;
    private Piece piece;
    private Terrain terrain;

    // For instantiating empty tiles
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(Piece piece)
    {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() { this.piece = null; }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
