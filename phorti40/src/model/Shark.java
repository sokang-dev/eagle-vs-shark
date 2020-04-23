package model;

import javafx.scene.image.ImageView;
import resources.Sprites;

public abstract class Shark extends Piece {

    protected int baseMovement = 1;
    private ImageView sprite;

    public Shark(Tile tile) {
        super(tile);
        super.setBaseMovement(baseMovement);
        this.sprite = new ImageView(Sprites.Shark);
    }
}
