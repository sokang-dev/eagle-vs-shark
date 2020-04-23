package controller;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;
import model.Eagle.AttackEagle;
import model.Eagle.DummyEagle;
import model.Eagle.UtilityEagle;
import model.Enums.PieceType;
import model.Shark.AttackShark;
import model.Shark.DummyShark;
import model.Shark.UtilityShark;
import resources.Constants;
import resources.Sprites;
import view.BoardView;
import view.GameInfoPanelView;
import view.TileView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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

    public GameController(int timerInput) {
        //initialise the players
        playerOne = new Player("Player 1 (Shark)", PieceType.Shark, TimeUnit.SECONDS.toMillis(timerInput));
        playerTwo = new Player("Player 2 (Eagle)", PieceType.Eagle, TimeUnit.SECONDS.toMillis(timerInput));
        //set current turn
        currentPlayer = playerOne;

        this.gameBoard = new Board();
        this.boardView = new BoardView(gameBoard);
        this.gameInfoPanel = new GameInfoPanel(currentPlayer.getPlayerName(), currentPlayer.getTimeRemaining());
        this.gameInfoPanelView = new GameInfoPanelView(gameInfoPanel);
        PieceController pieceController = new PieceController(boardView, this);
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
        gameInfoPanel.setTimeRemaining(currentPlayer.getTimeRemaining());
    }

    // Setup a timer that decrements the current players time, ending the game when it hits 0
    private void initialiseTimer()
    {
        long second = 1000l;

        Timer turnTimer = new Timer();
        turnTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(currentPlayer.getTimeRemaining() > 0) {
                    currentPlayer.decrementTimeRemaining(second); // Decrement a second
                    gameInfoPanel.setTimeRemaining(currentPlayer.getTimeRemaining());
                } else {
                    gameIsOver = true;
                    turnTimer.cancel();
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
