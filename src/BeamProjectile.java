import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

/*
 * This class creates a beam object from the point of the ship, and implements the projectile interface to do so.
 */
public class BeamProjectile implements Projectile {
    private final long BEAM_DURATION = 100; 
    private double beamWidth, beamLength;
    private CanvasWindow canvas;
    private Rectangle beamShape;
    private double initialX, initialY, angle;
    private long timeSinceSpawn = System.currentTimeMillis(); 
    

    /*
     * The constructor method which takes in the x and y value of the rocketship as well as the angle it is facing,
     * and assigns those values to already present variables. Calls the createBeam method.
     */
    public BeamProjectile(double initialX, double initialY, double angle, CanvasWindow canvas){
        this.canvas = canvas;
        this.initialX = initialX;
        this.initialY = initialY;
        this.angle = angle;
        
        beamLength = canvas.getWidth();
        beamWidth = 20;
        createBeam();
    }

    /*
     * We use the same system for mitigating bullet/beam spamming with the time stuff, but the subtle part here is returning
     * false in this method implementation so that in ProjectileManager's updateProjectile method, it's marked for removal. 
     */
    public boolean updatePosition(){ 
       long currentTime = System.currentTimeMillis();

       if(currentTime - timeSinceSpawn > BEAM_DURATION){
            timeSinceSpawn = currentTime;
            return false;
        }
        return true;
    }

    /*
     * We create points from the Asteroid/Beam to use our beam collision check method. 
     */
    public boolean intersects(Asteroid asteroid){ 
       Point P0 =  new Point(initialX, initialY);
       double endX = initialX + Math.cos(angle) * beamLength;
       double endY = initialY - Math.sin(angle) * beamLength;
       Point P1 = new Point(endX, endY);

       return isAsteroidInBeam(P0, P1, asteroid, beamWidth);
    }

    /*
     * Thank you Bret Jackson for explaining Linear Algebra to me. This method takes in two points, an
     * asteroid, and the beamWidth, all to use vector math to determine if an asteroid is in the beam.
     * P0 is the start of the beam (ship tip point) and P1 is the end of the beam(following the ships
     * current angle). 
     * First: We take in the asteroid to create a point E, which is the center of the asteroid.
     * Second: We create the beam's direction vector (start to end), which is V = P1 - P0 for x and y.
     * Third: We create a vector from the beams starting point P0 to the asteroids center point using our 
     * point E we made earlier.
     * Fourth: We project our new vector VE (VEx, veY) onto vector V (Vx^2, Vy^2) by computing dot products
     * (linear algebra thing...don't worry about it) and then dividing to get t, which tells us how far the asteroid
     * center is along the beam.
     * Fifth: We clamp t using the min/max functions to stay on the beam.
     * Sixth: We compute point P3 which gives us the closest point on the beams center line to the asteroid itself.
     * Seventh: We compute the distance between the asteroids center from P3 (so the shortest distance
     * from the asteroid center to the middle of the beam)
     * Finally, we just check if that distance is within half the beam + the asteroid radius combined. If so,
     * we have a collision.
     */
    private boolean isAsteroidInBeam(Point P0, Point P1, Asteroid asteroid, double beamWidth){
        Point E = new Point(asteroid.getCenterX(), asteroid.getCenterY());
        
        double Vx = P1.getX() - P0.getX();
        double Vy = P1.getY() - P0.getY();
        
        double VEx = E.getX() - P0.getX();
        double VEy = E.getY() - P0.getY();

        double dotVEV = Vx * VEx + Vy * VEy;
        double dotVV = Vx * Vx + Vy * Vy;

        double t = dotVEV/dotVV;
        t = Math.max(0,Math.min(1,t));

        double P3x = P0.getX() + Vx * t;
        double P3y = P0.getY() + Vy * t;

        double dX = E.getX() - P3x;
        double dY = E.getY() - P3y;

        double distance = Math.sqrt(dX * dX + dY * dY);

        
        return distance <= (beamWidth/2.0 + asteroid.getRadius());
    }

    /*
     * We take the initialX/Y positions of the ship and do a bit of math to maintain the correct angle. 
     */
    public void createBeam(){
        double topLeftX = initialX - beamWidth / 2;
        double topLeftY = initialY - beamLength;
        
        beamShape = new Rectangle(topLeftX, topLeftY, beamWidth, beamLength);
        beamShape.setAnchor(beamWidth / 2, beamLength);
        beamShape.setRotation(-Math.toDegrees(angle)+90);
        beamShape.setFillColor(Color.MAGENTA);
        addToCanvas();
    }
    
    /*
     * Adds the beam to the canvas.
     */
    public void addToCanvas(){
        canvas.add(beamShape);
    }

    /*
     * Removes the beam from the canvas.
     */
    public void removeFromCanvas(){
        canvas.remove(beamShape);
    }

    /*
     * Returns the length of the beam.
     */
    public double getBeamLength(){
        return beamLength;
    }

    /*
     * Returns the beam shape in order to manage collisions.
     */
    public GraphicsObject getProjectileShape(){
        return beamShape;
    }

    /*
     * Returns the center x position of the beam.
     */
    public double getCenterX(){
        return beamShape.getCenter().getX();
    }

    /*
     * Returns the center y position of the beam.
     */
    public double getCenterY(){
        return beamShape.getCenter().getY();
    }
}
