package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

/**
 * Represents a T shape in Tetris
 */
public class TShape extends Shape{
    
    /**
     * Sets the properties for a T Shape object
     */
    public TShape(GridPane g) {
        super(1, g, Color.PURPLE);
        pivotX = (int)(Math.random() * 8) + 1;
        
        //   r2
        //r4 r1 r3
        r1 = addRectangle(pivotX, pivotY);//pivot
        r2 = addRectangle(pivotX, pivotY - 1);
        r3 = addRectangle(pivotX+1, pivotY);
        r4 = addRectangle(pivotX-1, pivotY);
                
        reassignRectangles();
    }
    
    
    /** {@inheritDoc} */
    public void rotateTo90() {
        //Rotates to:
        //r2
        //r1 r3
        //r4
        int x = pivotX;
        int y = pivotY + 1;
        try {
            Rectangle next = getFromGrid(x, y);
            if(next == null) {
                removeRectangle(r4);
                r4 = addRectangle(x, y);    
                rectangles[3] = r4;
                angle = 90;
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    /** {@inheritDoc} */
    public void rotateTo180() {
        //Rotates to:
        //r2 r1 r3
        //   r4
        int x = pivotX - 1;
        int y = pivotY;
        try {
            Rectangle next = getFromGrid(x, y);
            if(next == null) {
                removeRectangle(r2);
                r2 = addRectangle(x, y);
                rectangles[1] = r2;
                angle = 180;
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    /** {@inheritDoc} */
    public void rotateTo270() {
        //Rotates to:
        //   r3
        //r2 r1
        //   r4
        int x = pivotX;
        int y = pivotY - 1;
        try {
            Rectangle next = getFromGrid(x, y);
            if(next == null) {
                removeRectangle(r3);
                r3 = addRectangle(x, y);
                rectangles[2] = r3;
                angle = 270;
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    /** {@inheritDoc} */
    public void rotateTo0() {
        //Rotates to:
        //   r2
        //r4 r1 r3
        int x = pivotX + 1;
        int y = pivotY;
        try {
            Rectangle next = getFromGrid(x, y);
            if(next == null) {
                for (Rectangle r : rectangles) {
                    removeRectangle(r);
                }
                r1 = addRectangle(pivotX, pivotY);//pivot
                r2 = addRectangle(pivotX, pivotY - 1);
                r3 = addRectangle(pivotX+1, pivotY);
                r4 = addRectangle(pivotX-1, pivotY);            
                reassignRectangles();
                angle = 0;
            }
        } catch (IndexOutOfBoundsException e) {}
    }  
}
