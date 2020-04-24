package model.Shark;

import resources.Sprites;

public class DummyShark extends Shark {

    public DummyShark() {
        super();
        super.setSprite(Sprites.Shark);
    }

    // Used for debugging only - returns ANSI_BLUE D
    @Override
    public String toString() {
        return "\u001B[34m D \u001B[0m";
    }
}
