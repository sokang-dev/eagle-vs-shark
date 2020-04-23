package model;

import javafx.scene.image.ImageView;
import resources.Sprites;

public class DummyShark extends Shark {

    public DummyShark(Tile tile) {
        super(tile);
        super.setSprite(new ImageView(Sprites.Shark));
    }
}
