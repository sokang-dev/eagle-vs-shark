package model;

import model.Enums.PieceType;
import model.interfaces.Piece;

public class Player {
    private String playerName;
    private PieceType pieceType;

    public Player(String playerName, PieceType pieceType) {
       this.playerName = playerName;
       this.pieceType = pieceType;
    }

    public String getPlayerName() {
        return playerName;
    }
    public PieceType getPieceType(){
        return pieceType;
    }
    public boolean isPlayerPiece(Piece piece) {
        return this.pieceType == piece.getPieceType();
    }
}
