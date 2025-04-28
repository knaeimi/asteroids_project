import java.util.Random;

import edu.macalester.graphics.*;

public class Meteor {
    private double centerX;
    private double centerY;
    private double angle;
    private Image meteorImage;
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
    
        meteorImage = new Image("meteor.png");
        meteorImage.setScale(random.nextDouble(0.1,0.3));
        meteorImage.setCenter(centerX, centerY);
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setPosition(double newX, double newY){
        meteorImage.setCenter(newX, newY);
    }

    public GraphicsObject getShape() {
        return meteorImage;
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(meteorImage);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(meteorImage);
    }

    public CanvasWindow getCanvas(){
        return canvas;
    }

    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Checks the distance between playerShip and the Meteor object
     */
    public boolean isCollidingWithShip(PlayerShip playerShip) {

        double dx = this.centerX + 250 - playerShip.getCenterX();
        double dy = this.centerY + 250 - playerShip.getCenterY();
        double distance = Math.sqrt(dx * dx + dy * dy);
    
        return distance < (METEOR_RADIUS + playerShip.getRadius());
    }

    public boolean isCollidingWithProjectile(ProjectileManager projectileManager) {
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
        meteorImage.setX(centerX += METEOR_SPEED * Math.cos(angle));
        meteorImage.setY(centerY -= METEOR_SPEED * Math.sin(angle));
        boundsCheck();
    }

    public void boundsCheck(){
        if(this.centerX < 0 || this.centerX > canvas.getWidth() || this.centerY < 0 || this.centerY > canvas.getHeight()){
            isActive = false;
        }
        else{
            isActive = true;
        }
    }

    public double getRadius() {
        return METEOR_RADIUS;
    }
}
