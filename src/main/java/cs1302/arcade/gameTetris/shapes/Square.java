package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;

public class Square extends Shape {
    //pivot is 1:
    //10
    //00
    public Square(GridPane g) {
        super(0, g, Color.YELLOW);
        int x = (int)(Math.random() * 9);
        pivotX = x;
        r1 = addRectangle(pivotX, pivotY);
        r2 = addRectangle(pivotX+1, pivotY);
        r3 = addRectangle(pivotX, pivotY+1);
        r4 = addRectangle(pivotX+1,pivotY+1);
        reassignRectangles();
    }

    /** Does nothing, as {@code Square} does not rotate. */
    public void rotateTo0() {}
    
    /** Does nothing, as {@code Square} does not rotate. */
    public void rotateTo90() {} 
    
    /** Does nothing, as {@code Square} does not rotate. */
    public void rotateTo180() {}
    
    /** Does nothing, as {@code Square} does not rotate. */
    public void rotateTo270() {}
}
