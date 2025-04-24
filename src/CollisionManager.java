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
        List<Meteor> removeList = new ArrayList<>();
        GraphicsObject centerObj = canvas.getElementAt(projectile.getCenterX(), projectile.getCenterY());
        if(centerObj != null){
            for(Meteor m : meteorManager.getMeteorList()){
                GraphicsObject mShape = m.getShape();
                if (mShape == centerObj) {
                    removeList.add(m);
                    canvas.remove(mShape);
                }
            } 
            meteorManager.getMeteorList().removeAll(removeList);
        }

        projectile.removeFromCanvas();
        //Modify score
    }

    public void shipCollision(PlayerShip playerShip) {
        GraphicsObject centerObj = canvas.getElementAt(playerShip.getCenterX(), playerShip.getCenterY());
        if(centerObj != null){
            for(Meteor m : meteorManager.getMeteorList()){
                GraphicsObject mShape = m.getShape();
                if (mShape == centerObj) {
                    playerShip.setCenter(300, 300);
                    canvas.pause(500);
                    // Modify livesCount
                }
            } 
        }  
    }
}
