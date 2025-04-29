import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class CollisionManager { //TODO: Refactor collisions system

    private CanvasWindow canvas;

    public CollisionManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    /**
     * TODO: Refactor after correct meteor spawning/respawning behavior is achieved
     */
    public void projectileCollision(ProjectileManager projectileManager, MeteorManager meteorManager) {

        for (Meteor m : meteorManager.getMeteorList()) {
            if (m.isCollidingWithProjectile(projectileManager)) {
                    System.out.println("Projectile collision detected!");
            }
        }
    }

    /**
     * Uses the Meteor method isCollidingWithShip to check if any meteor is colliding with the PlayerShip.
     * //TODO: Refactor this too
     */
    public void shipCollision(PlayerShip playerShip, MeteorManager meteorManager, UI ui) {
        
        for (Meteor meteor : meteorManager.getMeteorList()) {
            if (meteor.isCollidingWithShip(playerShip)) {
                System.out.println("Ship collision detected!");
                playerShip.setCenter(300, 50);
                canvas.pause(3000); 
            }
        }
    }
}
