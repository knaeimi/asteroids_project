import java.util.ArrayList;
import java.util.Iterator;

import edu.macalester.graphics.CanvasWindow;

/*
 * This class handles the management of bullets and beams on the screen. 
 */
public class ProjectileManager {
    private ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
    private CanvasWindow canvas;
    
    public ProjectileManager(CanvasWindow canvas){
        this.canvas = canvas;
    }

    /*
     * This method handles the removal of projectiles from the list of projectiles on the screen if update returns false (meaning a bullet is out of
     * bounds), or in the case of the beam that enough time has ellapsed for removal. Thank you Marvin and
     * Lewis for teaching me how to use iterators (concurrent modifications were happening without them)
     */
    public void updateProjectiles(){
        Iterator<Projectile> iterator = projectileList.listIterator();
    
        while(iterator.hasNext()){
            Projectile currentProj = iterator.next();
            if(!currentProj.updatePosition()){
                canvas.remove(currentProj.getProjectileShape());
                iterator.remove();
            }
        }
    }

    public ArrayList<Projectile> getProjectileList() {
        return new ArrayList<>(projectileList);
    }

    /*
     * Used in CollisionManager.
     */
    public void removeProjectile(Projectile projectile){
        canvas.remove(projectile.getProjectileShape());
        projectileList.remove(projectile);
    }

    /*
     * This method is called in the main class, although we just use the ship's x/y positions and angle when calling it. Creates a bullet object
     * and adds it to the list to keep track of all bullets on screen.
     */
    public void addBulletProjectile(double x, double y, double angle){
        BulletProjectile bulletProjectile = new BulletProjectile(x, y, angle, canvas);
        projectileList.add(bulletProjectile);
    }
    
    /*
     * Same as above- the implementation of beam is just different.
     */
    public void addBeamProjectile(double x, double y, double angle){
        BeamProjectile beamProjectile = new BeamProjectile(x, y, angle, canvas);
        projectileList.add(beamProjectile);
    }
}
