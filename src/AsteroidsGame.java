import edu.macalester.graphics.*;

public class AsteroidsGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private RocketShip rocketShip;
    private CanvasWindow canvas;

    public AsteroidsGame(){
        canvas = new CanvasWindow("Asteroids", CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public void run() {
        canvas.removeAll(); // Reset the canvas

        rocketShip = new RocketShip(300, 300);
        rocketShip.addToCanvas(canvas);
    }

    public static void main(String[] args) {
        AsteroidsGame game = new AsteroidsGame();
        game.run();
    }


}
