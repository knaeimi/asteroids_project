import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
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

   public UI(CanvasWindow canvas){
      this.canvas = canvas;
      createRockets();
      addRockets();
   }

   public void createRockets(){
      rocket1 = new RocketShip(50, 75);
      rocket2 = new RocketShip(80, 75);
      rocket3 = new RocketShip(110, 75);
      rocketList = new ArrayList<>();
      rocketList.add(rocket1);
      rocketList.add(rocket2);
      rocketList.add(rocket3);
   }

   public void addRockets(){
      for (RocketShip rocket: rocketList){
         rocket.setStroke(Color.GREEN);
      }
      addToCanvas(canvas);
      scoreText = new GraphicsText();
      setScoreText();

   }
   public void addToCanvas(CanvasWindow canvas){
      for (RocketShip rocket: rocketList){
         rocket.addToCanvas(canvas);
      }
   }

   public void setScoreText(){
      scoreText.setText(String.valueOf("Score: " + score));
      scoreText.setCenter(90,25);
      scoreText.setFillColor(Color.GREEN);
      scoreText.setScale(2);
      canvas.add(scoreText);
   }

   public void addPoints(){
      score += 20;
      setScoreText();
   }

   public void removeLife(){
      //TODO: Once we get collision working, change this method so that the implementation works.
      RocketShip lifeLost = rocketList.get(rocketList.size()-1);
      rocketList.remove(rocketList.size()-1);
      lifeLost.removeFromCanvas(canvas);
   }

}
