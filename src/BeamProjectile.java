import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class BeamProjectile implements Projectile {
    private final double VELOCITY = 5;
    private double initialX;
    private double initialY;
    private double angle;
    private double beamWidth, beamLength;
    private CanvasWindow canvas;
    private Rectangle beamShape;
    private double backX;
    private double backY;
        
    public BeamProjectile(double initialX, double initialY, double angle, CanvasWindow canvas){
        this.canvas = canvas;
        this.initialX = initialX;
        this.initialY = initialY;
        this.angle = angle;
        backX = initialX - beamLength;
        backY = initialY - beamLength;
        
        beamLength = canvas.getHeight()/2;
        beamWidth = 10;
        double topLeftX = initialX - beamWidth / 2;
        double topLeftY = initialY - beamLength;
        
        beamShape = new Rectangle(topLeftX, topLeftY, beamWidth, beamLength);
        beamShape.setAnchor(beamWidth / 2, beamLength);
        beamShape.setRotation(-Math.toDegrees(angle)+90);
        beamShape.setFillColor(Color.MAGENTA);
        addToCanvas();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removeFromCanvas();
                timer.cancel();
            }
        }, 2000);
    }

    public double getBeamLength(){
        return beamLength;
    }

    public boolean updatePosition(){
        // beamShape.setX(initialX += VELOCITY * Math.cos(angle));
        // beamShape.setY(initialY -= VELOCITY * Math.sin(angle));
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

    public double getCenterX(){
        return 0;
    }

    public double getCenterY(){
        return 0;
    }
}

