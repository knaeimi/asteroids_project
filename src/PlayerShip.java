
import edu.macalester.graphics.CanvasWindow;

/*
 * This subclass provides bounds logic along with the math behind movement.
 */
public class PlayerShip extends RocketShip{
    private double rotationAngle, currentVelocity, rotationSpeed = 8; 
    private final long milisecBetweenShots = 500; // 500ms between shots. Also, if we're just going to use the same value we don't need another variable.
    private final long milisecBetweenBeams = 2000;
    private final long timeToFinalVel = 150;
    private final long finalVel = 12;
    private long time = System.currentTimeMillis(); 
    private CanvasWindow canvas;
    private ProjectileManager projectileManager;
   
    /*
     * For our PlayerShip, we take in an initial x and y position for the ship, and use them to calculate the other two points 
     * for the triangle. We also have a rotation angle variable (initially 90 for the ship to travel in the correct direction) that tracks
     * left/right rotation of the ship.
     */
    public PlayerShip(double initialX, double initialY, CanvasWindow canvas, ProjectileManager projectileManager){
        super(initialX, initialY);
        this.canvas = canvas;
        this.projectileManager = projectileManager;
        rotationAngle = Math.toRadians(90);
    }
    
    /*
     * This method, along with rotateLeft and rotateRight, are what is called in the main class. We call up from RocketShip, and then check if
     * the ship is out of bounds; if they are, we respawn them on the opposite side of the canvas (preserving the mechanic in the original game).
     * Note: We set the x/y position where the ship went out of bounds to getSpeed as shorthand for adding the starting position of the axis we want to 
     * respawn + current speed (which would just be 0 + getSpeed).
     */
    public void forward(){ //TODO: Kian: Implement deacceleration by tomorow night
        accelerate();
        updatePosition();
        checkShipBounds();
    }

     /*
     * Similar idea to the forward method. We update the ship's current angle by the rotation speed, and then actually rotate the graphics object
     * by that number using the rotateBy method. To go right, we have to reduce this angle. 
     */
    public void rotateLeft(){
        rotationAngle += Math.toRadians(rotationSpeed);
        getShape().rotateBy(-rotationSpeed);
    }
    
     /*
     * Same concept as the rotateLeft method, except to go left we have add to the angle. To rotate in the opposite direction, we reverse the rotation
     * speed by negating it.
     */
    public void rotateRight(){
        rotationAngle -= Math.toRadians(rotationSpeed);
        getShape().rotateBy(rotationSpeed);
    }
    
    public void updatePosition(){
        double x = getShape().getX();
        double y = getShape().getY();
        
        getShape().setX(x += currentVelocity * Math.cos(rotationAngle));
        getShape().setY(y -= currentVelocity * Math.sin(rotationAngle));
    }
    
    /*
     * Here, we use the basic acceleration formula and a conditional to give the ship some weight.
     */
    public void accelerate(){
        double acceleration = (finalVel - currentVelocity) / timeToFinalVel;
        if(currentVelocity != finalVel){
            currentVelocity += acceleration;
        }
    }
    
    private void checkShipBounds(){
        if(getCenterY() < 0){ 
            setCenter(getCenterX(), canvas.getHeight());
        }
        if(getCenterY() > canvas.getHeight()){
            setCenter(getCenterX(), currentVelocity); 
        }

        if(getCenterX() < 0){ 
            setCenter(canvas.getWidth(), getCenterY());
        }

        if(getCenterX() > canvas.getWidth()){
            setCenter(currentVelocity, getCenterY());
        }
    }

    /*
     * The main function of this method is just to call the addBulletProjectile method, but I also went ahead and added some tricky math to
     * mitigate bullet spamming.
     */
    public void fireBulletProjectile(){
        long currentTime = System.currentTimeMillis();
            if (currentTime - time > milisecBetweenShots){
                projectileManager.addBulletProjectile(getCenterX(), getCenterY() - getSideLength()/2, 
                rotationAngle);
                time = currentTime;
            }
    }

    public void fireBeamProjectile(){ //TODO: Sean, this is the exact same logic as the method above, can we refactor this somehow?
        long currentTime = System.currentTimeMillis();
        if (currentTime - time > milisecBetweenBeams){
            projectileManager.addBeamProjectile(getCenterX(), getCenterY(), rotationAngle);
            time = currentTime;
        }
    }
}
