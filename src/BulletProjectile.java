import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

/*
 * This class handles creating the BulletProjectile objects, and provides an update method 
 */
public class BulletProjectile implements Projectile {
    private CanvasWindow canvas;
    private final double VELOCITY = 20;
    private final double BULLET_RADIUS = 10;
    private Ellipse bulletShape;
    private double initialX;
    private double initialY;
    private double angle;
 
    /*
     * This constructor takes in the rocketship x and y values and uses them to assign a location to the bullet.
     */
    public BulletProjectile(double initialX, double initialY, double angle, CanvasWindow canvas){
        this.canvas = canvas;
        this.initialX = initialX;
        this.initialY = initialY;
        this.angle = angle;
    
        bulletShape = new Ellipse(initialX,initialY,BULLET_RADIUS * 2,BULLET_RADIUS * 2);
        bulletShape.setFillColor(new Color(150,0,255));
        addToCanvas();
    }

    /*
     * We both update the bullets position and return a boolean to mark for list/canvas removal if a bullet
     * is out of bounds.
     */
    public boolean updatePosition() {
      bulletShape.setX(initialX += VELOCITY * Math.cos(angle));
      bulletShape.setY(initialY -= VELOCITY * Math.sin(angle));
      return boundsCheck();
    }

     /*
     * This returns a boolean value verifying whether the bullet has gone out of bounds or not. We use this to then verify we can update the position
     * in updatePosition, which is then used as a conditional in the main class for animation.
     */
    public boolean boundsCheck(){
        return (initialX < canvas.getWidth() && initialX > 0 && initialY < canvas.getHeight() && initialY > 0);
    }
    
    /*
     * Modeled after HW2's CannonBall intersects method. Very similar logic.
     */
    public boolean intersects(Asteroid asteroid) {
        double distance = Math.hypot(getCenterX()  - asteroid.getCenterX(), getCenterY()- asteroid.getCenterY());
        
        if (distance <=  getRadius() + asteroid.getRadius()) {
            return true;
        }
        return false;
    }

    /*
     * Adds the bullet object to the canvas.
     */
    public void addToCanvas() {
        canvas.add(bulletShape);
    }

    /*
     * Removes the bullet object from the canvas.
     */
    public void removeFromCanvas() {
        canvas.remove(bulletShape);
    }

    /*
     * Returns the center X value of the bullet object ellipse.
     */
    public double getCenterX() {
        return bulletShape.getCenter().getX();
    }

    /*
     * Returns the center Y value of the bullet object ellipse.
     */
    public double getCenterY() {
        return bulletShape.getCenter().getY();
    }

    /*
     * Returns the shape of the bullet for collision management.
     */
    public GraphicsObject getProjectileShape(){
        return bulletShape;
    }

    /*
     * Returns the radius of the bullet object ellipse which is a constant variable.
     */
    public double getRadius(){
        return BULLET_RADIUS;
    }
}
