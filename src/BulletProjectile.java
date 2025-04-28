import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

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

    public boolean updatePosition() {
      bulletShape.setX(initialX += VELOCITY * Math.cos(angle));
      bulletShape.setY(initialY -= VELOCITY * Math.sin(angle));
      return boundsCheck();
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
        return (initialX < canvas.getWidth() || initialX > 0 || initialY < canvas.getHeight() || initialY > 0);
    }

    public double getCenterX() {
        return bulletShape.getCenter().getX();
    }

    public double getCenterY() {
        return bulletShape.getCenter().getY();
    }

    public boolean isCollidingWithMeteor(MeteorManager meteorManager) {
        synchronized (meteorManager) {
            for(Meteor m : meteorManager.getMeteorList()){
                if(!m.getIsActive()){
                    double dx = this.getCenterX() - (m.getCenterX() + 200);
                    double dy = this.getCenterY() - m.getCenterY();
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    // System.out.println("Distance to meteor: " + distance);
                    // System.out.println("Sum of radii: " + (BULLET_RADIUS + m.getRadius()));
                    if(distance < (BULLET_RADIUS + m.getRadius())){
                        m.setPosition(1000, 1000); //Temporary fix, because removeFromCanvas is causing crashes
                        return true;
                    };
                }
            }
            return false;
        }
    }
}
