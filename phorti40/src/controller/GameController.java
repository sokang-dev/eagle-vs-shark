package controller;
import App.SaveStateManager;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.Event;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.util.Duration;
import model.*;
import model.Enums.PieceType;
import model.Enums.StatusType;
import model.interfaces.Piece;
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

    private PieceController pieceController;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    private Boolean gameIsOver = false;
    private long timeLimit;
    private int initialTimeLimit;

    public GameController(int timerInput, int boardSizeInput, int pieceCountInput) {
        InitialiseGameController(new Board(boardSizeInput, pieceCountInput), null, timerInput, DEFAULT_ACTIONS_REMAINING);
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
        pieceController = new PieceController(boardView, this);
        this.gameInfoPanelView = new GameInfoPanelView(gameInfoPanel, this);
    }

    public int GameStart() {
        initialiseTimer();

        // Overarching game loop
        while(!gameIsOver)
        {
            autoSaveGameMemento(createGameMemento());
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
        pieceController.pieceReset();
        // Lower stun duration
        lowerStatusDuration(currentPlayer.getPieceType(), StatusType.Stun);
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

    private void lowerStatusDuration(PieceType pieceType, StatusType statusType) {
        Set<Piece> pieces = gameBoard.getAllPieces();

        for (Piece piece : pieces) {
            // Only lower status duration when the opposite piece turn ends
            if (piece.getPieceType() == pieceType) continue;

            Status status = piece.getStatus(statusType);

            if (status != null) {
                piece.setStatus(StatusType.Stun, status.getDuration() - 1);
            }
        }
    }

    public void handleEndTurnButton(Event event) {
        Platform.runLater(() -> gameInfoPanelView.getGameInfoPanel().setActionsRemaining(0));
    }

    public void handleSaveButton(Event event) {
        gameInfoPanelView.getSaveStatusLabel().setVisible(true);
        if (SaveStateManager.SaveState(createGameMemento())){
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

    public void handleUndo(Event event) {
        restoreGame(SaveStateManager.Undo(2));
    }

    private void restoreGame(GameMemento memento){
        this.gameBoard=memento.getState().getGameBoard();
        this.currentPlayer = memento.getState().getCurrentPlayer();
        this.getGameInfoPanel().setActionsRemaining(memento.getState().getActionsRemaining());
        this.pieceController.setBoard(gameBoard);
        gameBoard.updatePiecesOnRestore();
        this.boardView.setBoard(gameBoard);
        this.boardView.setBoardView(this.boardView.RestoreTileView());
    }

    private GameMemento createGameMemento(){
        SaveState saveState = new SaveState((Board) gameBoard.clone(), currentPlayer, initialTimeLimit, gameInfoPanel.getActionsRemaining());
        return new GameMemento(saveState);
    }

    private void autoSaveGameMemento(GameMemento memento)
    {
        SaveStateManager.SaveGameMemento(memento);
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
    public PieceController getPieceController() {
        return this.pieceController;
    }
}
