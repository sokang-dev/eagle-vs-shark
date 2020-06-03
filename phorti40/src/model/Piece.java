package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.Enums.PieceType;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Piece implements Serializable {

    private Tile tile;
    private int health;
    protected int baseMovement;
    protected transient Image sprite;
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

    // Remove piece from current tile and set piece to a new tile.
    public void move(Tile tile) {
        this.tile.removePiece();
        tile.setPiece(this);
    }

    public void attack(Piece piece) {
        piece.takeDamage();
    }

    public void takeDamage() {
        this.health -= 1;

        if (this.health < 1) {
            this.tile.removePiece();
        }
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

    protected void setBaseMovement(int baseMovement) {
        this.baseMovement = baseMovement;
    }

    // Custom serialisation to account for the Sprite
    private void writeObject(ObjectOutputStream out) throws IOException {
        //write the default values to be serialised
        out.defaultWriteObject();
        //then if the sprite isn't null store as BufferedImage
        out.writeBoolean(sprite != null);
        if (sprite != null) {
            ImageIO.write(SwingFXUtils.fromFXImage(sprite, null), "png", out);
        }
    }

    // Custom deserialisation to account for the Sprite
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        sprite = in.readBoolean() ? new Image(in) : null;
    }
    protected void setHealth(int health) {
        this.health = health;
    }

}
