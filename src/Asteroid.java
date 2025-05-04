import java.awt.Color;
import edu.macalester.graphics.*;

/*
 * This class handles the creation of the asteroid objects, as well as movement/wrap around math.
 */
public class Asteroid {
    private static final double ASTEROID_SPEED = 4;
    private double centerX;
    private double centerY;
    private double angle;
    private double radius;
    private Ellipse asteroidShape;
    private CanvasWindow canvas;
    
    public Asteroid(double centerX, double centerY, double angle, double radius, CanvasWindow canvas) {
        this.canvas = canvas;
        this.centerX = centerX;
        this.centerY = centerY;
        this.angle = angle;
        this.radius = radius;
    
        asteroidShape = new Ellipse(centerX, centerY, radius* 2, radius * 2);
        asteroidShape.setFillColor(Color.gray);
    }

    /*
     * Updates position of asteroid by modifying centerX and centerY 
     * and then setting the new center point.
     */
    public void updatePosition() {
        centerX += ASTEROID_SPEED * Math.cos(angle);
        centerY -= ASTEROID_SPEED * Math.sin(angle);
        setCenter(centerX, centerY);
    }

    /*
     * For that nice respawning behavior that happens in the actual game. Very similar to boundsCheck
     * in PlayerShip.
     */
    public void wrapAround(){
        double x = asteroidShape.getCenter().getX();
        double y = asteroidShape.getCenter().getY();

        if(y < -30){
            centerY = canvas.getHeight();
        }
        if(y > canvas.getHeight() + 30){
            centerY = ASTEROID_SPEED;
        }
        if(x < -30){
            centerX = canvas.getWidth();
        }
        if(x > canvas.getWidth() + 30){
           centerX = ASTEROID_SPEED;
        }
    }

    /*
     * Accessor method for center X value of Ellipse asteroidShape
     */
    public double getCenterX() {
        return asteroidShape.getCenter().getX();
    }

    /*
     * Accessor method for center Y value of Ellipse asteroidShape
     */
    public double getCenterY() {
        return asteroidShape.getCenter().getY();
    }

    /*
     * Accessor method for GraphicsObject of Ellipse asteroidShape
     */
    public GraphicsObject getShape() {
        return asteroidShape;
    }

    /*
     * Adds Ellipse asteroidShape to Canvas
     */
    public void addToCanvas(){
        canvas.add(asteroidShape);
    }

    /*
     * Removes Ellipse asteroidShape from Canvas
     */
    public void removeFromCanvas(){
        canvas.remove(asteroidShape);
    }

    /*
     * Mutator method that sets center X and center Y values for Ellipse asteroidShape
     */
    public void setCenter(double newX, double newY){
        asteroidShape.setCenter(newX, newY);
    }

    /*
     * Accessor method for double radius
     */
    public double getRadius(){
        return radius;
    }
}
