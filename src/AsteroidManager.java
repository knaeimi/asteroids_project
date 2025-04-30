import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import edu.macalester.graphics.*;

/*
 * This class handles asteroid instantiation/creation, updating all asteroids, and also provides a 
 * remove asteroid method for collision manager to use.
 */
public class AsteroidManager {
    private final long SPAWN_DELAY = 1000;
    private int side;
    private long time = System.currentTimeMillis();
    private CanvasWindow canvas;
    private ArrayList<Asteroid> asteroidList = new ArrayList<>();
    Random random = new Random();
    Asteroid asteroid;
    
    public AsteroidManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public List<Asteroid> getAsteroidList() {
        return new ArrayList<>(asteroidList);
    }

    /*
     * We randomly choose from the 4 sides (top, bottom, left, and right respectively), and add an asteroid
     * at that position (which is also random, along with a random angle... had to fiddle around to find 
     * nice looking angles).
     */
    public void generateAsteroids(){    
        side = random.nextInt(1,5);
            if(side == 1){ //top side
                asteroid = (new Asteroid((random.nextDouble(0,canvas.getWidth())), -30, random.nextDouble(-97,-95), random.nextDouble(50,80), canvas));
            }

            else if (side == 2){ //bottom side
                asteroid = (new Asteroid((random.nextDouble(0,canvas.getWidth())), canvas.getHeight() + 30, random.nextDouble(-12,-10), random.nextDouble(50,80), canvas));
            }

            else if(side == 3){//left side
                asteroid = (new Asteroid(-30, (random.nextDouble(0,canvas.getHeight())), random.nextDouble(24,26), random.nextDouble(50,80), canvas));
            }

            else if (side == 4){//right side
                asteroid = (new Asteroid(canvas.getWidth() + 30, (random.nextDouble(0,canvas.getHeight())),(random.nextDouble(-42,-40)), random.nextDouble(50,80), canvas));
            }
            asteroid.addToCanvas();
            asteroidList.add(asteroid);
        }

        /*
         * This method just handles generating asteroids at a given amount, along with maintaining a delay
         * so as to not overwhelm the user.
         */
        public void populateAsteroids(){
            long currentTime = System.currentTimeMillis();
            if(currentTime - time > SPAWN_DELAY){
                if(getAsteroidList().size() < 5){ //Where we choose the amount of asteroids for a given wave.
                    generateAsteroids();
                    
                    if(getAsteroidList().isEmpty()){ 
                        generateAsteroids(); // if player has shot (and retroactively removed from the list) every asteroid, then respawn them.
                    }
                }
                time = currentTime;  
            }
        }
    
        /*
         * Where updating/wrap arounds happen.
         */
    public void updateAsteroids(){
        List<Asteroid> updateList = new ArrayList<>(asteroidList);
        for (int i = 0; i < updateList.size(); i++){ 
            updateList.get(i).updatePosition();
            updateList.get(i).wrapAround();
        }
    }

    /*
     * For asteroid removal logic in CollisionManager.
     */
    public void removeAsteroid(Asteroid asteroid){
        asteroid.removeFromCanvas();
        asteroidList.remove(asteroid);
    }
}
