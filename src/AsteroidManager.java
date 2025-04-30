import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import edu.macalester.graphics.*;

public class AsteroidManager {

    private int caseNumber;
    private long time = System.currentTimeMillis();
    private final long SPAWN_DELAY = 1000;
    private CanvasWindow canvas;
    private ArrayList<Asteroid> asteroidList = new ArrayList<>();
    Random rnd = new Random();
    
    public AsteroidManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public List<Asteroid> getAsteroidList() {
        return new ArrayList<Asteroid>(asteroidList);
    }

    /**
     *  This method uses a Timer that calls the TimerTask in the spawnMeteor() method, spawning Meteor objects within the game.
     */
    public void addAsteroids() {
        List<Asteroid> generateList = new ArrayList<>(asteroidList);
        
        for (Asteroid m : generateList) {
            m.addToCanvas(canvas);
        }
    }

    public void generateAsteroids(){ //TODO: Kian, go to Bret's office hours for help with meteor spawning/collisions on Wednesday
            
        caseNumber = rnd.nextInt(1,5);
                
            if(caseNumber == 1){ //top side
                asteroidList.add(new Asteroid((rnd.nextDouble(0,canvas.getWidth())), -30, rnd.nextDouble(-97,-95), canvas));
            }

            if(caseNumber == 2){ //bottom side
                asteroidList.add(new Asteroid((rnd.nextDouble(0,canvas.getWidth())), canvas.getHeight() + 30, rnd.nextDouble(-12,-10), canvas));
            }

            if(caseNumber == 3){//left side
                asteroidList.add(new Asteroid(-30, (rnd.nextDouble(0,canvas.getHeight())), rnd.nextDouble(24,26), canvas));
            }

            if(caseNumber == 4){//right side
                asteroidList.add(new Asteroid(canvas.getWidth() + 30, (rnd.nextDouble(0,canvas.getHeight())),(rnd.nextDouble(-42,-40)), canvas));
                
            }
            addAsteroids();
        }

        public void populateAsteroids(){
            long currentTime = System.currentTimeMillis();
            
            if(currentTime - time > SPAWN_DELAY){
                if(getAsteroidList().size() < 50){ //limits the amount we can spawn; 50 for now.
                    generateAsteroids();
                    
                    if(getAsteroidList().isEmpty()) { 
                    generateAsteroids(); // if player shot (removed from the list) all meteors, then run it again. won't work for now.
                    }
                }
                time = currentTime;  
            }
        }
        
    

    public void updateMeteors(){
        List<Asteroid> updateList = new ArrayList<>(asteroidList);
        for (int i = 0; i < updateList.size(); i++){ 
            updateList.get(i).updatePosition();
        }
    }

    public void removeAllAsteroids(){
        asteroidList.clear();
        canvas.removeAll();
    }
}
