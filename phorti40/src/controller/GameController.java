package controller;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;
import model.Enums.PieceType;
import resources.Constants;
import resources.Sprites;
import view.BoardView;
import view.GameInfoPanelView;
import view.TileView;

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

    public GameController() {

        //initialise the players
        playerOne = new Player("Player 1 (Shark)", PieceType.Shark);
        playerTwo = new Player("Player 2 (Eagle)", PieceType.Eagle);
        //set current turn
        currentPlayer = playerOne;

        this.gameBoard = new Board();
        this.boardView = new BoardView();
        this.gameInfoPanel = new GameInfoPanel(currentPlayer.getPlayerName());
        this.gameInfoPanelView = new GameInfoPanelView(gameInfoPanel);
        initialiseBoard();
    }

    public int GameStart(){
        //Overarching game loop
        while(!gameIsOver)
        {
            Turn();
        }
        return 0;
    }

    private void Turn(){
        while(gameInfoPanel.getActionsRemaining() >0)
        {
            Thread.yield();
        }
        //After turn ends reset Actions and swap Players
        gameInfoPanel.setActionsRemaining(3);
        setNewCurrentPlayer(currentPlayer);
    }

    private void initialiseBoard() {
        for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
            for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                TileView tile = new TileView(i, j);

                // Set the appropriate sprite
                // Note: best way to do this is probably a bunch of ifs
                if (gameBoard.getPiece(i, j) instanceof DummyShark)
                    tile.setSprite(new DummyShark(tile.getTile()), new ImageView(Sprites.Shark));

                if (gameBoard.getPiece(i, j) instanceof DummyEagle)
                    tile.setSprite(new DummyEagle(tile.getTile()), new ImageView(Sprites.Eagle));

                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
                boardView.getChildren().addAll(tile);
            }
        }
        PieceController pieceController = new PieceController(boardView, this);
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
    public Player getCurrentPlayer(){ return currentPlayer; }
}
