
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {

    /*
        Game starts with 2 atoms
        These atoms need to be in a game loop and will bounce of the walls
        Player can point at a location, and will start to build a wall from there on forward
        If player presses Right-Click, it will change between Vertical and Horizontal
        If ball collides with a wall, while it is building player loses a life, wall will stop building
        Player needs to capture at least 75% of the room in a time limit
        When 75% has been captured, the number is done
        Every number gives 1 more ball than the previous, until number 50
     */
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new GamePanel();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("JezzBall");

        stage.show();

        stage.setOnCloseRequest(event -> {
            ((GamePanel) pane).running = false;
        });
    }
}
