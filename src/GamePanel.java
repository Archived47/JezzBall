import javafx.application.Platform;
import javafx.scene.layout.Pane;

class GamePanel extends Pane implements Runnable {

    private final int WIDTH = 800;
    private final int HEIGHT = 500;

    private Thread thread;
    boolean running;

    private double FPS = 60;
    private double averageFPS;

    private Player player;
    private Level level;

    private long currentTime;

    GamePanel() {
        setPrefSize(WIDTH, HEIGHT);
        setFocused(true);
        player = new Player(this);
        level = newLevel(WIDTH, HEIGHT);
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }

    }

    @Override
    public void run() {
        running = true;

        long startTime;
        long UTime;
        long waitTime;
        long totalTime = 0;
        long targetTime = (long) (1000 / FPS);

        int frameCount = 0;
        int maxFrames = 30;

        // LOOP
        while(running) {
            startTime = System.nanoTime();

            gameUpdate();

            UTime = (System.nanoTime()-startTime) / 1000000;
            waitTime = targetTime - UTime;
            try {
                if (waitTime >= 0) {
                    Thread.sleep(waitTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == maxFrames) {
                averageFPS = 1000.0 / (totalTime/frameCount) / 1000000;
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    private void gameUpdate() {
        if (System.currentTimeMillis() - currentTime > 800) {
            currentTime = System.currentTimeMillis();
            level.timer();
        }
        if (level.areaCleared() >= 75) {
            level = newLevel(WIDTH, HEIGHT);
        } else if (level.getTime() == 0) {
            gameOver();
        }
        for (Atom atom : level.getAtoms()) {
            atom.move();
        }
        for (Wall wall : level.getWalls()) {
            if (wall.isExpand()) {
                wall.expand();
            }
        }
    }

    private void gameOver() {
        player = new Player(this);
        level = newLevel(WIDTH, HEIGHT);
    }

    private Level newLevel(int width, int height) {
        level = player.nextLevel(width, height);
        Platform.runLater(() -> {
            getChildren().clear();
            getChildren().addAll(level.getBackground());
            getChildren().addAll(level.getAtoms());
            for (Atom atom : level.getAtoms()) {
                atom.spawn();
            }
        });
        return level;
    }

    int getWIDTH() {
        return WIDTH;
    }

    int getHEIGHT() {
        return HEIGHT;
    }
}
