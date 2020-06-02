package model;

import javafx.scene.image.Image;
import model.Enums.PieceType;
import model.interfaces.Piece;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPiece implements Piece {

    private Tile tile;
    private int health;
    protected int baseMovement;
    protected Image sprite;
    protected PieceType pieceType;

    protected AbstractPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    // Get valid moves of a piece based on its baseMovement value
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

    public Set<Tile> getValidAttacks(Tile currentCoord, Board board) {

        Set<Tile> validAttacks = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (Math.abs(i) == Math.abs(j))
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                if (Tile.isOutOfBounds(x, y))
                    continue;

                // Add pieces from the opposing team
                if (board.getTile(x, y).getPiece() != null) {
                    if (board.getPiece(x, y).getPieceType() != this.getPieceType()) {
                        validAttacks.add(new Tile(x, y));
                    }
                }
            }
        }
        return validAttacks;
    }

    public Set<Tile> getValidSpecials(Tile currentCoord, Board board) {
        return new HashSet<>();
    }

    // Remove piece from current tile and set piece to a new tile.
    public void move(Board board, Tile tile) {
        this.tile.removePiece();
        tile.setPiece(this);
    }

    public void attack(Piece piece) {
        piece.takeDamage();
    }

    public boolean hasSpecial() {
        return false;
    }

    public void special() {
        System.out.println("This piece has no special.");
    }

    public void takeDamage() {
        this.health -= 1;

        if (this.health < 1) {
            die();
        }
    }

    public void die() {
        this.tile.removePiece();
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

    public int getHealth() {
        return this.health;
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

    public void setBaseMovement(int baseMovement) {
        this.baseMovement = baseMovement;
    }

    public void setHealth(int health) {
        this.health = health;
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