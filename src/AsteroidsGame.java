import java.awt.Color;
import edu.macalester.graphics.*;

public class AsteroidsGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private PlayerShip playerShip;
    private CanvasWindow canvas;
    private KeyHandler keyHandler;
    private ProjectileManager projectileManager;
    private MeteorManager meteorManager;
    private CollisionManager collisionManager;

    public AsteroidsGame(){
        canvas = new CanvasWindow("Asteroids", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK); 
        keyHandler = new KeyHandler();
        projectileManager = new ProjectileManager(canvas);
        meteorManager = new MeteorManager(canvas);
        collisionManager = new CollisionManager(canvas);
        playerShip = new PlayerShip(canvas.getWidth()/2, canvas.getHeight()/2, canvas, projectileManager); 
    }
    
    public void run() {
        canvas.removeAll();
        playerShip.addToCanvas(canvas); 
        meteorManager.generateMeteors();
        UI ui = new UI(canvas);
        animate();
    }

    public void animate(){
        canvas.onKeyDown(keyHandler::keyPressed);
        canvas.onKeyUp(keyHandler::keyReleased);
        canvas.animate(event ->{
            projectileManager.updateProjectiles();
            meteorManager.updateMeteors();
            collisionManager.shipCollision(playerShip, meteorManager);
            //collisionManager.projectileCollision(projectileManager, meteorManager);
            //TODO: Fix issues with projectileCollision

            if(keyHandler.upKey()){
                playerShip.forward();
            }

            if (!keyHandler.upKey()){
                playerShip.deaccelerate();
            }

            if(keyHandler.leftKey()){
                playerShip.rotateLeft();
            }
            if(keyHandler.rightKey()){
                playerShip.rotateRight();
            }
           
            if(keyHandler.spaceKey()){ 
                playerShip.fireBulletProjectile();
            }

            if(keyHandler.fKey()){ 
                playerShip.fireBeamProjectile();
            }
        });
    }

    public static void main(String[] args) {
        AsteroidsGame game = new AsteroidsGame();
        game.run();
    }
}
