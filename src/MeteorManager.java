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

    public void generateMeteors(){ //a start on new implementation using all 4 sides w/ random angles
            
        caseNumber = rnd.nextInt(1,4);
                
            if(caseNumber == 1){ //top side
                meteorList.add(new Meteor((rnd.nextDouble(0,canvas.getWidth())), -30, rnd.nextDouble(-60,60), canvas));
            }

            if(caseNumber == 2){ //bottom side
                meteorList.add(new Meteor((rnd.nextDouble(0,canvas.getWidth())), canvas.getHeight() + 30, rnd.nextDouble(-60,60), canvas));
            }

            if(caseNumber == 3){
                meteorList.add(new Meteor(-30, (rnd.nextDouble(0,canvas.getHeight())), rnd.nextDouble(-60,60), canvas));
            }

            if(caseNumber == 4){
                meteorList.add(new Meteor(canvas.getWidth() + 30, (rnd.nextDouble(0,canvas.getHeight())), rnd.nextDouble(-60,60), canvas));
            }
        
            addMeteors();
        }

        public void populateMeteors(){
            long currentTime = System.currentTimeMillis();
            
            for(int i = 0; i < 50; i++){
            
                if(currentTime - time > SPAWN_DELAY){
                    if(getMeteorList().size() < 50){
                        generateMeteors();
                    }
                    time = currentTime;  
                }
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
