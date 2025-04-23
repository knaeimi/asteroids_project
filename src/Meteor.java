import java.awt.Color;

import edu.macalester.graphics.*;

public class Meteor {

    private double centerX;
    private double centerY;
    private double radius;
    private static final double METEOR_SPEED = 10;

    private Ellipse meteorShape;

    public Meteor(double centerX, double centerY, double radius, double angle) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        meteorShape = new Ellipse(centerX-radius, centerY-radius, radius*2, radius*2);
        meteorShape.setCenter(centerX, centerY);
        meteorShape.setStrokeColor(Color.WHITE);
        meteorShape.setFillColor(Color.GRAY);
    }

    public double getRadius() {
        return this.radius;
    }

    public double getCenterX() {
        return this.centerX;
    }

    public double getCenterY() {
        return this.centerY;
    }

    public GraphicsObject getShape() {
        return this.meteorShape;
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(meteorShape);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(meteorShape);
    }

    public void updatePosition() {
        // TODO: Implement Meteor movement
    }

}
