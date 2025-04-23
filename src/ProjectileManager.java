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
     * This method is used in the main class to animate a bullet projectile using an x, y, and angle. We just take those values from the player
     * in the main class though. Also, we add the bullet to the list of projectiles for updating/removal from the screen.
     */
    public void addBulletProjectile(double x, double y, double angle){
        BulletProjectile bulletProjectile = new BulletProjectile(x, y, angle, canvas);
        projList.add(bulletProjectile);
        }
    }

