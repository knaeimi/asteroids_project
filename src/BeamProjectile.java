import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class BeamProjectile implements Projectile {
   
    private double beamWidth, beamLength;
    private CanvasWindow canvas;
    private Rectangle beamShape;
    private double initialX, initialY, angle;
    
    public BeamProjectile(double initialX, double initialY, double angle, CanvasWindow canvas){
        this.canvas = canvas;
        this.initialX = initialX;
        this.initialY = initialY;
        this.angle = angle;
        
        beamLength = canvas.getHeight();
        beamWidth = 10;
        createBeam();
    }

    public double getBeamLength(){
        return beamLength;
    }

    public boolean updatePosition(){ //for now until we figure out how to refactor.. only bullet needs update so how do we do this
        return true;
    }

    public void addToCanvas(){
        canvas.add(beamShape);
    }

    public void removeFromCanvas(){
        canvas.remove(beamShape);
    }

    public double getCenterX(){
        return beamShape.getCenter().getX();
    }

    public double getCenterY(){
        return beamShape.getCenter().getY();
    }

    public void createBeam(){
        double topLeftX = initialX - beamWidth / 2;
        double topLeftY = initialY - beamLength;
        
        beamShape = new Rectangle(topLeftX, topLeftY, beamWidth, beamLength);
        beamShape.setAnchor(beamWidth / 2, beamLength);
        beamShape.setRotation(-Math.toDegrees(angle)+90);
        beamShape.setFillColor(Color.MAGENTA);
        addToCanvas();
        removeBeam();
    }

    public void removeBeam(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeFromCanvas();
                timer.cancel();
            }
        }, 100);
    }
}

