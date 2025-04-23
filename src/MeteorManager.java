import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.*;

public class MeteorManager {

    private CanvasWindow canvas;
    private List<Meteor> meteorList;

    public MeteorManager(CanvasWindow canvas) {
        meteorList = new ArrayList<>();
        this.canvas = canvas;
    }

    public List<Meteor> getMeteorList() {
        return meteorList;
    }

    /**
     *  This method contains a Timer that calls the TimerTask in the spawnMeteor() method, spawning Meteor objects within the game.
     */
    public void generateMeteors() {
        for(int i = 1; i < 16; i ++) {
            new Timer().schedule(spawnMeteor(), i*3000);
        }
    }

    /**
     * This method defines the TimerTask that runs every interval through the Timer in the generateMeteors() method.
     * A Random condition determines what side of the screen the Meteor spawns on. The Meteor is then added
     * to meteorList and the Canvas.
     */
    public TimerTask spawnMeteor() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Random rnd = new Random();
                if(rnd.nextBoolean()){
                    Meteor meteor = new Meteor(0, 250, 50,0);
                    //TODO: define what direction the Meteor will move in
                    meteorList.add(meteor);
                    meteor.addToCanvas(canvas);
                }
                else{
                    Meteor meteor = new Meteor(canvas.getWidth(), 250, 50,0);
                    meteorList.add(meteor);
                    meteor.addToCanvas(canvas);
                } 
            }
        };
        return task;
    }

    /**
     * This method defines how a Meteor object should split once it is destroyed by a Projectile. 
     * We get the radius of the Meteor, then use that to determine the radii of the new Meteors created
     * as a result of the split. Those new meteors are then created, set at opposite angles, and added to the Canvas.
     */
    public void split(Meteor meteor) {
        double nRadius = 0;
        if(meteor.getRadius() == 50) {
        nRadius = 25;
        meteor.removeFromCanvas(canvas);
        }
        else if(meteor.getRadius() == 25) {
        nRadius = 10;
        meteor.removeFromCanvas(canvas);
        }
        Meteor m1 = new Meteor(meteor.getCenterX()-30, meteor.getCenterY()-10, nRadius, 45);
        meteorList.add(m1);
        m1.addToCanvas(canvas);
        Meteor m2 = new Meteor(meteor.getCenterX()+30, meteor.getCenterY()-10, nRadius, -45);
        meteorList.add(m2);
        m2.addToCanvas(canvas);
    }

    /**
     * This method removes all Meteor objects from meteorList and Canvas.
     */
    public void removeAllMeteors(){
        meteorList.clear();
        canvas.removeAll();
    }

    public static void main(String[] args) { //TODO: Delete test window
        CanvasWindow canvas = new CanvasWindow("Test", 500, 500);
        MeteorManager mm = new MeteorManager(canvas);
        mm.generateMeteors();
    }
}
