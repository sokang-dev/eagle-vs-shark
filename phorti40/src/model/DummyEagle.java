package model;

import javafx.scene.image.ImageView;
import resources.Sprites;

public class DummyEagle extends Eagle {

    public DummyEagle(Tile tile) {
        super(tile);
        super.setSprite(new ImageView(Sprites.Eagle));
    }
}
