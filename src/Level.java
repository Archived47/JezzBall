import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

class Level {
    private int time = 0;
    private Atom[] atoms;
    private final double WIDTH;
    private final double HEIGHT;
    private final int SIZE = 20;
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Rectangle> background = new ArrayList<>();

    Level(int level, int width, int height) {
        time = 1250 + (250 * level);
        atoms = new Atom[level < 49 ? level + 1 : 50];

        for (int i = 0; i < atoms.length; i++) {
            atoms[i] = new Atom(this);
        }

        WIDTH = width;
        HEIGHT = height;

        for (int x = 0; x < WIDTH; x+= SIZE) {
            for (int y = 0; y < HEIGHT; y += SIZE) {
                Rectangle rectangle = new Rectangle(x, y, width, height);
                rectangle.setStroke(Color.BEIGE);
                background.add(rectangle);
            }
        }
    }

    int areaCleared() {
        double area = WIDTH * HEIGHT;
        double wallArea = 0;
        for (Wall wall : walls) {
            area += wall.getArea();
        }
        return (int) ((area - (area - wallArea)) / area * 100);
    }

    ArrayList<Rectangle> getBackground() {
        return background;
    }

    Atom[] getAtoms() {
        return atoms;
    }

    ArrayList<Wall> getWalls() {
        return walls;
    }

    double getHEIGHT() {
        return HEIGHT;
    }

    double getWIDTH() {
        return WIDTH;
    }

    int getTime() {
        return time;
    }

    void timer() {
        time -= 1;
    }
}
