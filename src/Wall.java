import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Wall extends Rectangle {

    private boolean direction;
    private boolean expand;
    private final double SIZE = 5;
    private GamePanel pane;

    Wall(boolean direction, double x, double y, GamePanel pane) {
        super();
        setWidth(SIZE * 4);
        setHeight(SIZE * 4);
        setX(x);
        setY(y);
        setFill(Color.BROWN);
        setStroke(Color.WHITE);
        this.pane = pane;
        expand = true;
        this.direction = direction;
    }

    double getArea() {
        return getWidth() * getHeight();
    }

    void expand() {
        int actions = 0;
        if (getX() >= 0 && direction) {
            setX(getX() - SIZE);
            setWidth(getWidth() + SIZE);
            actions++;
        }
        if (getWidth() - getX() <= pane.getWIDTH() && direction) {
            setWidth(getWidth() + SIZE);
            actions++;
        }
        if (getY() >= 0 && !direction) {
            setY(getY() - SIZE);
            setHeight(getHeight() + SIZE);
            actions++;
        }
        if (getHeight() - getY() <= pane.getHEIGHT() && !direction) {
            setHeight(getHeight() + SIZE);
            actions++;
        }
        if (actions == 0) {
            if (direction) {
                setX(0);
            } else {
                setY(0);
            }
            expand = false;
            setStroke(Color.BLACK);
            setFill(Color.BLACK);
            if (noBalls()) {
                // Remove part of the field where there are no balls
            }
        }
    }

    boolean noBalls() {
        return true;
    }

    boolean isExpand() {
        return expand;
    }
}
