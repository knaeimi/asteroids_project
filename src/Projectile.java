import edu.macalester.graphics.GraphicsObject;

public interface Projectile {
    public boolean updatePosition();
    public void addToCanvas();
    public void removeFromCanvas();
    public double getCenterX();
    public double getCenterY();
    public boolean isCollidingWithMeteor(MeteorManager meteorManager);
    public GraphicsObject getProjectileShape();
}
