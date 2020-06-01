package model.Shark;

import resources.Sprites;

public class AttackShark extends Shark {

    public AttackShark() {
        super();
        super.setSprite(Sprites.AttackShark);
    }

    // Used for debugging only - returns ANSI_BLUE A
    @Override
    public String toString() {
        return "\u001B[34m A \u001B[0m";
    }
}
