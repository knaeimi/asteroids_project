import edu.macalester.graphics.CanvasWindow;

public interface Projectile {
    public boolean updatePosition();
    public void addToCanvas(CanvasWindow canvas);
    public void removeFromCanvas(CanvasWindow canvas);
}
