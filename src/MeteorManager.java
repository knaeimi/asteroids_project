import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Math;

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

    public void generateMeteors() { // Generate meteors for canvas
        for(int i = 1; i < 16; i ++) {
            new Timer().schedule(spawnMeteor(), i*3000);
        }
    }

    public TimerTask spawnMeteor() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Random rnd = new Random(); // Determines what side of screen meteor will spawn on
                if(rnd.nextBoolean()){
                    Meteor meteor = new Meteor(0, 250, 50,0);
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

    public void removeAllMeteors(){ // Removes all meteors from list and canvas
        meteorList.clear();
        canvas.removeAll();
    }

    public static void main(String[] args) { //TODO: Delete test window
        CanvasWindow canvas = new CanvasWindow("Test", 500, 500);
        MeteorManager mm = new MeteorManager(canvas);
        mm.generateMeteors();
    }
}
