import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class BeamProjectile implements Projectile {
    private double frontX;
    private double frontY;
    private double angle;
    private double length = 300;
    private Rectangle beamShape;
    private final double BEAM_WIDTH = 10;
    private CanvasWindow canvas;
    private double backX;
    private double backY;
    private final double VELOCITY = 5;
        
    public BeamProjectile(double initialX, double initialY, double angle, CanvasWindow canvas){
            this.frontX = initialX;
            this.frontY = initialY;
            this.angle = angle;
            this.canvas = canvas;
            backX = frontX - length;
            backY = frontY - length;

        beamShape = new Rectangle(backX, backY, BEAM_WIDTH, length);
        beamShape.setFillColor(Color.WHITE);
        beamShape.rotateBy(angle);
        addToCanvas();
    }

    public boolean updatePosition(){
        beamShape.setX(frontX += VELOCITY * Math.cos(angle));
        beamShape.setY(frontY -= VELOCITY * Math.sin(angle));
        return boundsCheck();
    }

    public void addToCanvas(){
        canvas.add(beamShape);
    }

    public void removeFromCanvas(){
        canvas.remove(beamShape);
    }

    public double getCenterX(){
        return backX;
    }

    public double getCenterY(){
        return backY;
    }

    public boolean boundsCheck(){
        return (backX < canvas.getWidth() || backX > 0 || backY < canvas.getHeight() || backY > 0);
    }
}
