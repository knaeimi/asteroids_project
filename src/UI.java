import java.awt.Color;
import java.util.ArrayList;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

/*
 * Sets UI and manages score/game over text.
 */
public class UI {
   private ArrayList<RocketShip> rocketList;
   private RocketShip rocket1, rocket2, rocket3;
   private GraphicsText scoreText;
   private GraphicsText gameOverText;
   private int score = 0;
   private CanvasWindow canvas;
   private PlayerShip playerShip;

   public UI(CanvasWindow canvas, PlayerShip playerShip){
      this.canvas = canvas;
      this.playerShip = playerShip;
      createUI();
   }

   public void createRockets(){
      rocket1 = new RocketShip(50, 125);
      rocket2 = new RocketShip(125, 125);
      rocket3 = new RocketShip(200, 125);
      rocketList = new ArrayList<>();
      rocketList.add(rocket1);
      rocketList.add(rocket2);
      rocketList.add(rocket3);
   }

   public void addRockets(){
      for (RocketShip rocket: rocketList){
         rocket.setStroke(Color.GREEN);
         rocket.getShape().rotateBy(-15);
         rocket.setRocketSize(2);
         rocket.addToCanvas(canvas);
      }
   }
   
   public void setScoreText(){
      scoreText = new GraphicsText();
      scoreText.setText(String.valueOf("Score:  " + score));
      scoreText.setCenter(140,30);
      scoreText.setFillColor(Color.GREEN);
      scoreText.setFontStyle(FontStyle.BOLD_ITALIC);
      scoreText.setScale(3);
      canvas.add(scoreText);
   }

   public void setGameOverText(){
      gameOverText = new GraphicsText();
      gameOverText.setText("GAME OVER");
      gameOverText.setCenter(canvas.getWidth()/2, canvas.getHeight()/2);
      gameOverText.setFillColor(Color.RED);
      gameOverText.setFontStyle(FontStyle.BOLD_ITALIC);
      gameOverText.setScale(5);
      canvas.add(gameOverText);
   }

   public void addPoints(){
      score += 20;
      scoreText.setText(String.valueOf("Score:  " + score));
   }

   /*
    * We use this method to first check if the user has lost all lives-if so, throw up the game over text and then
    remove the player from the canvas before (first waiting a couple seconds) closing the window. Otherwise, we
    take a ship from the 3 up top, along with the actual list.
    */
   public void removeLife(){
      if(rocketList.size() == 0){
         setGameOverText();
         playerShip.removeFromCanvas(canvas);
         canvas.draw();
         canvas.pause(5000);
         canvas.closeWindow();
      }
      RocketShip uiShip = rocketList.get(rocketList.size()-1);
      rocketList.remove(uiShip);
      uiShip.removeFromCanvas(canvas);
   }

   /*
    * Nice utility method for a clean call in the constructor.
    */
   public void createUI(){
      createRockets();
      addRockets();
      setScoreText();
   }
   
}
