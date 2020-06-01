package model.Eagle;

import model.Board;
import model.Tile;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class UtilityEagle extends Eagle {

    public UtilityEagle() {
        super();
        super.setSprite(Sprites.UtilityEagle);
    }

    // Used for debugging only - returns ANSI_RED U
    @Override
    public String toString() {
        return "\u001B[31m U \u001B[0m";
    }
}
