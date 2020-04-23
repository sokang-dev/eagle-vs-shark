package model;

import javafx.scene.image.ImageView;
import resources.Sprites;

public class DummyEagle extends Eagle {

    private ImageView sprite;

    public DummyEagle(Tile tile) {
        super(tile);
        this.sprite = new ImageView(Sprites.Eagle);
    }
}
