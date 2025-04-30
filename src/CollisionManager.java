
import java.util.List;
import java.util.ArrayList;



public class CollisionManager { 
    private AsteroidManager asteroidManager;
    private ProjectileManager projectileManager;
    private UI ui;

    public CollisionManager(AsteroidManager asteroidManager, ProjectileManager projectileManager, UI ui) {
        this.asteroidManager = asteroidManager;
        this.projectileManager = projectileManager;
        this.ui = ui;
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
                    ui.addPoints();
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
