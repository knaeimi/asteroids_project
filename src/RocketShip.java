import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;

/*
 * This class handles the creation of the RocketShip object, and provides getters for important values.
 */
public class RocketShip {
    private static final double SIDE_LENGTH = 20;
    private Path rocketShape;
    
    /*
     * To construct our RocketShip, we take in an initial x and y position for the ship, and use them to calculate the other two points 
     * for the triangle.
     */
    public RocketShip (double initialX, double initialY){ 
        rocketShape = Path.makeTriangle(initialX, initialY, initialX + SIDE_LENGTH, initialY, initialX + (SIDE_LENGTH/2), initialY - SIDE_LENGTH);
        rocketShape.setStrokeColor(Color.WHITE); 
        rocketShape.setStrokeWidth(2);
    }
    
    public void setCenter(double newX, double newY){
        rocketShape.setCenter(newX, newY);
    }

    public void setStroke(Color color){
        rocketShape.setStrokeColor(color);
    }

    public double getSideLength(){
        return SIDE_LENGTH;
    }

    public double getCenterX(){
        return rocketShape.getCenter().getX();
    }

    public double getCenterY(){
        return rocketShape.getCenter().getY();
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(rocketShape);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(rocketShape);
    }

    public GraphicsObject getShape(){
        return rocketShape;
    }
}
