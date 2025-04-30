import java.awt.Color;
import edu.macalester.graphics.*;

public class Asteroid {
    private double centerX;
    private double centerY;
    private double angle;
    private double radius;
    private Ellipse asteroidShape;
    private static final double ASTEROID_SPEED = 5;
    
    public Asteroid(double centerX, double centerY, double angle, double radius, CanvasWindow canvas) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.angle = angle;
        this.radius = radius;
    
        asteroidShape = new Ellipse(centerX, centerY, radius* 2, radius * 2);
        asteroidShape.setFillColor(Color.gray);
    }

    public double getCenterX() {
        return asteroidShape.getCenter().getX();
    }

    public double getCenterY() {
        return asteroidShape.getCenter().getY();
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
   
    public void setCenter(double newX, double newY){
        asteroidShape.setCenter(newX, newY);
    }

    public double getRadius(){
        return radius;
    }
}
