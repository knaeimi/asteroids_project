
import java.util.List;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;


public class CollisionManager { 

    private CanvasWindow canvas;
    private AsteroidManager asteroidManager;
    private ProjectileManager projectileManager;

    public CollisionManager(AsteroidManager asteroidManager, ProjectileManager projectileManager, CanvasWindow canvas) {
        this.canvas = canvas;
        this.asteroidManager = asteroidManager;
        this.projectileManager = projectileManager;
    }

    public void checkBulletCollisions(){
        //using this format of removal lists so we dont get annoying concurrent modification errors
        List <Asteroid> asteroidsToRemove = new ArrayList<>(); 
        List<Projectile> projectilesToRemove = new ArrayList<>();

        for(Projectile projectile : projectileManager.getProjectileList()){
            for(Asteroid asteroid: asteroidManager.getAsteroidList()){
                if(projectile.intersects(asteroid)){
                    asteroidsToRemove.add(asteroid);
                    projectilesToRemove.add(projectile);
                    break; //breaking so a projectile and asteroid are only added once to the list
                }
            }
        }

        for(Projectile projectile : projectilesToRemove){
            projectileManager.removeProjectile(projectile);
        }

        for(Asteroid asteroid: asteroidsToRemove){
            asteroidManager.removeAsteroid(asteroid);
        }
        
    }
}
