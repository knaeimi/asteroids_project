
import edu.macalester.graphics.CanvasWindow;

public class PlayerShip extends RocketShip{
    private CanvasWindow canvas;
    
    public PlayerShip(double x0, double y0, CanvasWindow canvas){
        super(x0,y0);
        this.canvas = canvas;
    }
    
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

    public void rotateLeft(){
        left();
    }
    
    public void rotateRight(){
        right();
    }
       
}
