import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random random;
    
    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     * 
     * 5.62
     * 5.64
     */
    public void bounce(int ballsAmount)
    {
        HashSet<BouncingBall> balls = new HashSet<>();
        random = new Random()
        ;
        int ground = 400;
        myCanvas.setVisible(true);
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);
        
        for(int i = 0; i<ballsAmount; i++) {
            BouncingBall ball = new BouncingBall(random.nextInt(300), random.nextInt(250), 16, Color.BLUE, ground, 
            myCanvas);
            balls.add(ball);
            ball.draw();
        }
        
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);
            for(BouncingBall ball : balls) {
                ball.move();
            if(ball.getXPosition() >= 550 ) {
                finished = true;
            }
            }
        }
   }
   
   /**
    * balls in a box
    * 
    * 5.65
    * 5.66
    */
   public void BoxBounce(int nmrBalls)
   {
       myCanvas.setVisible(true);
       Rectangle box = new Rectangle(50, 50, 250,250);
       myCanvas.draw(box);
       HashSet<BoxBall> balls = new HashSet<>();
       random = new Random();
       
       for(int i = 0;i<nmrBalls;i++) {
            Dimension size = myCanvas.getSize();
            int x = (int)box.getX() + random.nextInt((int) box.getWidth()-16);
            int y = (int)box.getY() + random.nextInt((int) box.getHeight() -16);
            int xSpeed = random.nextInt(10);
            int ySpeed = random.nextInt(10);
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            BoxBall ball = new BoxBall(x, y, xSpeed, ySpeed, 16, color, box, myCanvas);
            balls.add(ball);
            ball.draw();
        }
       
       
        boolean finished = false;
        while(!finished) {
        myCanvas.wait(50);
            for(BoxBall ball : balls) {
            ball.move();

        }
       }
    }
   
   
   
   
   
   
   
}
