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
        this.setPrefWidth(500);
        this.setSpacing(20);

        Label heading = new Label("Sharks vs. Eagles");
        heading.setFont(Font.font(25));

        TextField turnTimerInput = new TextField();
        turnTimerInput.setPromptText("Turn Timer Length");
        turnTimerInput.setFocusTraversable(false); // So the input doesn't immediately get focus

        Button newGameButton = new Button("New Game");
        newGameButton.setAlignment(Pos.CENTER);
        newGameButton.setOnAction(event -> {
            mainMenuController.handleNewGameButton(Integer.parseInt(turnTimerInput.getText()), event);
        });

        Button resumeGameButton = new Button("Resume Game");
        resumeGameButton.setOnAction(event -> {
            mainMenuController.handleResumeGame(event);
        });
        HBox newGameBox = new HBox(turnTimerInput, newGameButton, resumeGameButton);
        newGameBox.setSpacing(5);

        this.getChildren().addAll(heading, newGameBox);
    }
}
