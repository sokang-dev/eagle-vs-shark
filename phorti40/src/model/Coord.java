package model;

import java.util.Objects;

class Coord {

    private int x, y;

    public Coord(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9)
            throw new ArrayIndexOutOfBoundsException();

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("x: %d, y: %d", this.x, this.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Coord)) return false;

        Coord c = (Coord) obj;
        return this.x == c.x && this.y == c.y;
    }
}
