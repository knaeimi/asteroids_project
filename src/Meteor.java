import edu.macalester.graphics.*;

public class Meteor {
    private double centerX;
    private double centerY;
    private double angle;
    private Image meteorImage;
    private CanvasWindow canvas;
    private static final double METEOR_SPEED = 2;

    public Meteor(double centerX, double centerY, double angle, CanvasWindow canvas) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.angle = angle;
        this.canvas = canvas;
    
        meteorImage = new Image("meteor.png");
        meteorImage.setScale(0.3);


    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setPosition(double newX, double newY){
        meteorImage.setCenter(newX, newY);
    }

    public GraphicsObject getShape() {
        return meteorImage;
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(meteorImage);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(meteorImage);
    }

    public void updatePosition() {

        meteorImage.setX(centerX += METEOR_SPEED * Math.cos(angle));
        meteorImage.setY(centerY -= METEOR_SPEED * Math.sin(angle));
    }
}
