import java.util.ArrayList;
import java.util.List;

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
        
    }

    public void shipCollision(PlayerShip playerShip) {
        List<Meteor> removeList = new ArrayList<>();
        GraphicsObject centerObj = canvas.getElementAt(playerShip.getCenterX(), playerShip.getCenterY());
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

        playerShip.removeFromCanvas(canvas);
        // Modify livesCount
    }
}
