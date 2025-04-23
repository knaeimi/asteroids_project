
import edu.macalester.graphics.CanvasWindow;

/*
 * This subclass provides bounds logic along with the math behind movement.
 */
public class PlayerShip extends RocketShip{
    private CanvasWindow canvas;
    private double rotationAngle, currentVelocity = 2, rotationSpeed = 5; //Good values until acceleration/deacceleration implemented.
    private long time = System.currentTimeMillis(); 
    private final long timeBetweenAccelerations = 500; // 500msec between accelerations
    private final long milisecBetweenShots = 500; // 500ms between shots
    private final long milisecBetweenBeams = 500;
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
    public void forward(){ //TODO: Implement deacceleration
        updatePosition();
        accelerate();
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

    public void fireBulletProjectile(){
        long currentTime = System.currentTimeMillis();
            if (currentTime - time > milisecBetweenShots){
                projectileManager.addBulletProjectile(getCenterX(), getCenterY() - getSideLength()/2, 
                getRotationAngle());
                time = currentTime;
            }
    }

    public void fireBeamProjectile(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - time > milisecBetweenBeams){
            projectileManager.addBeamProjectile(getCenterX(), getCenterY() - getSideLength()/2, getRotationAngle());
            time = currentTime;
        }
    }

    private void checkShipBounds(){
        if(getCenterY() < 0){ 
            setCenter(getCenterX(), canvas.getHeight());
        }
        if(getCenterY() > canvas.getHeight()){
            setCenter(getCenterX(), getSpeed()); 
        }

        if(getCenterX() < 0){ 
            setCenter(canvas.getWidth(), getCenterY());
        }

        if(getCenterX() > canvas.getWidth()){
            setCenter(getSpeed(), getCenterY());
        }
    }

    public void accelerate(){
        long currentTime = System.currentTimeMillis();
        for(int i = 0; i <8; i++){
            if (currentTime - time > timeBetweenAccelerations){
                if(currentVelocity <= 8){
                    currentVelocity +=0.01;
                }
            }
        }
    }

    public void updatePosition(){
        double x = getShape().getX();
        double y = getShape().getY();
        
        getShape().setX(x += currentVelocity * Math.cos(rotationAngle));
        getShape().setY(y -= currentVelocity * Math.sin(rotationAngle));
    }
    
    public double getRotationAngle(){
        return rotationAngle;
    }
    public double getSpeed(){
        return currentVelocity;
    }
}
