import edu.macalester.graphics.*;


public class RocketShip {
    private double centerX;
    private double centerY;
    private static final double SIDE_LENGTH = 5;
    private Path rocketShape;
    private double x0, y0;

    public RocketShip (double centerX, double centerY, double x0, double y0){
        this.centerX = centerX;
        this.centerY = centerY;
        this.x0 = x0;
        this.y0 = y0;

        rocketShape = Path.makeTriangle(x0, y0, x0 + SIDE_LENGTH, y0, x0 + (SIDE_LENGTH/2), y0 + SIDE_LENGTH);
    }

    public void setCenter(double newX, double newY){
        this.centerX = newX;
        this.centerY = newY;
        rocketShape.setCenter(newX, newY);
    }

    public double getCenterX(){
        return centerX;
    }

    public double getCenterY(){
        return centerY;
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(rocketShape);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(rocketShape);
    }

    public GraphicsObject getShape(){
        return this.rocketShape;
    }
}
