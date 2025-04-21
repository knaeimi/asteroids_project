import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

public class PlayerShip extends RocketShip{
    private CanvasWindow canvas;
    private boolean up, left, right; 
    private List<Boolean> currentlyPressed = new ArrayList<>();

    public PlayerShip(double x0, double y0, CanvasWindow canvas){
        super(x0,y0);
        this.canvas = canvas;
        checkKeys();
    }
    
    public void forward(KeyboardEvent pressedKey){
        if (pressedKey.getKey() == Key.UP_ARROW){ //bound
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
    }

    public void rotateLeft(KeyboardEvent pressedKey){
        if(pressedKey.getKey() == Key.RIGHT_ARROW){
            right();
        }
    }

    public void rotateRight(KeyboardEvent pressedKey){
        if(pressedKey.getKey() == Key.LEFT_ARROW){
            left();
        }
    }
    
    public void checkKeys(){
        canvas.onKeyDown(upArrow -> this.forward(upArrow));
        canvas.onKeyUp(upArrow -> this.forward(upArrow));
        canvas.onKeyDown(leftArrow -> this.rotateLeft(leftArrow));
        canvas.onKeyDown(rightArrow -> this.rotateRight(rightArrow));
    }
}
