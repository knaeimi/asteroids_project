import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Path;

public class RocketShip {
    private static final double SIDE_LENGTH = 20;
    private Path rocketShape;
    private double steering, rotationSpeed = 15;
    int speed = 10;

    public RocketShip (double x0, double y0){ 
        rocketShape = Path.makeTriangle(x0, y0, x0 + SIDE_LENGTH, y0, x0 + (SIDE_LENGTH/2), y0 - SIDE_LENGTH);
        rocketShape.setStrokeColor(Color.WHITE); 
        steering = Math.toRadians(90); //Steering variable that tracks left/right rotation. Initially 90 for expected behavior.
    }

    public void up(){
        double x = getShape().getX();
        double y = getShape().getY();
        getShape().setX(x += speed * Math.cos(steering));
        getShape().setY(y -= speed * Math.sin(steering));
    }

    void right(){
        steering -= Math.toRadians(rotationSpeed);
        rocketShape.rotateBy(rotationSpeed);
      }
      void left(){
        steering += Math.toRadians(rotationSpeed);
        rocketShape.rotateBy(-rotationSpeed);
      } 

    public void setCenter(double newX, double newY){
        rocketShape.setCenter(newX, newY);
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

    public double getSpeed(){
        return speed;
    }
}
