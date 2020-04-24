package model.Eagle;

import javafx.scene.image.ImageView;
import model.Tile;
import resources.Sprites;

public class DummyEagle extends Eagle {

    public DummyEagle() {
        super();
        super.setSprite(Sprites.Eagle);
    }

    // Used for debugging only - returns ANSI_RED D
    @Override
    public String toString() {
        return "\u001B[31m D \u001B[0m";
    }
}
