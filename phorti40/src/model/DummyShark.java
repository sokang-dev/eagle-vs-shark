package model;

import javafx.scene.image.ImageView;
import resources.Sprites;

public class DummyShark extends Shark {

    ImageView sprite;

    public DummyShark(Tile tile) {
        super(tile);
        this.sprite = new ImageView(Sprites.Shark);
    }
}
