import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;

/*
 * This class handles the creation of the RocketShip object, along with the math behind movement.
 */
public class RocketShip {
    private static final double SIDE_LENGTH = 20;
    private Path rocketShape;
    private double rotationAngle, currentVelocity = 2, rotationSpeed = 5; //Good values until acceleration/deacceleration implemented.
    private long time = System.currentTimeMillis(); 
    private final long timeBetweenAccelerations = 500; // 500msec between accelerations
    
    /*
     * To construct our rocketship, we take in an initial x and y position for the ship, and use them to calculate the other two points 
     * for the triangle. We also have a rotation angle variable (initially 90 for the ship to travel in the correct direction) that tracks
     * left/right rotation of the ship.
     */
    public RocketShip (double initialX, double initialY){ 
        rocketShape = Path.makeTriangle(initialX, initialY, initialX + SIDE_LENGTH, initialY, initialX + (SIDE_LENGTH/2), initialY - SIDE_LENGTH);
        rocketShape.setStrokeColor(Color.WHITE); 
        rotationAngle = Math.toRadians(90);
    }

    /*
     * We get the current x and y position of the rocket's coordinates (have to get graphics object first, then use the getters for x and y),
     * so that we can set the position of the x and y coordinates of the ship accordingly. We use the sin/cos functions, the current velocity
     * of the ship, and the ship's angle to place the ship in the correct direction based off of it's angle.
     * 
     */
    public void up(){ //TODO: Implement deacceleration
        long currentTime = System.currentTimeMillis();
        double x = getShape().getX();
        double y = getShape().getY();
        
        for(int i = 0; i <8; i++){
            if (currentTime - time > timeBetweenAccelerations){
                if(currentVelocity <= 8){
                    currentVelocity +=0.01;
                }
            }
        }
        getShape().setX(x += currentVelocity * Math.cos(rotationAngle));
        getShape().setY(y -= currentVelocity * Math.sin(rotationAngle));
    }

    /*
     * Similar idea to the up method. We update the ship's current angle by the rotation speed, and then actually rotate the graphics object
     * by that number using the rotateBy method. To go right, we have to reduce this angle. 
     */
    public void right(){
        rotationAngle -= Math.toRadians(rotationSpeed);
        rocketShape.rotateBy(rotationSpeed);
    }
    
    /*
     * Same concept as the right method, except to go left we have add to the angle. To rotate in the opposite direction, we reverse the rotation
     * speed by negating it.
     */
    public void left(){
        rotationAngle += Math.toRadians(rotationSpeed);
        rocketShape.rotateBy(-rotationSpeed);
    } 

    public void setCenter(double newX, double newY){
        rocketShape.setCenter(newX, newY);
    }

    public double getSideLength(){
        return SIDE_LENGTH;
    }

    public double getCenterX(){
        return rocketShape.getCenter().getX();
    }

    public double getCenterY(){
        return rocketShape.getCenter().getY();
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(rocketShape);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(rocketShape);
    }

    public GraphicsObject getShape(){
        return rocketShape;
    }

    public double getRotationAngle(){
        return rotationAngle;
    }
    public double getSpeed(){
        return currentVelocity;
    }
    public void resetSpeed(){//For deacceleration
        currentVelocity = 0;
    }
}
