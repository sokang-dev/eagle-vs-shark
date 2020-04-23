package model;

import javafx.scene.image.ImageView;
import resources.Sprites;

public abstract class Eagle extends Piece {

    protected int baseMovement = 2;
    private ImageView sprite;

    public Eagle(Tile tile) {
        super(tile);
        super.setBaseMovement(baseMovement);
        this.sprite = new ImageView(Sprites.Eagle);

    }
}
