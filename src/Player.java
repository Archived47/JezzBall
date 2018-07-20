import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

class Player {
    private Pane pane;
    private boolean direction;
    private int number = 0;
    private int lives = 0;
    private int score = 0;
    private Level level;

    Player(Pane pane) {
        this.pane = pane;
        direction = true;
        pane.setCursor(Cursor.W_RESIZE);
        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                direction = !direction;
                pane.setCursor(direction ? Cursor.W_RESIZE : Cursor.N_RESIZE);
            } else if (event.getButton() == MouseButton.PRIMARY) {
                buildWall(event);
            }
        });
    }

    private void buildWall(MouseEvent event) {
        //TODO
        Wall wall = null;
        if (correctPlace()) {
            wall = new Wall(direction, event.getX(), event.getY(), (GamePanel) pane);
        }
        if (wall != null) {
            level.getWalls().add(wall);
            Wall addedWall = wall;
            Platform.runLater(() -> {
                pane.getChildren().add(addedWall);
                level.getWalls().add(addedWall);
            });
        }
        // Do something with Score
    }

    private boolean correctPlace() {
        // TODO
        return true;
    }

    boolean isDirection() {
        return direction;
    }

    Level nextLevel(int width, int height) {
        number += 1;
        level = new Level(number, width, height);
        lives = number < 49 ? number + 1 : 50;
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}
