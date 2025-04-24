import java.awt.Color;
import edu.macalester.graphics.*;

public class AsteroidsGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private final long milisecBetweenShots = 500; // 500ms between shots
    private PlayerShip playerShip;
    private CanvasWindow canvas;
    private KeyHandler keyHandler;
    private ProjectileManager projectileManager;
    private MeteorManager meteorManager;
    private CollisionManager collisionManager;
    private long lastShotTime = System.currentTimeMillis(); 

    public AsteroidsGame(){
        canvas = new CanvasWindow("Asteroids", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK); 
        keyHandler = new KeyHandler();
        projectileManager = new ProjectileManager(canvas);
        meteorManager = new MeteorManager(canvas);
        collisionManager = new CollisionManager(canvas, meteorManager);
        animate();
    }

    public void run() {
        canvas.removeAll();
        playerShip = new PlayerShip(300, 300, canvas); 
        playerShip.addToCanvas(canvas);
        meteorManager.generateMeteors();
    }

    public void animate(){
        canvas.onKeyDown(keyHandler::keyPressed);
        canvas.onKeyUp(keyHandler::keyReleased);
        canvas.animate(event ->{
            projectileManager.updateProjectiles(); //moves bullets
            meteorManager.updateMeteors(); //moves meteors
            collisionManager.shipCollision(playerShip);
            if(keyHandler.upKey()){
                playerShip.forward();
            }
            if(keyHandler.leftKey()){
                playerShip.rotateLeft();
            }
            if(keyHandler.rightKey()){
                playerShip.rotateRight();
            }
           
            if(keyHandler.spaceKey()){ //handles bullet spacing 
                 long currentTime = System.currentTimeMillis();
                 if (currentTime - lastShotTime > milisecBetweenShots){
                    projectileManager.addBulletProjectile(playerShip.getCenterX(), playerShip.getCenterY() - playerShip.getSideLength()/2, 
                    playerShip.getRotationAngle());
                    lastShotTime = currentTime;
                }
            }
        });
    }

    public static void main(String[] args) {
        AsteroidsGame game = new AsteroidsGame();
        game.run(); 
    }
}
