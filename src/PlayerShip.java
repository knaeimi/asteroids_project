import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

public class PlayerShip extends RocketShip{
    private CanvasWindow canvas;

    public PlayerShip(double x0, double y0, CanvasWindow canvas){
        super(x0,y0);
        this.canvas = canvas;
    }
    
    public void move(KeyboardEvent event){
        if (event.getKey() == Key.UP_ARROW){ //bound
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
        
        if(event.getKey() == Key.LEFT_ARROW){ 
            left();
           
        }

        if(event.getKey() == Key.RIGHT_ARROW){
            right();
        }
    }
}
