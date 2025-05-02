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
        rocketShape = Path.makeTriangle(initialX, initialY, initialX + SIDE_LENGTH, initialY, initialX + (SIDE_LENGTH/2), initialY - SIDE_LENGTH * 2);
        rocketShape.setStrokeColor(Color.WHITE); 
        rocketShape.setStrokeWidth(2);
    }

    /*
     * Sets the graphic object's center to the passed through x/y positions.
     */
    public void setCenter(double newX, double newY){
        rocketShape.setCenter(newX, newY);
    }

    /*
     * For changing the outline of the graphic objects for both the UI objects in the top left and
     * for reflecting combos with changing to purple/pink or flashing health state on collision.
     */
    public void setStroke(Color color){
        rocketShape.setStrokeColor(color);
    }

    /*
     * For changing the size of the UI objects in the top left.
     */
    public void setRocketSize(double size){
        rocketShape.setScale(size);
    }

    /*
     * To get the side length for math calculations.
     */
    public double getSideLength(){
        return SIDE_LENGTH;
    }

    /*
     * For bounds calculations on the x-axis.
     */
    public double getCenterX(){
        return rocketShape.getCenter().getX();
    }

    /*
     * For bounds calculations on the y-axis.
     */
    public double getCenterY(){
        return rocketShape.getCenter().getY();
    }

    /*
     * Utility method to add the player to the canvas once the game has begun.
     */
    public void addToCanvas(CanvasWindow canvas){
        canvas.add(rocketShape);
    }

    /*
     * For removing the player from the canvas on last hit.
     */
    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(rocketShape);
    }

    /*
     * So we can get the graphics object for setting position/other calculations.
     */
    public GraphicsObject getShape(){
        return rocketShape;
    }
}
