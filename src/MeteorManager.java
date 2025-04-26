import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.*;

public class MeteorManager {

    private CanvasWindow canvas;
    private ArrayList<Meteor> meteorList = new ArrayList<>();
    Timer t = new Timer();
    Random rnd = new Random();

    public MeteorManager(CanvasWindow canvas) {
        this.canvas = canvas;
        for(int i = 1; i < 100; i ++){
            meteorList.add(new Meteor(-300 * i, rnd.nextDouble(-200, 150),0 , canvas));
        }
        
        // System.out.println(meteorList);
    }

    public List<Meteor> getMeteorList() {
        return new ArrayList<Meteor>(meteorList);
    }

    /**
     *  This method uses a Timer that calls the TimerTask in the spawnMeteor() method, spawning Meteor objects within the game.
     */
    public void generateMeteors() {
        List<Meteor> generateList = new ArrayList<>(meteorList);
        // for(int i = 1; i < generateList.size()+1; i++) {
        //     // System.out.println(i);
        //     t.schedule(spawnMeteor(i-1), i*1000);
        // }
        for (Meteor m : generateList) {
            m.addToCanvas(canvas);
        }
    }

    /**
     * TODO: REWRITE JAVADOC for spawnMeteor.md
     */
    public TimerTask spawnMeteor(int n) { 
        List<Meteor> spawnList = new ArrayList<>(meteorList);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // System.out.println(spawnList);
                // System.out.println(n);
                Meteor m = spawnList.get(n);
                m.addToCanvas(canvas);    
            }
        };
        return task;
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
