package model;

import model.Enums.PieceType;
import model.interfaces.Piece;

import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private PieceType pieceType;
    private boolean canUndo;

    public Player(String playerName, PieceType pieceType) {
       this.playerName = playerName;
       this.pieceType = pieceType;
       this.canUndo = true;
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
    public boolean getCanUndo(){
        return this.canUndo;
    }
    public void setCanUndo(boolean canUndo){
        this.canUndo = canUndo;
    }
}
