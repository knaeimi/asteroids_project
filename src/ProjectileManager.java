import java.util.ArrayList;
import edu.macalester.graphics.CanvasWindow;

/*
 * This class handles (currently only the BulletProjectile) the management of bullets on the screen. 
 */
public class ProjectileManager {
    private ArrayList<Projectile> projList = new ArrayList<Projectile>();
    private CanvasWindow canvas;
    
    public ProjectileManager(CanvasWindow canvas){
        this.canvas = canvas;
    }

    /*
     * This method handles the removal of projectiles from the list of projectiles on the screen if update returns false (meaning a bullet is out of
     * bounds)
     */
    public void updateProjectiles(){
        for (Projectile projectile : projList){
            if(!projectile.updatePosition()){
                projList.remove(projectile);
            }
        }
    }

    /*
     * Not sure if we'll need this method but here just in case.
     */
    public void removeAllProjectiles(){
        for (Projectile projectile : projList){
            projectile.removeFromCanvas();
        }
    }

    /*
     * This method is called in the main class, although we just use the ship's x/y positions and angle when calling it. Creates a bullet object
     * and adds it to the list to keep track of all bullets on screen.
     */
    public void addBulletProjectile(double x, double y, double angle){
        BulletProjectile bulletProjectile = new BulletProjectile(x, y, angle, canvas);
        projList.add(bulletProjectile);
    }
}
