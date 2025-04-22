
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

public class KeyHandler{
    private boolean upPressed, leftPressed, rightPressed;
    private Key pressedKey, releasedKey;
    
    public boolean upKey(){
        return upPressed;
    }

    public boolean leftKey(){
        return leftPressed;
    }

    public boolean rightKey(){
        return rightPressed;
    }

    public void keyPressed(KeyboardEvent event) {
        pressedKey = event.getKey();
        if (pressedKey == Key.UP_ARROW){
            upPressed = true;
        }
        if (pressedKey == Key.LEFT_ARROW){
            leftPressed = true;
        }
        if (pressedKey == Key.RIGHT_ARROW){
            rightPressed = true;
        }
    }

    public void keyReleased(KeyboardEvent event) {
        releasedKey = event.getKey();
        if (releasedKey == Key.UP_ARROW){
            upPressed = false;
        }
        if (releasedKey == Key.LEFT_ARROW){
            leftPressed = false;
        }
        if (releasedKey == Key.RIGHT_ARROW){
            rightPressed = false;
        }  
    }
}
