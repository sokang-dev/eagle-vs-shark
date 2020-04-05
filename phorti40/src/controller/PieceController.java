package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Board;
import model.Piece;
import model.Tile;
import view.TileView;

import java.util.Set;

public class PieceController {
    GridPane visualBoard;
    Board board;
    TileView tile;

    public PieceController(GridPane visualBoard, Board board, TileView tile) {
        this.visualBoard = visualBoard;
        this.board = board;
        this.tile = tile;

        tile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Set<Tile> validMoves = calculateTilesToHighlight();
                for(Tile validMove : validMoves) {
                    int x = validMove.getX();
                    int y = validMove.getY();

                    // Iterate every tile on the board
                    // Get their X and Y coordinates
                    // If it matches the validMove, highlight the TileView at those coordinates
                    for(int i = 0; i < 100; i++) {
                            TileView tView = (TileView) visualBoard.getChildren().get(i);
                            if(tView.getTile().getX() == x && tView.getTile().getY() == y) {
                                tView.highlightMovement();
                            }
                    }
                }
            }
        });
    }

    private Set<Tile> calculateTilesToHighlight() {
        int x = tile.getTile().getX();
        int y = tile.getTile().getY();

        Piece selectedPiece = board.getPiece(x, y);
        Set<Tile> validMoves = selectedPiece.getValidMoves(tile.getTile(), selectedPiece.getBaseMovement(), board);

        return validMoves;
    }
}
