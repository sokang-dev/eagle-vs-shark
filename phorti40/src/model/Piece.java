package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    protected int movement;

    protected Piece(int movement) {
        this.movement = movement;
    }

    public Set<Coord> getValidMoves(Coord currentCoord, int movement) {

        Set<Coord> validMoves = new HashSet<>();

        try {
            if (movement == 0) {
                validMoves.add(currentCoord);
            } else {
                validMoves.add(new Coord(currentCoord.getX() + 1, currentCoord.getY()));
                validMoves.add(new Coord(currentCoord.getX(), currentCoord.getY() + 1));
                validMoves.add(new Coord(currentCoord.getX() - 1, currentCoord.getY()));
                validMoves.add(new Coord(currentCoord.getX(), currentCoord.getY() - 1));

                Set<Coord> recursiveValidMoves = new HashSet<>();

                for (Coord validMove : validMoves) {
                    recursiveValidMoves.addAll(getValidMoves(validMove, movement));
                }

                validMoves.addAll(recursiveValidMoves);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}

        return validMoves;
    }
}
