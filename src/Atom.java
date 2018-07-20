import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Random;

class Atom extends Circle {

    private Level level;
    private double vX, vY;

    Atom(Level level) {
        this.level = level;

        spawn();

        // Make it visible
        setStroke(Color.BLACK);
        setFill(Color.WHITE);
        setRadius(20);
    }

    void move() {
        collide();
        setCenterX(getCenterX() + vX);
        setCenterY(getCenterY() + vY);
    }

    private void collide() {
        // Check with outer walls
        if (getCenterX() + getRadius() >= level.getWIDTH()) { // Hit right side of wall
            setVelocity(-1, 0);
        } else if (getCenterX() - getRadius() <= 0) { // Hit left side of wall
            setVelocity(1,0);
        } else if (getCenterY() + getRadius() >= level.getHEIGHT()) { // Hit bottom side of wall
            setVelocity(0, -1);
        } else if (getCenterY() - getRadius() <= 0) { // Hit top side of wall
            setVelocity(0, 1);
        }
        // Check with inner walls
        // TODO Pulls instead of resists, needs refactoring
        List<Wall> walls = level.getWalls();
        for (Wall wall : walls) {
            if (getCenterX() + getRadius() <= wall.getX()) { // Hit left side of wall
                setVelocity(1,0);
            } else if (getCenterX() - getRadius() >= wall.getX() + wall.getWidth()) { // Hit right side of wall
                setVelocity(-1, 0);
            } else if(getCenterY() + getRadius() <= wall.getY()) { // Hit top side of wall
                setVelocity(0, 1);
            } else if(getCenterY() - getRadius() >= wall.getY() + wall.getHeight()) { // Hit bottom side of wall
                setVelocity(0, -1);
            }
        }
        // Check with other atoms
        Atom[] atoms = level.getAtoms();
        for (Atom atom : atoms) {
            if (atom != this) {
                touches(atom);
            }
        }
    }

    private void setVelocity(int vX, int vY) {
        if (vX != 0) this.vX = vX;
        if (vY != 0) this.vY = vY;
    }

    void spawn() {
        // Set the X and Y value of the Atom
        Random random = new Random();
        setCenterX(level.getWIDTH() * random.nextDouble());
        setCenterY(level.getHEIGHT() * random.nextDouble());

        // Fix the X if wrong
        if (getCenterX() + getRadius() >= level.getWIDTH()) {
            setCenterX(getCenterX() - getRadius());
        } else if (getCenterX() - getRadius() <= level.getWIDTH()) {
            setCenterX(getCenterX() + getRadius());
        }

        // Fix the Y if wrong
        if (getCenterY() + getRadius() >= level.getHEIGHT()) {
            setCenterY(getCenterY() - getRadius());
        } else if (getCenterY() - getRadius() <= level.getHEIGHT()) {
            setCenterY(getCenterY() + getRadius());
        }

        // Assign an initial speed
        double speed = 1;
        vX = random.nextDouble() < 0.5 ? -speed : speed;
        vY = random.nextDouble() < 0.5 ? -speed : speed;
    }

    void touches(Atom atom) {
        //TODO
    }
}
