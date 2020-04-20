package model;

import static resources.Constants.BOARD_HEIGHT;
import static resources.Constants.BOARD_WIDTH;

public class GameInfoPanel {

    private String currentPlayer;
    private int actionsRemaining;
    private int timeRemaining;


    // Initialises Control UI
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

    public void deductActionsRemaining(){
        actionsRemaining -= 1;
    }

    public void setActionsRemaining (int actionsRemaining){
        this.actionsRemaining = actionsRemaining;
    }

    public int getTimeRemaining(){ return timeRemaining;}

    public void setCurrentPlayer(Player player)
    {
        currentPlayer = player.getPlayerName();
    }


}
