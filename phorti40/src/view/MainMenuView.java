package view;

import controller.MainMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainMenuView extends VBox {

    MainMenuController mainMenuController;

    public MainMenuView(MainMenuController mainMenuController) {
        super();
        this.mainMenuController = mainMenuController;
        drawMainMenu();
    }

    private void drawMainMenu(){
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setPrefWidth(650);
        this.setSpacing(20);

        Label heading = new Label("Sharks vs. Eagles");
        heading.setFont(Font.font(25));

        TextField turnTimerInput = new TextField();
        turnTimerInput.setPromptText("Turn Timer Length (seconds)");
        turnTimerInput.setFocusTraversable(false); // So the input doesn't immediately get focus

        // The length and width of the game board
        TextField boardSizeInput = new TextField();
        boardSizeInput.setPromptText("Board Width/Height");
        boardSizeInput.setFocusTraversable(false);

        // Number of pieces per side
        TextField pieceCountInput = new TextField();
        pieceCountInput.setPromptText("Pieces per side");
        pieceCountInput.setFocusTraversable(false);


        Button newGameButton = new Button("New Game");
        newGameButton.setAlignment(Pos.CENTER);
        newGameButton.setOnAction(event -> {
            mainMenuController.handleNewGameButton(
                    Integer.parseInt(turnTimerInput.getText()),
                    Integer.parseInt(boardSizeInput.getText()),
                    Integer.parseInt(pieceCountInput.getText()), event
            );
        });

        HBox newGameBox = new HBox(turnTimerInput, boardSizeInput, pieceCountInput, newGameButton);
        newGameBox.setSpacing(5);

        this.getChildren().addAll(heading, newGameBox);
    }
}
