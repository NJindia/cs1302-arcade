package cs1302.arcade.gameTetris.shapes;
import java.util.Random;

/**
 *  represents L shape 
 */
public class LShape extends Shape{

    public LShape(int x, int y, Rectangle[][] b, GridPane g) {
         super(x, y, b, g, Color.BLUE);
         addRectangle(x, y);
         addRectangle(x, y-1);
         addRectangle(x, y-2);
         addRectangle(x+1,y-3);
    }
}
             
