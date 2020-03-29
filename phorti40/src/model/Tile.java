package model;

public class Tile {
    private Piece piece;

    public Tile(Piece piece)
    {
        this.piece = piece;
    }

    // For instantiating empty tiles
    public Tile(){}

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
