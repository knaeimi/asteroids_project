import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.events.KeyboardEvent;

/*
 * This class handles taking in inputs for keys. We use booleans to represent whether a key is currently pressed or not.
 * Thank you StackOverflow for the concept. We have methods for each key
 * to change state on keyPress/keyRelease.
 */
public class KeyHandler{
    private boolean upPressed, leftPressed, rightPressed, spacePressed, fPressed;
    private Key pressedKey, releasedKey;
    private PlayerShip playerShip;

    public KeyHandler(PlayerShip playerShip){
        this.playerShip = playerShip;
    }

    /*
     * Called in the main class's animation loop to constantly check for user input.
     */
    public void checkKeyPresses(){
        if(upPressed){
            playerShip.forward();
        }

        if (!upPressed){
            playerShip.deaccelerate();
        }

        if(leftPressed){
            playerShip.rotateLeft();
        }
        if(rightPressed){
            playerShip.rotateRight();
        }
       
        if(spacePressed){ 
            playerShip.fireBulletProjectile();
        }

        if(fPressed){ 
            playerShip.fireBeamProjectile();
        }

    }

    /*
     * We change the state of each key to true on keyPress.
     */
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
        }
    }

    /*
     * We similarly change the state of each key, this time to false when a key is released.
     */
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
