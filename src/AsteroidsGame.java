import java.awt.Color;
import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;

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

    public void animateBullet() { // Temporary method to test bullet animation
        canvas.onKeyDown(event -> {
            if (event.getKey().equals(Key.SPACE)) {  
                Projectile bullet = new BulletProjectile(
                    playerShip.getCenterX(),
                    playerShip.getCenterY(),
                    90
                );
                bullet.addToCanvas(canvas);
                while (bullet.updatePosition()){
                    bullet.updatePosition();
                    canvas.draw();
                }
                bullet.removeFromCanvas(canvas);
            }
        });
    }

    public static void main(String[] args) {
        AsteroidsGame game = new AsteroidsGame();
        game.run(); // Start game
    }
}
