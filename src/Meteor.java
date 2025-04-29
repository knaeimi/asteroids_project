import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.*;

public class Meteor {
    private double centerX;
    private double centerY;
    private double angle;
    private Ellipse meteorShape;
    private Random random = new Random();
    private CanvasWindow canvas;
    private boolean isActive;
    private static final double METEOR_SPEED = 2;
    private static final double METEOR_RADIUS = 15;

    public Meteor(double centerX, double centerY, double angle, CanvasWindow canvas) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.angle = angle;
        this.canvas = canvas;
    
        meteorShape = new Ellipse(centerX, centerY, METEOR_RADIUS * 2, METEOR_RADIUS * 2);
        meteorShape.setScale(random.nextDouble(2,3));
        meteorShape.setFillColor(Color.gray);
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setPosition(double newX, double newY){
        meteorShape.setCenter(newX, newY);
    }

    public GraphicsObject getShape() {
        return meteorShape;
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(meteorShape);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(meteorShape);
    }

    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Checks the distance between playerShip and the Meteor object
     */
    public boolean isCollidingWithShip(PlayerShip playerShip) { //TODO: Refactor

        // double dx = this.centerX + 250 - playerShip.getCenterX();
        // double dy = this.centerY + 250 - playerShip.getCenterY();
        // double distance = Math.sqrt(dx * dx + dy * dy);
    
        // return distance < (METEOR_RADIUS + playerShip.getRadius());
        return true; // until refactored: (playerShip doesn't have a radius- we'll check the 4 compass points similar to the Breakout implementation)
    }

    public boolean isCollidingWithProjectile(ProjectileManager projectileManager) { //TODO: Refactor
        GraphicsObject centerObj = canvas.getElementAt(this.getCenterX(), this.getCenterY());
        if(centerObj != null){
            for(Projectile p : projectileManager.getProjList()){
                GraphicsObject pShape = p.getProjectileShape();
                if (pShape == centerObj) {
                    return true;
                }
            }    
        }
        return false;  
    }

    public void updatePosition() {
        meteorShape.setX(centerX += METEOR_SPEED * Math.cos(angle));
        meteorShape.setY(centerY -= METEOR_SPEED * Math.sin(angle));
        // boundsCheck(); //TODO: Will use once meteors respawn on other side like ship
    }

    // public void boundsCheck(){ //TODO: Refactor
    //     if(this.centerX < 0 || this.centerX > canvas.getWidth() || this.centerY < 0 || this.centerY > canvas.getHeight()){
    //         isActive = false;
    //     }
    //     else{
    //         isActive = true;
    //     }
    // }

    public double getRadius() {
        return METEOR_RADIUS;
    }
}
