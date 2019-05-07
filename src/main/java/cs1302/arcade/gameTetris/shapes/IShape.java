package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;

public class IShape extends Shape{

    public IShape(GridPane g) {
        super(0, g, Color.DEEPSKYBLUE);
        pivotX = (int)(Math.random() * 7);

        //r1 r2 r3 r4
        r1 = addRectangle(pivotX, pivotY); //pivot
        r2 = addRectangle(pivotX+1, pivotY);
        r3 = addRectangle(pivotX+2, pivotY);
        r4 = addRectangle(pivotX+3, pivotY);
        
        reassignRectangles();
    }
    
    /** {@inheritDoc} */
    public void rotateTo90() {
        //Rotates to:
        //r1
        //r2
        //r3
        //r4
        try {
            Rectangle nextR2 = getFromGrid(pivotX, pivotY + 1);
            Rectangle nextR3 = getFromGrid(pivotX, pivotY + 2);
            Rectangle nextR4 = getFromGrid(pivotX, pivotY + 3);
            if(nextR2 == null && nextR3 == null && nextR4 == null) {
                removeRectangle(r2);
                removeRectangle(r3);
                removeRectangle(r4);
                r2 = addRectangle(pivotX, pivotY + 1);
                r3 = addRectangle(pivotX, pivotY + 2);
                r4 = addRectangle(pivotX, pivotY + 3);
                reassignRectangles();
                angle = 90;
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    /** {@inheritDoc} */
    public void rotateTo180() {
        //Rotates to:
        //r4 r3 r2 r1
        try {
            Rectangle nextR2 = getFromGrid(pivotX - 1, pivotY);
            Rectangle nextR3 = getFromGrid(pivotX - 2, pivotY);
            Rectangle nextR4 = getFromGrid(pivotX - 3, pivotY);
            if(nextR2 == null && nextR3 == null && nextR4 == null) {
                removeRectangle(r2);
                removeRectangle(r3);
                removeRectangle(r4);
                r2 = addRectangle(pivotX - 1, pivotY);
                r3 = addRectangle(pivotX - 2, pivotY);
                r4 = addRectangle(pivotX - 3, pivotY);
                reassignRectangles();
                angle = 180;
            }
        } catch (IndexOutOfBoundsException e) {}
    }

    /** {@inheritDoc} */
    public void rotateTo270() {
        //Rotates to:
        //r4
        //r3
        //r2
        //r1
        try {
            Rectangle nextR2 = getFromGrid(pivotX, pivotY - 1);
            Rectangle nextR3 = getFromGrid(pivotX, pivotY - 2);
            Rectangle nextR4 = getFromGrid(pivotX, pivotY - 3);
            if(nextR2 == null && nextR3 == null && nextR4 == null) {
                removeRectangle(r2);
                removeRectangle(r3);
                removeRectangle(r4);
                r2 = addRectangle(pivotX, pivotY - 1);
                r3 = addRectangle(pivotX, pivotY - 2);
                r4 = addRectangle(pivotX, pivotY - 3);
                reassignRectangles();
                angle = 270;
            }
        } catch (IndexOutOfBoundsException e) {}
    }
    
    /** {@inheritDoc} */
    public void rotateTo0() {
        //Rotates to:
        //r1 r2 r3 r4
        try {
            Rectangle nextR2 = getFromGrid(pivotX + 1, pivotY);
            Rectangle nextR3 = getFromGrid(pivotX + 2, pivotY);
            Rectangle nextR4 = getFromGrid(pivotX + 3, pivotY);
            if(nextR2 == null && nextR3 == null && nextR4 == null) {
                removeRectangle(r2);
                removeRectangle(r3);
                removeRectangle(r4);
                r2 = addRectangle(pivotX + 1, pivotY);
                r3 = addRectangle(pivotX + 2, pivotY);
                r4 = addRectangle(pivotX + 3, pivotY);
                reassignRectangles();
                angle = 0;
            }
        } catch (IndexOutOfBoundsException e) {}
    }
}
