import edu.macalester.graphics.*;

public class AsteroidsGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private RocketShip rocketShip;
    private CanvasWindow canvas;

    public AsteroidsGame(){
        canvas = new CanvasWindow("Asteroids", CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public static void main(String[] args) {
        new AsteroidsGame();
        
    }


}
