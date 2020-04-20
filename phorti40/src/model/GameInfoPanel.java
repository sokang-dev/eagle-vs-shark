package model;

public class GameInfoPanel {

    private String currentPlayer;
    private int actionsRemaining;
    private int timeRemaining;

    public GameInfoPanel(String currentPlayerName) {
        currentPlayer = currentPlayerName;
        actionsRemaining = 3;
        timeRemaining = 60;
    }

    public String getCurrentPlayerName() {
        return currentPlayer;
    }
    public int getActionsRemaining(){
        return actionsRemaining;
    }
    public void deductActionsRemaining(){ actionsRemaining -= 1; }
    public void setActionsRemaining (int actionsRemaining){
        this.actionsRemaining = actionsRemaining;
    }
    public int getTimeRemaining(){ return timeRemaining;}
    public void setCurrentPlayer(Player player)
    {
        currentPlayer = player.getPlayerName();
    }

}
