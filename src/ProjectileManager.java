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

    public void removeAllProjectiles(){
        for (Projectile projectile : projList){
            projectile.removeFromCanvas();
        }
    }

    public void addBulletProjectile(double x, double y, double angle){
        BulletProjectile bulletProjectile = new BulletProjectile(x, y, angle, canvas);
        projList.add(bulletProjectile);
    }
}
