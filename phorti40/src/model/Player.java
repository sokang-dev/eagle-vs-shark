package model;

import model.Enums.PieceType;

public class Player {
    private String playerName;
    private PieceType pieceType;
    private long timeRemaining;

    public Player(String playerName, PieceType pieceType, long timeRemaining) {
       this.playerName = playerName;
       this.pieceType = pieceType;
       this.timeRemaining = timeRemaining;
    }

    public String getPlayerName() {
        return playerName;
    }
    public PieceType getPieceType(){
        return pieceType;
    }
    public long getTimeRemaining() { return timeRemaining; }

    public void decrementTimeRemaining(long time) { this.timeRemaining = this.timeRemaining - time; }

    public boolean isPlayerPiece(Piece piece) {
        return this.pieceType == piece.pieceType;
    }
}
