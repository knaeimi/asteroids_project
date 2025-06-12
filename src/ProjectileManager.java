import java.util.ArrayList;
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
     * bounds), or in the case of the beam that enough time has ellapsed for removal. To avoid ConcurrentModificationExceptions, we 
     * simply loop like normal through projectileList. But instead of removing elements from a list we're
     * currently looping through... we just add the projectiles that are out of bounds to a list...
     * and then loop through the removalList instead. This way we can always continue iterating through
     * the list of projectiles (the elements in the list grow and aren't interrupted).
     */
    public void updateProjectiles(){
       
        ArrayList<Projectile> removalList = new ArrayList<Projectile>();
        
        for(Projectile projectile : projectileList){
            if (!projectile.updatePosition()){
                removalList.add(projectile);
            }
        }
                
        for(Projectile markedProjectile : removalList){
            removeProjectile(markedProjectile);
        }
    }

    /*
     * Used in CollisionManager for marking projectiles for removal after collisions.
     */
    public ArrayList<Projectile> getProjectileList() {
        return new ArrayList<>(projectileList);
    }

    /*
     * Used in CollisionManager for projectile removal.
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
