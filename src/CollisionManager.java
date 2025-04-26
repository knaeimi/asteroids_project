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

    public void projectileCollision(ProjectileManager projectileManager, MeteorManager meteorManager) {
        synchronized (projectileManager) {
            for (Projectile projectile : projectileManager.getProjList()) {
                if (projectile.isCollidingWithMeteor(meteorManager)) {
                    System.out.println("Collision detected!");
                }
            }
        }
    }


    /*
     * OLD COLLISION LOGIC -- KEEPING FOR REFERENCE -Sam
     */
    // public void shipCollision(PlayerShip playerShip) {
    //     GraphicsObject centerObj = canvas.getElementAt(playerShip.getCenterX(), playerShip.getCenterY());
    //     if(centerObj != null){
    //         for(Meteor m : meteorManager.getMeteorList()){
    //             GraphicsObject mShape = m.getShape();
    //             if (mShape == centerObj) {
    //                 playerShip.setCenter(300, 300);
    //                 // Modify livesCount
    //             }
    //         } 
    //     }  
    // }

    /**
     * New shipCollision method. Uses the Meteor method isCollidingWithShip to check if any meteor is colliding with the PlayerShip.
     */
    public void shipCollision(PlayerShip playerShip, MeteorManager meteorManager) {
        synchronized (meteorManager) {
            for (Meteor meteor : meteorManager.getMeteorList()) {
                if (meteor.isCollidingWithShip(playerShip)) {
                    System.out.println("Collision detected!");
                    playerShip.setCenter(300, 50);
                }
            }
        }
    }
}
