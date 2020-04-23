package model.Shark;

import javafx.scene.image.ImageView;
import model.Tile;
import resources.Sprites;

public class DummyShark extends Shark {

    public DummyShark(Tile tile) {
        super(tile);
        super.setSprite(Sprites.Shark);
    }
}
