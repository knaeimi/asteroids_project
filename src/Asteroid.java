import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.*;

public class Asteroid {
    private double centerX;
    private double centerY;
    private double angle;
    private Ellipse asteroidShape;
    private Random random = new Random();
    private CanvasWindow canvas;
    private static final double ASTEROID_SPEED = 2;
    private static final double ASTEROID_RADIUS = 15;

    public Asteroid(double centerX, double centerY, double angle, CanvasWindow canvas) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.angle = angle;
        this.canvas = canvas;
    
        asteroidShape = new Ellipse(centerX, centerY, ASTEROID_RADIUS * 2, ASTEROID_RADIUS * 2);
        asteroidShape.setScale(random.nextDouble(2,3));
        asteroidShape.setFillColor(Color.gray);
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setPosition(double newX, double newY){
        asteroidShape.setCenter(newX, newY);
    }

    public GraphicsObject getShape() {
        return asteroidShape;
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(asteroidShape);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(asteroidShape);
    }
  
    public void updatePosition() {
        asteroidShape.setX(centerX += ASTEROID_SPEED * Math.cos(angle));
        asteroidShape.setY(centerY -= ASTEROID_SPEED * Math.sin(angle));
    }
}
