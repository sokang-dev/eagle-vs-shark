package model;

import javafx.scene.image.Image;
import resources.Sprites;

import java.io.Serializable;

public class Terrain implements Serializable {

    Image sprite;

    public Terrain() {
        this.sprite = Sprites.Terrain;
    }

    public Image getSprite() {
        return this.sprite;
    }
}
