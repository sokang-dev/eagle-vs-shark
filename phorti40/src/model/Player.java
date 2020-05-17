package model;

import model.Enums.PieceType;

import java.io.Serializable;

public class Player implements Serializable {
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
        return this.pieceType == piece.pieceType;
    }
}
