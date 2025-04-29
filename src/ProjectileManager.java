import java.util.ArrayList;
import java.util.Iterator;

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
        Iterator<Projectile> iterator = projList.listIterator();
        
        while(iterator.hasNext()){ 
            if(!iterator.next().updatePosition()){
                iterator.remove();
            }
        }
    }

    public ArrayList<Projectile> getProjList() {
        return projList;
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
    
    /*
     * Same as above- the implementation of beam is just different. Polymorphism!
     */
    public void addBeamProjectile(double x, double y, double angle){
        BeamProjectile beamProjectile = new BeamProjectile(x, y, angle, canvas);
        projList.add(beamProjectile);
    }
}
