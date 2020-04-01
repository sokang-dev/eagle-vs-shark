package model;

public class Tile {

    private Coord coord;
    private Piece piece;
    private Terrain terrain;

    // For instantiating empty tiles
    public Tile(Coord coord) {
        this.coord = coord;
    }

    public Tile(Piece piece)
    {
        this.piece = piece;
    }

    public Coord getCoord() {
        return this.coord;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() { this.piece = null; }
}
