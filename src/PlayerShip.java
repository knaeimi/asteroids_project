import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

/*
 * This subclass provides bounds logic along with the math behind movement.
 */
public class PlayerShip extends RocketShip{
    private double rotationAngle, xVel, yVel, rotationSpeed = 7; 
    private final long SHOT_DELAY = 500; 
    private final long BEAM_DELAY = 2000;
    private final double THRUST = 0.15; //for a slow build up instead of instant speed
    private final double DRAG = 0.99; //for 1% reduction in speed 
    private final double HITCIRCLE_RADIUS = getSideLength()/2;
    private long time = System.currentTimeMillis(); 
    private CanvasWindow canvas;
    private ProjectileManager projectileManager;
    private Ellipse hitCircle;
   
    /*
     * For our PlayerShip, we take in an initial x and y position for the ship, and use them to calculate the other two points 
     * for the triangle. We also have a rotation angle variable (initially 90 for the ship to travel in the correct direction) that tracks
     * left/right rotation of the ship. Finally, we have an invisible hit circle (that was not so at first... had it in red for a while
     * to figure out what a good size would be for collisions) that is used to determine if an asteroid collision
     * has occured.
     */
    public PlayerShip(double initialX, double initialY, CanvasWindow canvas, ProjectileManager projectileManager){
        super(initialX, initialY);
        this.canvas = canvas;
        this.projectileManager = projectileManager;
        rotationAngle = Math.toRadians(90);
        setRocketSize(1.3);
        hitCircle = new Ellipse(getCenterX(),getCenterY(), HITCIRCLE_RADIUS * 2 , HITCIRCLE_RADIUS * 2);
    }
    
    /*
     * This method, along with rotateLeft, rotateRight, and updatePosition are what is called in the main class. This just 
     * applies a forward vector that depends on the ship's current angle. We clamp the speed for both velocities at 15 with a conditional.
     */
    public void forward(){ 
        if(Math.abs(xVel) < 15 && Math.abs(yVel) < 15){
        accelerate();
        }
    }

    /*
     * To slow down the ship on key up, we just multiply x and y velocity by 99% over time to slow down gradualy.
     */
    public void deaccelerate(){
        xVel *= DRAG;
        yVel *= DRAG;
    }

     /*
     * Similar idea to the forward method. We update the ship's current angle by the rotation speed, and then actually rotate the graphics object
     * by that number using the rotateBy method. To go right, we have to reduce this angle. 
     */
    public void rotateLeft(){
        rotationAngle += Math.toRadians(rotationSpeed);
        getShape().rotateBy(-rotationSpeed);
        hitCircle.rotateBy(-rotationSpeed);
    }
    
     /*
     * Same concept as the rotateLeft method, except to go left we have add to the angle. To rotate in the opposite direction, we reverse the rotation
     * speed by negating it.
     */
    public void rotateRight(){
        rotationAngle -= Math.toRadians(rotationSpeed);
        getShape().rotateBy(rotationSpeed);
        hitCircle.rotateBy(rotationSpeed);
    }
    
    /*
     * First we check if we're out of bounds, then update the ships current x/y positions by their coresponding velocities.
     */
    public void updatePosition(){  
        checkShipBounds();
        
        double x = getShape().getX();
        double y = getShape().getY();
        
        getShape().setX(x += xVel);
        getShape().setY(y += yVel);
        hitCircle.setCenter(getCenterX(), getCenterY());
    }   
    
    /*
     * Here, we just add thrust (which is a small number for proper speed increase. Without this thrust constant we go like all the way across the screen
     * in half a second)
     */
    public void accelerate(){
       xVel += THRUST * Math.cos(rotationAngle);
       yVel -= THRUST * Math.sin(rotationAngle);
    }

    /*
     * This is for when we shoot the beam projectile for game balancing. We just set both x/y velocities to 0 to stop the ship.
     */
    public void stopShip(){
        xVel = 0;
        yVel = 0;
    }

    /*
     * We check if the ship is out of bounds for both axes, and then set position according to what axis you went out of bounds on. We set the position
     * of the axis you didn't go out of bounds as the corresponding current velocity to maintain speed.
     */
    private void checkShipBounds(){
        if(getCenterY() < -30){ 
            setCenter(getCenterX(), canvas.getHeight());
        }
        if(getCenterY() > canvas.getHeight() + 30){
            setCenter(getCenterX(), yVel); 
        }

        if(getCenterX() < -30){ 
            setCenter(canvas.getWidth(), getCenterY());
        }

        if(getCenterX() > canvas.getWidth() + 30){
            setCenter(xVel, getCenterY());
        }
    }

    /*
     * We use that hit circle, along with more HW2 style logic to determine if a collision has occured.
     */
    public boolean intersectsAsteroid(Asteroid asteroid){
        double distance = Math.hypot(getCenterX()  - asteroid.getCenterX(), getCenterY()- asteroid.getCenterY());
        
        if (distance <=  HITCIRCLE_RADIUS + asteroid.getRadius()) {
            return true;
        }
        return false;
    }

    /*
     * The main function of this method is just to call the addBulletProjectile method, but I also went ahead and added some tricky math to
     * mitigate bullet spamming.
     */
    public void fireBulletProjectile(){
        long currentTime = System.currentTimeMillis();
            if (currentTime - time > SHOT_DELAY){
                projectileManager.addBulletProjectile(getCenterX(), getCenterY() - getSideLength()/2, 
                rotationAngle);
                time = currentTime;
            }
    }

    /*
     * Same concept as the fireBulletProjectile method, but we have a two second delay here instead because of how powerful the beam is. For
     * additional game balancing, we stop the ship if a successful shot happens (but we don't punish the user for simply pressing f- only when they
     * have the beam available)
     */
    public void fireBeamProjectile(){ 
        long currentTime = System.currentTimeMillis();
        if (currentTime - time > BEAM_DELAY){ 
            stopShip();
            projectileManager.addBeamProjectile(getCenterX(), getCenterY(), rotationAngle);
            time = currentTime;
        }
    }

    public void fireSpreadProjectile(){
        long currentTime = System.currentTimeMillis();
            if (currentTime - time > SHOT_DELAY){
                projectileManager.addBulletProjectile(getCenterX(), getCenterY() - getSideLength()/2, 
                rotationAngle);
                
                for(int i = 0; i < 4; i++){
                   projectileManager.addBulletProjectile(getCenterX(), getCenterY() - getSideLength()/2, 
                rotationAngle - (100 * i));
                }

                 for(int i = 0; i < 4; i++){
                   projectileManager.addBulletProjectile(getCenterX(), getCenterY() - getSideLength()/2, 
                rotationAngle + (100 * i));
                }
                time = currentTime;
            }
    }
}
