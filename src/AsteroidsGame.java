import java.awt.Color;
import edu.macalester.graphics.*;

public class AsteroidsGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private PlayerShip playerShip;
    private CanvasWindow canvas;
    private KeyHandler keyHandler;

    public AsteroidsGame(){
        canvas = new CanvasWindow("Asteroids", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
        keyHandler = new KeyHandler();
        animate();
    }

    public void run() {
        canvas.removeAll(); // Reset game

        playerShip = new PlayerShip(300, 300, canvas); 
        playerShip.addToCanvas(canvas); 

        Meteor meteor = new Meteor(150, 150, 50); // temporary
        meteor.addToCanvas(canvas);
    }

    public void animate(){
        canvas.onKeyDown(keyHandler::keyPressed); //method reference, shorthand for longer lambda expression
        canvas.onKeyUp(keyHandler::keyReleased);
        canvas.animate(event ->{
            if(keyHandler.upKey()){
                playerShip.forward();
            }
            if(keyHandler.leftKey()){
                playerShip.rotateLeft();
            }
            if(keyHandler.rightKey()){
                playerShip.rotateRight();
            }
        });
    }

    public static void main(String[] args) {
        AsteroidsGame game = new AsteroidsGame();
        game.run(); // Start game
    }
}
