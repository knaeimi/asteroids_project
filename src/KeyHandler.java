
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

/*
 * This class handles taking in inputs for keys. We use booleans to represent whether a key is currently pressed or not. If you have an input
 * you need registered (e.g. projectiles with the spacebar): Add a boolean variable, a getter for that value, and add it to the keyPressed and keyReleased methods
 * using this same syntax to work with the methods of your class. You will also need to add a conditional for the method to run in the main class's
 * animate method.
 */
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
