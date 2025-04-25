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
        collisionManager = new CollisionManager(canvas, meteorManager);
        animate();
    }
    
    public void run() {
        canvas.removeAll();
        playerShip = new PlayerShip(canvas.getWidth()/2, canvas.getHeight()/2, canvas, projectileManager); 
        playerShip.addToCanvas(canvas); 
        meteorManager.generateMeteors();
        UI ui = new UI(canvas);
    }

    public void animate(){
        canvas.onKeyDown(keyHandler::keyPressed);
        canvas.onKeyUp(keyHandler::keyReleased);
        canvas.animate(event ->{
            projectileManager.updateProjectiles(); //TODO: What else is causing these crashes? Both updateProjectiles and generateMeteors somehow crash independently?!
            meteorManager.updateMeteors();
            collisionManager.shipCollision(playerShip);
            for(Projectile p : projectileManager.getProjList()){
                collisionManager.projectileCollision(p);
            }
            
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
