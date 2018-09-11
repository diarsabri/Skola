import java.awt.*;
import java.awt.geom.*;

public class BoxBall
{
    private int ballDegradation = 1;
    private Ellipse2D.Double circle;
    private Rectangle2D.Double rectangle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final Rectangle border;
    private Canvas canvas;
    private int ySpeed = 1;
    private int xSpeed = 1;
    
    public BoxBall(int xPos, int yPos,int xSpeed, int ySpeed,  int ballDiameter, Color ballColor, 
    Rectangle borderRect, Canvas drawingCanvas)
    {
       xPosition = xPos;
       yPosition = yPos;
       this.xSpeed = xSpeed;
       this.ySpeed = ySpeed;
       color = ballColor;
       diameter = ballDiameter;
       canvas = drawingCanvas;
       border = borderRect;
    }
    
    /**
     * draws ball att current position
     */
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * removes -> moves -> redraws
     **/
    public void move()
    {
        erase();
        
        yPosition += ySpeed;
        xPosition += xSpeed;
        
        if(yPosition >= (border.getMaxY() - diameter -2) && ySpeed > 0) {
            yPosition = (int)(border.getMaxY() - diameter);
            ySpeed = -ySpeed; 
        }
        
        else if(yPosition <= (border.getMinY()) && ySpeed < 0) {
            yPosition = (int)(border.getMinY());
            ySpeed = -ySpeed; 
        }
        
        else if(xPosition >= (border.getMaxX() - diameter -2) && xSpeed > 0) {
            xPosition = (int)(border.getMaxX() - diameter);
            xSpeed = -xSpeed; 
        }
        
        else if(xPosition <= (border.getMinX()) && xSpeed < 0) {
            xPosition = (int)(border.getMinX());
            xSpeed = -xSpeed;
        }
        
        draw();
    }
    
    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
