package model.Eagle;

import javafx.scene.image.Image;
import model.Board;
import model.Enums.PieceType;
import model.Tile;
import model.interfaces.Piece;

import java.util.Set;

public abstract class EagleDecorator implements Piece {

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
    public void move(Tile tile) {
        decoratedEagle.move(tile);
    }

    @Override
    public void attack(Piece piece) {
        decoratedEagle.attack(piece);
    }

    @Override
    public void takeDamage() {
        decoratedEagle.takeDamage();
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
}
