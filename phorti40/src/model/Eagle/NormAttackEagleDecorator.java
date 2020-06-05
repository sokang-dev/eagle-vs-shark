package model.Eagle;

import model.Board;
import model.Enums.PieceType;
import model.Enums.StatusType;
import model.Tile;
import model.interfaces.Piece;
import resources.Sprites;

import java.util.HashSet;
import java.util.Set;

public class NormAttackEagleDecorator extends EagleDecorator {

    public NormAttackEagleDecorator(Piece decoratedEagle) {
        super(decoratedEagle);
        super.setSprite(Sprites.AttackEagle);
    }

    // NormAttackEagle can move across unlimited tiles in one direction
    @Override
    public Set<Tile> getValidMoves(Tile currentCoord, int movement, Board board) {
        Set<Tile> validMoves = new HashSet<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                // Disregard current coordinates
                if (i == 0 && j == 0)
                    continue;

                int x = currentCoord.getX() + i;
                int y = currentCoord.getY() + j;

                while (board.getTile(x, y) != null) {
                    // Add only unoccupied Tiles
                    if (board.getTile(x, y).getPiece() == null && board.getTile(x, y).getTerrain() == null)
                        validMoves.add(new Tile(x, y));

                    x += i;
                    y += j;
                }
            }
        }

        return validMoves;
    }

    // NormAttackEagle's attack is tied to its movement, it will kill anything in its path.
    @Override
    public Set<Tile> getValidAttacks(Tile currentCoord, Board board) {
        return new HashSet<>();
    }

    // NormAttackEagle's move kill every shark in its path
    @Override
    public void move(Tile tile, Board board) {
        boolean kill = false;

        int oldX = super.getTile().getX();
        int oldY = super.getTile().getY();
        int newX = tile.getX();
        int newY = tile.getY();

        int diffX = newX - oldX;
        int diffY = newY - oldY;
        int diff = diffX != 0 ? Math.abs(diffX) : Math.abs(diffY);

        for (int i = 1; i < diff; i++) {
            int x = diffX > 0 ? oldX + i : (diffX < 0 ? oldX - i : oldX);
            int y = diffY > 0 ? oldY + i : (diffY < 0 ? oldY - i : oldY);

            Piece piece = board.getTile(x, y).getPiece();
            if (piece != null && piece.getPieceType() == PieceType.Shark && piece.getStatus(StatusType.Untargetable) == null) {
                piece.die();
                kill = true;
            }
        }

        // If movement ends up in kill, NormAttackEagle will be stunned for 2 turns
        if (kill) super.setStatus(StatusType.Stun, 2);

        super.move(tile, board);
    }

    @Override
    public Piece transform() {
        Piece newForm = new AltAttackEagleDecorator(this.decoratedEagle);
        super.getTile().setPiece(newForm);

        return newForm;
    }

    @Override
    public String toString() {
        return "\u001B[31m D \u001B[0m";
    }
}
