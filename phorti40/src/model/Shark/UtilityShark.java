package model.Shark;

import resources.Sprites;


public class UtilityShark extends Shark {

    public UtilityShark() {
        super();
        super.setSprite(Sprites.UtilityShark);
    }

    // Used for debugging only - returns ANSI_BLUE U
    @Override
    public String toString() {
        return "\u001B[34m U \u001B[0m";
    }
}
