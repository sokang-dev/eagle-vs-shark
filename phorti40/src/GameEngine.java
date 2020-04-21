import controller.GameController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameEngine extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        GameController gameController = new GameController();
        Scene scene = new Scene(createContent(gameController));
        primaryStage.setTitle("4040 OOSP");
        primaryStage.setScene(scene);
        primaryStage.setOnShown(event -> new Thread(() -> {
             gameController.GameStart();
         }).start());
        primaryStage.show();
    }

    private Parent createContent(GameController gameController) {
        BorderPane pane = new BorderPane();
        pane.setCenter(gameController.getBoardView());
        pane.setRight(gameController.getGameInfoPanelView());
        return pane;
    }
}
