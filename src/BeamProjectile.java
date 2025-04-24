import java.awt.Color;
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
        
    public BeamProjectile(double initialX, double initialY, double angle, CanvasWindow canvas){
        this.canvas = canvas;
        this.initialX = initialX;
        this.initialY = initialY;
        this.angle = angle;
        
        beamLength = canvas.getHeight()/2;
        beamWidth = 10;
      
        beamShape = new Rectangle(initialX, initialY, beamWidth, beamLength);
        beamShape.setFillColor(Color.MAGENTA);
        addToCanvas();
    }

    public double getBeamLength(){
        return beamLength;
    }

    public boolean updatePosition(){
        beamShape.setX(initialX += VELOCITY * Math.cos(angle));
        beamShape.setY(initialY -= VELOCITY * Math.sin(angle));
        return boundsCheck();
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

    /*
     *  TODO: Sean, this is the exact same logic and name as the method from bullet: Is there a more efficient way to do this?
     */
    public boolean boundsCheck(){ 
        return (initialX < canvas.getWidth() || initialX > 0 || initialY < canvas.getHeight() || initialY > 0);
    }
}
