import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;
import java.lang.Math;

public class PlayerShip extends RocketShip{
    
    private double speed = 10;
    private double angle, xVelocity, yVelocity; // May make sense to use a velocity-based system instead
    private CanvasWindow canvas;

    public PlayerShip(double x0, double y0, CanvasWindow canvas){
        super(x0,y0);
        this.canvas = canvas;
    }
    
    public void move(KeyboardEvent event){
        if (event.getKey() == Key.UP_ARROW && getCenterY() != -10){ //bound
            setCenter(getCenterX(), getCenterY() - speed);
            
            if(getCenterY() == -10){ //spawning @ bottom/top
                setCenter(getCenterX(), canvas.getHeight() + speed);
            }
        }
        
        if (event.getKey() == Key.DOWN_ARROW && getCenterY() != canvas.getHeight() + 10){ 
            setCenter(getCenterX(), getCenterY() + speed);

            if(getCenterY() == canvas.getHeight() + 10){
                setCenter(getCenterX(), 0 + speed);
            }
        }

        if(event.getKey() == Key.LEFT_ARROW){ //rotating left/right
            getShape().rotateBy(-speed);
        }

        if(event.getKey() == Key.RIGHT_ARROW){
            getShape().rotateBy(speed);
        }
    }
}
