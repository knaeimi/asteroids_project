import java.awt.Color;
import edu.macalester.graphics.*;

public class AsteroidsGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private PlayerShip playerShip;
    private CanvasWindow canvas;
    private KeyHandler keyHandler;
    private ProjectileManager projectileManager;

    public AsteroidsGame(){
        canvas = new CanvasWindow("Asteroids", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK); 
        keyHandler = new KeyHandler();
        projectileManager = new ProjectileManager(canvas);
        animate();
    }

    public void run() {
        canvas.removeAll();
        playerShip = new PlayerShip(300, 300, canvas); 
        playerShip.addToCanvas(canvas); 
    }

    public void animate(){
        canvas.onKeyDown(keyHandler::keyPressed);
        canvas.onKeyUp(keyHandler::keyReleased);
        canvas.animate(event ->{
            projectileManager.updateProjectiles(); //moves bullets
            if(keyHandler.upKey()){
                playerShip.forward();
            }
            if(keyHandler.leftKey()){
                playerShip.rotateLeft();
            }
            if(keyHandler.rightKey()){
                playerShip.rotateRight();
            }
            if(keyHandler.spaceKey()){ 
                projectileManager.addBulletProjectile(playerShip.getCenterX(), playerShip.getCenterY() - playerShip.getSideLength()/2, 
                playerShip.getRotationAngle());
            }
        });
    }

    public static void main(String[] args) {
        AsteroidsGame game = new AsteroidsGame();
        game.run(); 
    }
}
