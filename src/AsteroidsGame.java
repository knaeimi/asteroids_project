import java.awt.Color;
import edu.macalester.graphics.*;

public class AsteroidsGame {
    private static final int CANVAS_WIDTH = 1920;
    private static final int CANVAS_HEIGHT = 1080;
    private PlayerShip playerShip;
    private CanvasWindow canvas;
    private KeyHandler keyHandler;
    private ProjectileManager projectileManager;
    private AsteroidManager asteroidManager;
    private CollisionManager collisionManager;
    private UI ui;

    public AsteroidsGame(){
        canvas = new CanvasWindow("Asteroids", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK); 
        keyHandler = new KeyHandler();
        projectileManager = new ProjectileManager(canvas);
        asteroidManager = new AsteroidManager(canvas);
        playerShip = new PlayerShip(canvas.getWidth()/2, canvas.getHeight()/2, canvas, projectileManager); 
        ui = new UI(canvas, playerShip);
        collisionManager = new CollisionManager(asteroidManager, projectileManager, playerShip, ui, canvas);
        animateObjects();
    }

    public void animateObjects(){ 
        canvas.onKeyDown(keyHandler::keyPressed);
        canvas.onKeyUp(keyHandler::keyReleased);
        canvas.animate(event ->{
            playerShip.updatePosition();
            projectileManager.updateProjectiles();
            asteroidManager.populateAsteroids();
            asteroidManager.updateAsteroids();
            collisionManager.checkAllCollisions();

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
        new AsteroidsGame();
    }
}
