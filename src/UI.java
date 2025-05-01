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
   private GraphicsText noLivesText;
   private int score = 0;
   private CanvasWindow canvas;
   private PlayerShip playerShip;

   /*
    * Constructor for the UI which takes in the canvas and the playership object which is will be used in
    another method to put ships in the top left to count lives.
    */
   public UI(CanvasWindow canvas, PlayerShip playerShip){
      this.canvas = canvas;
      this.playerShip = playerShip;
      createUI();
   }

   /*
    * Creates 3 rocketships which each represent one life in the game. They are each added to a list which manages lives.
    */
   public void createRockets(){
      rocket1 = new RocketShip(50, 125);
      rocket2 = new RocketShip(125, 125);
      rocket3 = new RocketShip(200, 125);
      rocketList = new ArrayList<>();
      rocketList.add(rocket1);
      rocketList.add(rocket2);
      rocketList.add(rocket3);
   }

   /*
    * Orients the rockets and adds them to the canvas.
    */
   public void addRockets(){
      for (RocketShip rocket: rocketList){
         rocket.setStroke(Color.GREEN);
         rocket.getShape().rotateBy(-15);
         rocket.setRocketSize(2);
         rocket.addToCanvas(canvas);
      }
   }

   /*
    * Used in the updateRocketStatus method.
    */
   public void updateColors(Color color){
      for(RocketShip rocket : rocketList){
         rocket.removeFromCanvas(canvas);
         rocket.setStroke(color);
         rocket.addToCanvas(canvas);
      }
   }
   
   /*
    * Adds the score text to the top left of the screen.
    */
   public void setScoreText(){
      scoreText = new GraphicsText();
      scoreText.setText(String.valueOf("Score:  " + score));
      scoreText.setCenter(140,30);
      scoreText.setFillColor(Color.GREEN);
      scoreText.setFontStyle(FontStyle.BOLD_ITALIC);
      scoreText.setScale(3);
      canvas.add(scoreText);
   }

   /*
    * Very similar to setScoreText but is called when all three lives are lost and the text appears in the middle of the screen.
    */
   public void setGameOverText(){
      gameOverText = new GraphicsText();
      gameOverText.setText("GAME OVER");
      gameOverText.setCenter(canvas.getWidth()/2, canvas.getHeight()/2);
      gameOverText.setFillColor(Color.RED);
      gameOverText.setFontStyle(FontStyle.BOLD_ITALIC);
      gameOverText.setScale(5);
      canvas.add(gameOverText);
   }

   /*
    * This method adds points to the score updates the score on screen.
    */
   public void addPoints(){
      score += 20;
      scoreText.setText(String.valueOf("Score:  " + score));
   }

   /*
    * Utility method to accomplish all life checks in CollisionManager.
    */
   public void removeLife(){
      checkForGameOver();
      updateRocketStatus();
      removeShipFromUI();
   }

   /*
    * Nice utility method for a clean call in the constructor.
    */
   public void createUI(){
      createRockets();
      addRockets();
      setScoreText();
   }

   /*
    * A way to indicate to the user visually to be careful as lives are lost.
    */
   public void updateRocketStatus(){
      if(rocketList.size() - 1  == 2){
         updateColors(Color.ORANGE);
      }

      if(rocketList.size() - 1 == 1 ){
         updateColors(Color.RED);
      }

      if(rocketList.size() - 1 == 0){
         noLivesText = new GraphicsText();
         noLivesText.setText("NO LIVES REMAINING");
         noLivesText.setFillColor(Color.RED);
         noLivesText.setPosition(88,90);
         noLivesText.setScale(2);
         canvas.add(noLivesText);
      }
   }

   /*
    * Checks to see if there are any lives left by checking the size of the rocketList. If there are no lives left,
     setGameOverText is called and the window closes after 5 seconds.
    */
   public void checkForGameOver(){
      if(rocketList.size() == 0){
         setGameOverText();
         playerShip.removeFromCanvas(canvas);
         canvas.draw();
         canvas.pause(5000);
         canvas.closeWindow();
      }
   }

   /*
    * Removes the ship from the top left of the screen and removes it from the list for the future.
    */
   public void removeShipFromUI(){
      RocketShip uiShip = rocketList.get(rocketList.size()-1);
      rocketList.remove(uiShip);
      uiShip.removeFromCanvas(canvas);
   }
}
