package model;

import javafx.scene.image.Image;
import resources.Sprites;

import java.io.Serializable;

public class Terrain implements Serializable {

    Tile tile;
    Image sprite;

    public Terrain(Tile tile) {
        this.tile = tile;
        this.sprite = Sprites.Terrain;
    }

    public Image getSprite() {
        return this.sprite;
    }
}
