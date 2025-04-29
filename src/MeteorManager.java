import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import edu.macalester.graphics.*;

public class MeteorManager {

    private int caseNumber;
    private long time = System.currentTimeMillis();
    private final long SPAWN_DELAY = 1000;
    private CanvasWindow canvas;
    private ArrayList<Meteor> meteorList = new ArrayList<>();
    Random rnd = new Random();
    
    public MeteorManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public List<Meteor> getMeteorList() {
        return new ArrayList<Meteor>(meteorList);
    }

    /**
     *  This method uses a Timer that calls the TimerTask in the spawnMeteor() method, spawning Meteor objects within the game.
     */
    public void addMeteors() {
        List<Meteor> generateList = new ArrayList<>(meteorList);
        
        for (Meteor m : generateList) {
            m.addToCanvas(canvas);
        }
    }

    public void generateMeteors(){ //TODO: Kian, go to Bret's office hours for help with meteor spawning/collisions on Wednesday
            
        caseNumber = rnd.nextInt(1,5);
                
            if(caseNumber == 1){ //top side
                meteorList.add(new Meteor((rnd.nextDouble(0,canvas.getWidth())), -30, rnd.nextDouble(-97,-95), canvas));
            }

            if(caseNumber == 2){ //bottom side
                meteorList.add(new Meteor((rnd.nextDouble(0,canvas.getWidth())), canvas.getHeight() + 30, rnd.nextDouble(-12,-10), canvas));
            }

            if(caseNumber == 3){//left side
                meteorList.add(new Meteor(-30, (rnd.nextDouble(0,canvas.getHeight())), rnd.nextDouble(24,26), canvas));
            }

            if(caseNumber == 4){//right side
                meteorList.add(new Meteor(canvas.getWidth() + 30, (rnd.nextDouble(0,canvas.getHeight())),(rnd.nextDouble(-42,-40)), canvas));
                
            }
            addMeteors();
        }

        public void populateMeteors(){
            long currentTime = System.currentTimeMillis();
            
            if(currentTime - time > SPAWN_DELAY){
                if(getMeteorList().size() < 50){ //limits the amount we can spawn; 50 for now.
                    generateMeteors();
                    
                    if(getMeteorList().isEmpty()) { 
                    generateMeteors(); // if player shot (removed from the list) all meteors, then run it again. won't work for now.
                    }
                }
                time = currentTime;  
            }
        }
        
    

    public void updateMeteors(){
        List<Meteor> updateList = new ArrayList<>(meteorList);
        for (int i = 0; i < updateList.size(); i++){ 
            updateList.get(i).updatePosition();
        }
    }

    /**
     * This method removes all Meteor objects from meteorList and Canvas.
     */
    public void removeAllMeteors(){
        meteorList.clear();
        canvas.removeAll();
    }
}
