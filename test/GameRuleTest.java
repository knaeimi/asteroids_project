import org.junit.jupiter.api.Test;

import edu.macalester.graphics.CanvasWindow;

import static org.junit.jupiter.api.Assertions.*;


import java.util.NoSuchElementException;

public class GameRuleTest {
    private CanvasWindow canvas = new CanvasWindow("Test Window", 600, 600);
    private BulletProjectile bulletProjectile;
    private ProjectileManager projectileManager;
    private Asteroid asteroid;
    private PlayerShip playerShip = new PlayerShip(0, 0, canvas, projectileManager);
    private UI ui = new UI(canvas, playerShip);

    /*
     * Tests if the ui.removeLive() method functions as intended.
     */
    @Test
    void lifeCanBeRemoved() {
        ui.removeLife();
        assertEquals(3, ui.getLives());  
    }

    /*
     * Tests if ui.removeLine() can be used to remove multiple lives.
     */
    @Test
    void multipleLivesCanBeRemoved() {
        ui.removeLife();
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
     * Tests that the program ends once the player loses four lives.
     */
    @Test
    void loseAllLivesEndsGame() {
        ui.removeLife();
        ui.removeLife();
        ui.removeLife();
        ui.removeLife();
        assertThrows(NoSuchElementException.class, () -> {
            ui.removeLife();
        });
    }

}
