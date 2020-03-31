package model;

public class Tile {

    private Coord coord;
    private Piece piece;

    // For instantiating empty tiles
    public Tile(){}

    public Tile(Piece piece)
    {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
