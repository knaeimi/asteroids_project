import java.util.List;
import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;
import java.util.ArrayList;

/*
 * We check for ship/asteroid collisions in this class, and use the methods here in the main class's
 * animation loop.
 */
public class CollisionManager {
    private AsteroidManager asteroidManager;
    private ProjectileManager projectileManager;
    private PlayerShip playerShip;
    private UI ui;
    private CanvasWindow canvas;

    public CollisionManager(AsteroidManager asteroidManager, ProjectileManager projectileManager, PlayerShip playerShip,
        UI ui, CanvasWindow canvas) {
        this.asteroidManager = asteroidManager;
        this.projectileManager = projectileManager;
        this.playerShip = playerShip;
        this.ui = ui;
        this.canvas = canvas;
    }

    /*
     * We check for if the user has hit a asteroid with this method. First, we create two new removal
     * lists to add the asteroids/projectiles to for removal (after the inital loop has executed).
     * Before then, we check for intersections, and also add points to the score. We break so a given
     * projectile is only marked for removal once (otherwise... no such element errors). Finally, we use
     * two for-each loops to delete elements from the removal lists.
     * 
     */
    public void checkProjectileCollisions() {
        List<Asteroid> asteroidsToRemove = new ArrayList<>();
        List<Projectile> projectilesToRemove = new ArrayList<>();

        for (Projectile projectile : projectileManager.getProjectileList()) {
            for (Asteroid asteroid : asteroidManager.getAsteroidList()) {
                if (projectile.intersects(asteroid)) {
                    playerShip.setStroke(Color.MAGENTA);
                    asteroidsToRemove.add(asteroid);
                    asteroidManager.split(asteroid);
                    ui.addPoints();

                    if (!(projectile instanceof BeamProjectile)) {
                        playerShip.setStroke(new Color(150,0,255));
                        projectilesToRemove.add(projectile);
                        break; 
                    }
                }
            }
        }

        for (Projectile projectile : projectilesToRemove) {
            projectileManager.removeProjectile(projectile);
        }

        for (Asteroid asteroid : asteroidsToRemove) {
            asteroidManager.removeAsteroid(asteroid);
        }
    }

    /*
     * We check every asteroid in the (COPY for list safety) asteroid list for ship collisions. If a
     * collision is detected, we call the removeLife method from the UI class, remove the asteroid, and
     * call this blink method to tell the user very obviously that they've taken damage.
     */
    public void checkShipCollisions() {
        for (Asteroid asteroid : new ArrayList<>(asteroidManager.getAsteroidList())) {
            if (playerShip.intersectsAsteroid(asteroid)) {
                if(ui.removeLife()){
                    asteroidManager.removeAsteroid(asteroid);
                    playerShip.setCenter(canvas.getWidth()/2,canvas.getHeight()/2);
                    blink(); 
                }
                else{
                    ui.gameOver();
                }
            }
        }
    }

    /*
     * Utility method for a clean call in the main class.
     */
    public void checkAllCollisions() {
        checkProjectileCollisions();
        checkShipCollisions();
    }

    /*
     * Neat way to indicate to the player that they've lost a life.
     */
    public void blink() {
        for (int i = 0; i < 5; i++) {
            playerShip.setStroke(Color.BLACK);
            canvas.draw();
            canvas.pause(500);
            playerShip.setStroke(Color.WHITE);
            canvas.draw();
            canvas.pause(300);
        }
    }
}
