package model.Eagle;

import javafx.scene.image.Image;
import model.Board;
import model.Enums.PieceType;
import model.Enums.StatusType;
import model.Status;
import model.Tile;
import model.interfaces.Piece;

import java.io.Serializable;
import java.util.Set;

public abstract class EagleDecorator implements Piece, Serializable {

    protected Piece decoratedEagle;

    public EagleDecorator(Piece decoratedEagle) {
        this.decoratedEagle = decoratedEagle;
    }

    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        return decoratedEagle.getValidMoves(currentCoord, movement, board);
    }

    @Override
    public Set<Tile> getValidAttacks(Tile currentCoord, Board board) {
        return decoratedEagle.getValidAttacks(currentCoord, board);
    }

    @Override
    public Set<Tile> calcValidSpecials(Tile currentCoord, Board board) {
        return decoratedEagle.calcValidSpecials(currentCoord, board);
    }

    @Override
    public void move(Tile tile, Board board) {
        getTile().removePiece();
        tile.setPiece(this);
    }

    @Override
    public void attack(Piece piece) {
        decoratedEagle.attack(piece);
    }

    @Override
    public void special(Tile tile, Board board) {
        decoratedEagle.special(tile, board);
    }

    @Override
    public void takeDamage() {
        decoratedEagle.takeDamage();
    }

    @Override
    public void die() { decoratedEagle.die(); }

    @Override
    public void setStatus(StatusType type, int duration) {
        decoratedEagle.setStatus(type, duration);
    }

    @Override
    public Status getStatus(StatusType type) {
        return decoratedEagle.getStatus(type);
    }

    @Override
    public PieceType getPieceType() {
        return decoratedEagle.getPieceType();
    }

    @Override
    public Image getSprite() {
        return decoratedEagle.getSprite();
    }

    @Override
    public Tile getTile() {
        return decoratedEagle.getTile();
    }

    @Override
    public int getHealth() {
        return decoratedEagle.getHealth();
    }

    @Override
    public void setTile(Tile tile) {
        decoratedEagle.setTile(tile);
    }

    @Override
    public void setSprite(Image sprite) {
        decoratedEagle.setSprite(sprite);
    }

    @Override
    public int getBaseMovement() {
        return decoratedEagle.getBaseMovement();
    }

    @Override
    public void setBaseMovement(int baseMovement) {
        decoratedEagle.setBaseMovement(baseMovement);
    }

    @Override
    public void setHealth(int health) {
        decoratedEagle.setHealth(health);
    }

    @Override
    public Set<Tile> getValidSpecials() {
        return decoratedEagle.getValidSpecials();
    }

    @Override
    public void setValidSpecials(Set<Tile> validSpecials) {
        decoratedEagle.setValidSpecials(validSpecials);
    }
}
