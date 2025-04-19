import java.awt.Color;

import edu.macalester.graphics.*;


public class RocketShip {
    private static final double SIDE_LENGTH = 20;
    private Path rocketShape;
    private double x0, y0;

    public RocketShip (double x0, double y0){
        this.x0 = x0;
        this.y0 = y0;

        rocketShape = Path.makeTriangle(x0, y0, x0 + SIDE_LENGTH, y0, x0 + (SIDE_LENGTH/2), y0 - SIDE_LENGTH);
        rocketShape.setStrokeColor(Color.WHITE);
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

    public GraphicsObject getShape(){ //don't need this keyword, no naming conflicts
        return rocketShape;
    }
}
