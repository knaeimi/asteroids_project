import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

public class BeamProjectile implements Projectile {
   
    private double beamWidth, beamLength;
    private CanvasWindow canvas;
    private Rectangle beamShape;
    private double initialX, initialY, angle;
    private final long BEAM_DURATION = 100; //100 ms duration
    private long timeSinceSpawn = System.currentTimeMillis(); 
    

    /*
     * BeamProjectile constructor method.
     */
    public BeamProjectile(double initialX, double initialY, double angle, CanvasWindow canvas){
        this.canvas = canvas;
        this.initialX = initialX;
        this.initialY = initialY;
        this.angle = angle;
        
        beamLength = canvas.getWidth();
        beamWidth = 20;
        createBeam();
    }

    public double getBeamLength(){
        return beamLength;
    }

    
    public GraphicsObject getProjectileShape(){
        return beamShape;
    }

    /*
     * Stupid fix. Basically we use the same system for mitigating bullet/beam spamming with the time stuff, but the subtle part here is returning
     * false in this method implementation so that in ProjectileManager's updateProjectile method, it's marked for removal. No more timers here to 
     * cause crashes.
     * 
     */
    public boolean updatePosition(){ 
       long currentTime = System.currentTimeMillis();

       if(currentTime - timeSinceSpawn > BEAM_DURATION){
            removeFromCanvas();
            timeSinceSpawn = currentTime;
            return false;
        }
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
    }
}

 