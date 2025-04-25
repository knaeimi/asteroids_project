import java.awt.Color;
import java.awt.geom.Rectangle2D;

import edu.macalester.graphics.*;

public class Meteor {
    private double centerX;
    private double centerY;
    private double radius;
    private double angle;
    private boolean moveLeft;
    private Image meteorImage; //TODO: Kian and Sam: Finish conversion to meteor pngs with Marvin tomorow
    private Rectangle2D boundingBox = meteorImage.getBounds();
    private Rectangle boundingBox1 = new Rectangle(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
    private Ellipse meteorShape;
    private CanvasWindow canvas;
    private static final double METEOR_SPEED = 5;

    public Meteor(double centerX, double centerY, double radius, double angle, boolean moveLeft, CanvasWindow canvas) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.angle = angle;
        this.moveLeft = moveLeft;
        this.canvas = canvas;
        meteorShape = new Ellipse(centerX-radius, centerY-radius, radius*2, radius*2);
        meteorShape.setCenter(centerX, centerY);
        meteorShape.setStrokeColor(Color.WHITE);
        meteorShape.setFillColor(Color.GRAY);
    }

    public double getRadius() { 
        return radius;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public GraphicsObject getShape() {
        return meteorShape;
    }

    public void addToCanvas(CanvasWindow canvas){
        canvas.add(meteorShape);
    }

    public void removeFromCanvas(CanvasWindow canvas){
        canvas.remove(meteorShape);
    }

    public boolean updatePosition() {
        
        if(moveLeft){
            meteorShape.setX(centerX += METEOR_SPEED * Math.cos(angle));
            meteorShape.setY(centerY -= METEOR_SPEED * Math.sin(angle));
        }
        else{
            meteorShape.setX(centerX -= METEOR_SPEED * Math.cos(angle));
            meteorShape.setY(centerY += METEOR_SPEED * Math.sin(angle));
        }
        return boundsCheck();
    }

    public boolean boundsCheck(){
        return (centerX < canvas.getWidth() || centerX > 0 || centerY < canvas.getHeight() || centerY > 0);
    }
}
