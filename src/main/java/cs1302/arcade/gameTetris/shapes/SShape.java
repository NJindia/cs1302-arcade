package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
/**
 * Represents the S shape in Tetris
 */
public class SShape extends Shape {

    /**
     * Sets the appropriate properties for an S Shape.
     */
    public SShape(GridPane g)  {
        super(1, g, Color.GREEN);
        pivotX = (int)(Math.random() * 8) + 1;
        //   r2 r3
        //r4 r1
        r1 = addRectangle(pivotX, pivotY); //pivot
        r2 = addRectangle(pivotX, pivotY - 1);
        r3 = addRectangle(pivotX + 1, pivotY - 1);
        r4 = addRectangle(pivotX - 1, pivotY);

        reassignRectangles();
    }

    /** {@inheritDoc} */
    public void rotateTo90() {
        //Rotates to:
        //r2
        //r1 r3
        //   r4
        try {
            Rectangle nextR4 = getFromGrid(pivotX + 1, pivotY + 1);
            Rectangle nextR3 = getFromGrid(pivotX + 1, pivotY);
            if(nextR4 == null && nextR3 == null) {
                removeRectangle(r4);
                removeRectangle(r3);
                r4 = addRectangle(pivotX + 1, pivotY + 1);
                r3 = addRectangle(pivotX + 1, pivotY);
                reassignRectangles();
                angle = 90;
            }
        } catch (IndexOutOfBoundsException e) {}
    }
    
    /** {@inheritDoc} */
    public void rotateTo180() {
        //Rotates to:
        //   r1 r3
        //r4 r2
        try {
            Rectangle nextR4 = getFromGrid(pivotX - 1, pivotY + 1);
            Rectangle nextR2 = getFromGrid(pivotX, pivotY + 1);
            if(nextR4 == null && nextR2 == null) {
                removeRectangle(r4);
                removeRectangle(r2);
                r4 = addRectangle(pivotX - 1, pivotY + 1);
                r2 = addRectangle(pivotX, pivotY + 1);
                reassignRectangles();
                angle = 180;
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    /** {@inheritDoc} */
    public void rotateTo270() {
        //Rotates to:
        //r3
        //r4 r1
        //   r2
        try {
            Rectangle nextR4 = getFromGrid(pivotX - 1, pivotY);
            Rectangle nextR3 = getFromGrid(pivotX - 1, pivotY - 1);
            if(nextR4 == null && nextR3 == null) {
                removeRectangle(r4);
                removeRectangle(r3);
                r4 = addRectangle(pivotX - 1, pivotY);
                r3 = addRectangle(pivotX - 1, pivotY - 1);
                reassignRectangles();
                angle = 270;
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    
    /** {@inheritDoc} */
    public void rotateTo0() {
        //Rotates to:
        //   r2 r3
        //r4 r1
        try {
            Rectangle nextR2 = getFromGrid(pivotX, pivotY - 1);
            Rectangle nextR3 = getFromGrid(pivotX + 1, pivotY - 1);
            if(nextR2 == null && nextR3 == null) {
                removeRectangle(r2);
                removeRectangle(r3);
                r2 = addRectangle(pivotX, pivotY - 1);
                r3 = addRectangle(pivotX + 1, pivotY - 1);
                reassignRectangles();
                angle = 0;
            }
        } catch (IndexOutOfBoundsException e) {}
    }
}

    
