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
     * Modeled after HW2's CannonBall intersects method. Very similar logic.
     */
    public boolean intersects(Asteroid asteroid) {
        double distance = Math.hypot(getCenterX()  - asteroid.getCenterX(), getCenterY()- asteroid.getCenterY());
        
        if (distance <=  getRadius() + asteroid.getRadius()) {
            return true;
        }
        return false;
    }

    public void addToCanvas() {
        canvas.add(bulletShape);
    }

    public void removeFromCanvas() {
        canvas.remove(bulletShape);
    }

    /*
     * This returns a boolean value verifying whether the bullet has gone out of bounds or not. We use this to then verify we can update the position
     * in updatePosition, which is then used as a conditional in the main class for animation.
     */
    public boolean boundsCheck(){
        return (initialX < canvas.getWidth() && initialX > 0 && initialY < canvas.getHeight() && initialY > 0);
    }

    public double getCenterX() {
        return bulletShape.getCenter().getX();
    }

    public double getCenterY() {
        return bulletShape.getCenter().getY();
    }

    public GraphicsObject getProjectileShape(){
        return bulletShape;
    }

    public double getRadius(){
        return BULLET_RADIUS;
    }
}
