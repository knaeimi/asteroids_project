import edu.macalester.graphics.CanvasWindow;

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
        playerShip.removeFromCanvas(canvas);
    }
}
