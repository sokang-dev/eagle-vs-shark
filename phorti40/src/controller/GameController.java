package controller;
import App.SaveStateManager;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.Event;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.util.Duration;
import model.*;
import model.Enums.PieceType;
import view.BoardView;
import view.GameInfoPanelView;

import static resources.Constants.DEFAULT_ACTIONS_REMAINING;

public class GameController {

    // Logical representation of gameboard
    private Board gameBoard;
    // Visual board
    private BoardView boardView;

    private GameInfoPanel gameInfoPanel;
    private GameInfoPanelView gameInfoPanelView;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    private Boolean gameIsOver = false;

    private long timeLimit;
    private int initialTimeLimit;

    public GameController(int timerInput) {
        InitialiseGameController(new Board(), null, timerInput, DEFAULT_ACTIONS_REMAINING);
    }

    public GameController(SaveState loadState){
        InitialiseGameController(loadState.getGameBoard(), loadState.getCurrentPlayer(), loadState.getTimeLimit(), loadState.getActionsRemaining());
    }

    private void InitialiseGameController(Board gameBoard, Player currentPlayer, int timerInput, int actionsRemaining){
        initialTimeLimit = timerInput;
        timeLimit = TimeUnit.SECONDS.toMillis(timerInput);

        //initialise the players
        playerOne = new Player("Player 1 (Shark)", PieceType.Shark);
        playerTwo = new Player("Player 2 (Eagle)", PieceType.Eagle);
        //set current turn
        this.currentPlayer = (currentPlayer == null) ? playerOne : currentPlayer;

        this.gameBoard = gameBoard;
        this.boardView = new BoardView(gameBoard);
        this.gameInfoPanel = new GameInfoPanel(this.currentPlayer.getPlayerName(), timeLimit, actionsRemaining);
        this.gameInfoPanelView = new GameInfoPanelView(gameInfoPanel, this);
        new PieceController(boardView, this);
    }


    public int GameStart() {
        initialiseTimer();

        // Overarching game loop
        while(!gameIsOver)
        {
            Turn();
        }
        return 0;
    }

    private void Turn() {
        while(gameInfoPanel.getActionsRemaining() > 0)
        {
            Thread.yield();
        }
        // After turn ends reset Actions and swap Players
        gameInfoPanel.setActionsRemaining(3);
        setNewCurrentPlayer(currentPlayer);
        gameInfoPanel.setTimeRemaining(timeLimit);
    }

    // Setup a timer that decrements time remaining, ending the turn when it hits 0
    private void initialiseTimer()
    {
        long second = 1000l;

        Timer turnTimer = new Timer();
        turnTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(gameInfoPanel.getTimeRemaining() > 0) {
                    gameInfoPanel.decrementTimeRemaining(second); // Decrement a second
                } else {
                    // End the turn
                    gameInfoPanel.setActionsRemaining(0);
                }
            }
        }, 0, 1000);

    }

    private void setNewCurrentPlayer(Player currentPlayer){
        if (currentPlayer == playerOne)
        {
            this.currentPlayer = playerTwo;
            gameInfoPanel.setCurrentPlayer(playerTwo);
        }
        else
        {
            this.currentPlayer = playerOne;
            gameInfoPanel.setCurrentPlayer(playerOne);
        }
    }

    public void handleEndTurnButton(Event event) {
        Platform.runLater(() -> gameInfoPanelView.getGameInfoPanel().setActionsRemaining(0));
    }
    public void handleSaveButton(Event event) {
        SaveState currentState = SaveStateManager.CreateSaveState(gameBoard, currentPlayer, initialTimeLimit, gameInfoPanel.getActionsRemaining());
        if (SaveStateManager.SaveState(currentState)){
            Platform.runLater(() -> gameInfoPanelView.setSaveStatusLabel("Save success."));
        }
        else {
            Platform.runLater(() -> gameInfoPanelView.setSaveStatusLabel("Save failed."));
        };
        // Hide the message after 4 seconds
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(4)
        );
        visiblePause.setOnFinished(
                pauseEvent -> gameInfoPanelView.getSaveStatusLabel().setVisible(false)
        );
        visiblePause.play();
    }

    public Board getGameBoard() {
        return this.gameBoard;
    }
    public BoardView getBoardView() {
        return this.boardView;
    }
    public GameInfoPanel getGameInfoPanel() { return this.gameInfoPanel; }
    public GameInfoPanelView getGameInfoPanelView() { return this.gameInfoPanelView; }
    public Player getCurrentPlayer() { return currentPlayer; }
}
