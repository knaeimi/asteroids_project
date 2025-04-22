import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

public class BulletProjectile implements Projectile {
    private double x;
    private double y;
    private double angle;
    private final double VELOCITY = 0.1;
    private final double maxX = 600;
    private final double maxY = 600;
    private double dx;
    private double dy;
    private Ellipse bulletShape;
    
    public BulletProjectile(double x, double y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;

        double AngleRadians = Math.toRadians(angle);
        this.dx = VELOCITY * Math.cos(AngleRadians);
        this.dy = -VELOCITY * Math.sin(AngleRadians);

        bulletShape = new Ellipse(x,y,10,10);
        bulletShape.setFillColor(Color.WHITE);
    }

    public boolean updatePosition() {
        double newX = x + dx;
        double newY = y + dy;
        if (0 < x && x < maxX && y < maxY && y > 0){
            x = newX;
            y = newY;
            bulletShape.setPosition(x,y);
            return true;
        }
        else{
            return false;
        }
    }

    public double getCenterX(){
        return x;
    }

    public double getCenterY(){
        return y;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(bulletShape);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(bulletShape);
    }
}
