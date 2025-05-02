import java.awt.Color;
import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.Button;

/*
 * Our main class. This is where we animate objects and also create the start button.
 */
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
        projectileManager = new ProjectileManager(canvas);
        asteroidManager = new AsteroidManager(canvas);
        playerShip = new PlayerShip(canvas.getWidth()/2, canvas.getHeight()/2, canvas, projectileManager); 
        ui = new UI(canvas, playerShip);
        keyHandler = new KeyHandler();
        collisionManager = new CollisionManager(asteroidManager, projectileManager, playerShip, ui, canvas);
        createStartButton();
    }

    /*
     * Our animation loop. We keep track of key presses and object state here, 
     * reflected in real time.
     * 
     */
    private void animateObjects(){ 
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

    /*
     * A simple method to make a start button so as to ease the user in. Once we the start button is
     * clicked, we begin the animation loop, add the ship to the canvas, and remove both the button 
     * and the big asteroids text.
     */
    public void createStartButton(){
        Button startButton = new Button("S T A R T  G A M E");
        canvas.add(startButton,canvas.getWidth()/2.1,canvas.getHeight()/1.7);
        startButton.onClick(() -> {
            animateObjects();
            playerShip.addToCanvas(canvas);
            ui.removeStartText();
            canvas.remove(startButton);    
        });
    }

    public static void main(String[] args) {
        new AsteroidsGame();
    }
}
