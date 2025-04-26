import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.*;

public class MeteorManager {

    private CanvasWindow canvas;
    private List<Meteor> meteorList = new ArrayList<>();
    private int itrCount = 0;

    Timer t = new Timer();
    Random rnd = new Random();

    public MeteorManager(CanvasWindow canvas) {
        this.canvas = canvas;
        for(int i = 0; i < 20; i ++){
            meteorList.add(new Meteor(0, rnd.nextDouble(-150, 150), 0, canvas));
        }
        
        System.out.println(meteorList);
    }

    public List<Meteor> getMeteorList() {
        return meteorList;
    }

    /**
     *  This method uses a Timer that calls the TimerTask in the spawnMeteor() method, spawning Meteor objects within the game.
     */
    public void generateMeteors() {
        List<Meteor> nList = new ArrayList<>(meteorList);
        for(int i = 1; i < nList.size()+1; i++) {
            System.out.println(i);
            t.schedule(spawnMeteor(i-1), i*1000);
            itrCount = i;
        }
    }

    /**
     * TODO: REWRITE JAVADOC for spawnMeteor.md
     */
    public TimerTask spawnMeteor(int n) { //TODO: Debug conflicting updateMeteors/spawnMeteor list modification logic
        List<Meteor> sList = new ArrayList<>(meteorList);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(sList);
                System.out.println(n);
                Meteor m = sList.get(n);
                m.addToCanvas(canvas);
            }
        };
        return task;
    }

    public void updateMeteors(){
        List<Meteor> updateList = new ArrayList<>(meteorList);
        for (int i = 0; i < meteorList.size(); i++){ //TODO: Iterating through a constantly modified list... how else can we do this?
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
