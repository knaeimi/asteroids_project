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
   private GraphicsText gameStartText;
   private int score = 0;
   private CanvasWindow canvas;
   private PlayerShip playerShip;
   private int lives = 3;

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
    * This method adds points to the score updates the score on screen.
    */
   public void addPoints(){
      score += 20;
      scoreText.setText(String.valueOf("Score:  " + score));
   }

   /*
    * Getter method that returns number of lives.
    */
   public int getLives() {
      return lives;
   }

   /*
    * Getter method that returns the score number.
    */
    public int getScore() {
      return score;
   }

   /*
    * For CollisionManager to use with ship collisions
    * Used in CollisionManager to decrement lives until 0, in which case the game closes.
    */
   public boolean removeLife(){
      lives--;
      if(lives < 0){
         gameOver();
         return false;
      }
      updateStatus();
      return true;
   }

   /*
    * Nice utility method for a clean call in the constructor.
    */
   public void createUI(){
      createRockets();
      addRockets();
      setScoreText();
      setGameStartText();
   }

   /*
    * Creates the big asteroids text before you play the game.
    */
   public void setGameStartText(){
      gameStartText = new GraphicsText();
      gameStartText.setText("ASTEROIDS");
      gameStartText.setStrokeWidth(0.2);
      gameStartText.setStrokeColor(new Color(150,0,255));
      gameStartText.setPosition(canvas.getWidth()/2,canvas.getHeight()/2);
      gameStartText.setScale(8);
      canvas.add(gameStartText);
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
    * Creates the game over text and ends the game.
    */
   public void gameOver(){
      if(gameOverText == null){
         gameOverText = new GraphicsText();
         gameOverText.setText("GAME OVER");
         gameOverText.setCenter(canvas.getWidth()/2, canvas.getHeight()/2);
         gameOverText.setFillColor(Color.RED);
         gameOverText.setFontStyle(FontStyle.BOLD_ITALIC);
         gameOverText.setScale(5);
         canvas.add(gameOverText);
         playerShip.removeFromCanvas(canvas);
         canvas.draw();

         canvas.pause(5000);
         canvas.closeWindow();
      }
   }
   
   /*
    * We start at 3 lives total, and every time we lose a life we change the UI's health rockets
    * colors to reflect the increasing severity. We protect against index-out-of bounds exceptions
    * with the first conditional, so that when we reach 0 lives, we don't try to remove a rocket at
    * index -1.
    */ 
   public void updateStatus(){
      if(lives <= rocketList.size()){
      RocketShip lostUIShip = rocketList.remove(lives);
      lostUIShip.removeFromCanvas(canvas);
      }

      if(lives == 2){
         updateColors(Color.ORANGE);
      }
      if(lives == 1 ){
         updateColors(Color.RED);
      }

      if(lives == 0){
         noLivesText = new GraphicsText();
         noLivesText.setText("NO LIVES REMAINING");
         noLivesText.setFillColor(Color.RED);
         noLivesText.setPosition(88,90);
         noLivesText.setScale(2);
         canvas.add(noLivesText);
      }
   }

   /*
    * Used in the main class after the start button is clicked.
    */
   public void removeStartText(){
      canvas.remove(gameStartText);
   }
}
