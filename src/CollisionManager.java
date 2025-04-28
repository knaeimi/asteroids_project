import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class CollisionManager {

    private CanvasWindow canvas;

    public CollisionManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    /**
     * TODO: Implement projectileCollision
     */
    public void projectileCollision(ProjectileManager projectileManager, MeteorManager meteorManager) {
        synchronized (projectileManager) {
            for (Meteor m : meteorManager.getMeteorList()) {
                if (m.isCollidingWithProjectile(projectileManager)) {
                    System.out.println("Projectile collision detected!");
                }
            }
        }
    }


    /**
     * Uses the Meteor method isCollidingWithShip to check if any meteor is colliding with the PlayerShip.
     */
    public void shipCollision(PlayerShip playerShip, MeteorManager meteorManager, UI ui) {
        synchronized (meteorManager) {
            for (Meteor meteor : meteorManager.getMeteorList()) {
                if (meteor.isCollidingWithShip(playerShip)) {
                    System.out.println("Ship collision detected!");
                    playerShip.setCenter(300, 50);
                    canvas.pause(3000);
                }
            }
        }
    }
}
