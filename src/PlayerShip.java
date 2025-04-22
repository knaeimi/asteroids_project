
import edu.macalester.graphics.CanvasWindow;

/*
 * This subclass provides bounds logic and implements the movement methods defined in RocketShip. 
 */
public class PlayerShip extends RocketShip{
    private CanvasWindow canvas;
    
    public PlayerShip(double initialX, double initialY, CanvasWindow canvas){
        super(initialX, initialY);
        this.canvas = canvas;
    }
    
    /*
     * This method, along with rotateLeft and rotateRight, are what is called in the main class. We call up from RocketShip, and then check if
     * the ship is out of bounds; if they are, we respawn them on the opposite side of the canvas (preserving the mechanic in the original game).
     * Note: We set the x/y position where the ship went out of bounds to getSpeed as shorthand for adding the starting position of the axis we want to 
     * respawn + current speed (which would just be 0 + getSpeed).
     */
    public void forward(){
        up();
           
        if(getCenterY() < 0){ //spawning @ bottom/top
            setCenter(getCenterX(), canvas.getHeight());
        }
        if(getCenterY() > canvas.getHeight()){
            setCenter(getCenterX(), getSpeed()); // 0 + getSpeed, simplifies to getSpeed
        }

        if(getCenterX() < 0){ //spawning @ right/left
            setCenter(canvas.getWidth(), getCenterY());
        }

        if(getCenterX() > canvas.getWidth()){
            setCenter(getSpeed(), getCenterY());
        }
    }

    /*
     * Convienience methods for naming logic in the main class.
     */
    public void rotateLeft(){
        left();
    }
    
    public void rotateRight(){
        right();
    }     
}
