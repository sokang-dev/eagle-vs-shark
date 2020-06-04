package model.Shark;

import javafx.scene.image.Image;
import model.Board;
import model.Enums.PieceType;
import model.Tile;
import model.interfaces.Piece;

import java.io.Serializable;
import java.util.Set;

public abstract class SharkDecorator implements Piece, Serializable {
    protected Piece decoratedShark;

    public SharkDecorator(Piece decoratedShark) {
        this.decoratedShark = decoratedShark;
    }

    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        return decoratedShark.getValidMoves(currentCoord, movement, board);
    }

    @Override
    public Set<Tile> getValidAttacks(Tile currentCoord, Board board) {
        return decoratedShark.getValidAttacks(currentCoord, board);
    }

    @Override
    public Set<Tile> getValidSpecials(Tile currentCoord, Board board) {
        return decoratedShark.getValidSpecials(currentCoord, board);
    }

    @Override
    public void move(Board board, Tile tile) {
        getTile().removePiece();
        tile.setPiece(this);
    }

    @Override
    public void attack(Piece piece) {
        decoratedShark.attack(piece);
    }

    @Override
    public boolean hasSpecial() {
        return decoratedShark.hasSpecial();
    }

    @Override
    public void special() { decoratedShark.special();}

    @Override
    public void takeDamage() {
        decoratedShark.takeDamage();
    }

    @Override
    public void die() { decoratedShark.die(); }

    @Override
    public PieceType getPieceType() {
        return decoratedShark.getPieceType();
    }

    @Override
    public Image getSprite() {
        return decoratedShark.getSprite();
    }

    @Override
    public Tile getTile() {
        return decoratedShark.getTile();
    }

    @Override
    public int getHealth() {
        return decoratedShark.getHealth();
    }

    @Override
    public void setTile(Tile tile) {
        decoratedShark.setTile(tile);
    }

    @Override
    public void setSprite(Image sprite) {
        decoratedShark.setSprite(sprite);
    }

    @Override
    public int getBaseMovement() {
        return decoratedShark.getBaseMovement();
    }

    @Override
    public void setBaseMovement(int baseMovement) {
        decoratedShark.setBaseMovement(baseMovement);
    }

    @Override
    public void setHealth(int health) {
        decoratedShark.setHealth(health);
    }
}
