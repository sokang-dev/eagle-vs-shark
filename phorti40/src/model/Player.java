package model;

public class Player {
    private String playerName;

    private String pieceType;

    public Player(String playerName, String pieceType) {
       this.playerName = playerName;
       this.pieceType = pieceType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPieceType(){
        return pieceType;
    }
}
