package model;

import javafx.scene.image.Image;
import model.Enums.PieceType;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    private Tile tile;
    protected int baseMovement;
    protected Image sprite;
    protected PieceType pieceType;

    protected Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    // Get valid moves of a piece based on its baseMovement value
    // Will get used more in assignment 2
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {

        Set<Tile> validMoves = new HashSet<>();

        if (movement == 0) {
            validMoves.add(currentCoord);
        } else {
            // Add adjacent Tiles to validMoves with some rules
            addAdjacentTiles(currentCoord, validMoves, board);
            Set<Tile> recursiveValidMoves = new HashSet<>();

            for (Tile validMove : validMoves) {
                recursiveValidMoves.addAll(getValidMoves(validMove, movement - 1, board));
            }
            validMoves.addAll(recursiveValidMoves);
        }

        return validMoves;
    }

    // Remove piece from current tile and set piece to a new tile.
    public void move(Tile tile) {
        this.tile.removePiece();
        tile.setPiece(this);
    }

    private void addAdjacentTiles(Tile currentCoord, Set<Tile> validMoves, Board board) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Don't get diagonal Tiles
                if (Math.abs(i) == Math.abs(j))
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                // Add only unoccupied Tiles that are in bounds
                if (board.getTile(x, y) != null && board.getTile(x, y).getPiece() == null)
                    validMoves.add(new Tile(x, y));
            }
        }
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }
    public Image getSprite() {
        return this.sprite;
    }
    public Tile getTile() {
        return this.tile;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
    public int getBaseMovement() {
        return this.baseMovement;
    }
    protected void setBaseMovement(int baseMovement) {
        this.baseMovement = baseMovement;
    }
}
