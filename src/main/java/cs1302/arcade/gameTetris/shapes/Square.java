package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
/**
 * Represents a Square Shape in Tetris.
 */
public class Square extends Shape {
    /**
     * Sets the properties for a Square Shape.
     * @param g a reference to the {@code GridPane} storing the {@code Rectangle} objects
     */
    public Square(GridPane g) {
        super(0, g, Color.YELLOW);
        pivotX = (int)(Math.random() * 9); //random int between 0 and 8

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
