
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

/*
 * This class handles taking in inputs for keys. We use booleans to represent whether a key is currently pressed or not. 
 */
public class KeyHandler{
    private boolean upPressed, leftPressed, rightPressed, spacePressed, fPressed;
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

    public boolean spaceKey(){
        return spacePressed;
    }

    public boolean fKey(){
        return fPressed;
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
        if (pressedKey == Key.SPACE){
            spacePressed = true;
        }
        if (pressedKey == Key.F){
            fPressed = true;
            // upPressed = false; //Stopping player movement for game balancing
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
        if (releasedKey == Key.SPACE){
            spacePressed = false;
        }  
        if (releasedKey == Key.F){
            fPressed = false;
        }
    }
}
