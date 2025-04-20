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

    public void generateMeteors() { // Generate meteors for canvas
        for(int i = 1; i < 16; i ++) {
            new Timer().schedule(spawnMeteor(), i*3000);
        }
    }

    public TimerTask spawnMeteor() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Random rnd = new Random();
                if(rnd.nextBoolean()){
                    Meteor meteor = new Meteor(0, 250, 50);
                    meteorList.add(meteor);
                    meteor.addToCanvas(canvas);
                    System.out.println("Meteor");
                }
                else{
                    Meteor meteor = new Meteor(canvas.getWidth(), 250, 50);
                    meteorList.add(meteor);
                    meteor.addToCanvas(canvas);
                    System.out.println("Meteor");
                }
                
            }
        };
        return task;
    }

    public void removeAllMeteors(){ // Remove all meteors from list and canvas
        meteorList.clear();
        canvas.removeAll();
    }

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Test", 500, 500);
        MeteorManager mm = new MeteorManager(canvas);
        mm.generateMeteors();
    }
}
