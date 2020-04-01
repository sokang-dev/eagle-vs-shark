package model;

import java.util.Set;

public class DummyEagle extends Eagle {

    public DummyEagle(Tile tile) {
        super(tile);
    }

    public Set<Coord> getValidMoves() {
        return super.getValidMoves(new Coord(1,1), this.baseMovement);
    }
}
