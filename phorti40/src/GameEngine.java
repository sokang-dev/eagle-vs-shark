import controller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameEngine extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    GameController gameController;
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("4040 OOSP");
        primaryStage.setScene(scene);
        primaryStage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
               new Thread(() -> {
                    gameController.GameStart();
                }).start();
            }
        });
        primaryStage.show();
    }

    private Parent createContent() {
        BorderPane pane = new BorderPane();
        gameController = new GameController();

        pane.setCenter(gameController.getBoardView());
        pane.setRight(gameController.getGameInfoPanelView());
        return pane;
    }
}
