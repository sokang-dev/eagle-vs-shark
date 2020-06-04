package model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.Enums.PieceType;
import model.Enums.StatusType;
import model.interfaces.Piece;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPiece implements Piece, Serializable {

    private Tile tile;
    private int health;
    protected int baseMovement;
    protected Set<Status> statuses;
    protected transient Image sprite;
    protected PieceType pieceType;
    private Set<Tile> validSpecials;

    protected AbstractPiece(PieceType pieceType) {
        this.pieceType = pieceType;
        statuses = new HashSet<>();
        validSpecials = new HashSet<>();
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

    // Standard attack range is adjacent tiles (exclude diagonal)
    public Set<Tile> getValidAttacks(Tile currentCoord, Board board) {

        Set<Tile> validAttacks = new HashSet<>();
        Set<Piece> adjacentPieces = board.getAdjacentPieces(currentCoord);

        // Only add opponent adjacent pieces
        for (Piece piece : adjacentPieces) {
            if (piece.getPieceType() != this.getPieceType()) {
                validAttacks.add(piece.getTile());
            }
        }

        return validAttacks;
    }

    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        return this.validSpecials;
    }

    // Remove piece from current tile and set piece to a new tile.
    public void move(Tile tile, Board board) {
        this.tile.removePiece();
        tile.setPiece(this);
    }

    public void attack(Tile tile) {
        if(tile.getTerrain() != null)
            tile.removeTerrain();
        else
            tile.getPiece().takeDamage();
    }

    public void special(Tile tile, Board board) {
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

    public void setStatus(StatusType type, int duration) {
        Status status = new Status(type, duration);

        // Remove status when duration runs out
        if (duration < 0) {
            statuses.remove(status);
            return;
        }

        // Replace status if the same status type is already present
        if (!statuses.add(status)) {
            statuses.remove(status);
            statuses.add(status);
        }
    }

    @Override
    public Piece transform() { return null; }

    public Status getStatus(StatusType type) {
        for (Status status : statuses) {
            if (type == status.getType())
                return status;
        }

        return null;
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

    @Override
    public Set<Tile> getValidSpecials() {
        return this.validSpecials;
    }

    @Override
    public void setValidSpecials(Set<Tile> validSpecials) {
        this.validSpecials = validSpecials;
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
                if (board.getTile(x, y) == null)
                    continue;

                // Add only unoccupied Tiles
                if (board.getTile(x, y).getPiece() == null && board.getTile(x, y).getTerrain() == null)
                    validMoves.add(new Tile(x, y));
            }
        }
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
}
