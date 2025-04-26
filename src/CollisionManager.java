import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class CollisionManager {

    private CanvasWindow canvas;
    private MeteorManager meteorManager;

    public CollisionManager(CanvasWindow canvas, MeteorManager meteorManager) {
        this.canvas = canvas;
        this.meteorManager = meteorManager;
    }

    public void projectileCollision(Projectile projectile) {
        GraphicsObject centerObj = canvas.getElementAt(projectile.getCenterX(), projectile.getCenterY());
        if(centerObj != null){
            for(Meteor m : meteorManager.getMeteorList()){
                GraphicsObject mShape = m.getShape();
                if (mShape == centerObj) {
                    m.removeFromCanvas(canvas);
                    // Modify score
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
     * New shipCollision method. Uses the Meteor method isCollidingWith to check if any meteor is colliding with the PlayerShip.
     */
    public void shipCollision(PlayerShip playerShip, MeteorManager meteorManager) {
        synchronized (meteorManager) {
            for (Meteor meteor : meteorManager.getMeteorList()) {
                if (meteor.isCollidingWith(playerShip)) {
                    System.out.println("Collision detected!");
                    playerShip.setCenter(300, 50);
                }
            }
        }
    }
}
