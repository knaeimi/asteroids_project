import org.junit.jupiter.api.Test;
import edu.macalester.graphics.CanvasWindow;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class GameRuleTest {
    private CanvasWindow canvas = new CanvasWindow("Test Window", 600, 600);
    private BulletProjectile bulletProjectile;
    private ProjectileManager projectileManager;
    private AsteroidManager asteroidManager = new AsteroidManager(canvas);
    private Asteroid asteroid;
    private PlayerShip playerShip = new PlayerShip(0, 0, canvas, projectileManager);
    private UI ui = new UI(canvas, playerShip);

    /*
     * Tests if the ui.removeLive() method functions as intended.
     */
    @Test
    void lifeCanBeRemoved() {
        ui.removeLife();
        assertEquals(2, ui.getLives());  
    }

    /*
     * Tests if ui.removeLife() can be used to remove multiple lives.
     */
    @Test
    void multipleLivesCanBeRemoved() {
        ui.removeLife();
        ui.removeLife();
        ui.removeLife();
        assertEquals(0, ui.getLives());  
    }

    /*
     * Tests that ui.addPoints() adds points to the player's score.
     */
    @Test
    void addPointsWorks() {
        int initPoints = ui.getScore();
        ui.addPoints();
        assertTrue(ui.getScore() > initPoints);
    }

    /*
     * Ensures that ui.addPoints() adds twenty points to the player's score.
     */
    @Test
    void addPointsAddsTwenty() {
        ui.addPoints();
        assertEquals(20, ui.getScore());
    }

    /*
     * Tests that the program ends once the player loses three lives.
     */
    @Test
    void loseAllLivesEndsGame() {
        ui.removeLife();
        ui.removeLife();
        ui.removeLife();
        assertThrows(NoSuchElementException.class, () -> {
            ui.removeLife();
        });
    }

    /*
     * Tests that asteroid.updatePosition() actually changes the asteroid's position.
     */
    @Test
    void asteroidUpdatePositionWorks() {
        asteroid = new Asteroid(100, 100, 45, 5, canvas);
        asteroid.updatePosition();
        assertNotEquals(100, asteroid.getCenterX());
        assertNotEquals(100, asteroid.getCenterY());
    }

    /*
     * Tests that Asteroid objects can "split" using asteroidManager.split()
     */
    @Test
    void asteroidCanSplit() {
        asteroidManager.generateAsteroids();
        int initialSize = asteroidManager.getAsteroidList().size();
        asteroidManager.split(asteroidManager.getAsteroidList().get(0));
        assertNotEquals(initialSize, asteroidManager.getAsteroidList().size());
    }

    /*
     * Tests that bulletProjectile.updatePosition() actually changes the bullet's position.
     */
    @Test
    void bulletProjectileUpdatePositionWorks() {
        bulletProjectile = new BulletProjectile(100, 100, 45, canvas);
        bulletProjectile.updatePosition();
        assertNotEquals(100, bulletProjectile.getCenterX());
        assertNotEquals(100, bulletProjectile.getCenterY());
    }
}
