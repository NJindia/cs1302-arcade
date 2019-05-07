package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;

public class LShape extends Shape{

    public LShape(GridPane g) {
        super(1, g, Color.ORANGE);
        pivotX = (int)(Math.random() * 8) + 1;
        //      r3
        //r4 r1 r2
        r1 = addRectangle(pivotX, pivotY);
        r2 = addRectangle(pivotX + 1, pivotY);
        r3 = addRectangle(pivotX + 1, pivotY - 1);
        r4 = addRectangle(pivotX -1,pivotY);
        rectangles[0] = r1;
        rectangles[1] = r2;
        rectangles[2] = r3;
        rectangles[3] = r4;
    }

    /** {@inheritDoc} */
    public void rotateTo90() {
        //Rotates to:
        //r4
        //r1
        //r2 r3
        Rectangle nextR4 = getFromGrid(pivotX, pivotY - 1);
        Rectangle nextR2 = getFromGrid(pivotX, pivotY + 1);
        Rectangle nextR3 = getFromGrid(pivotX + 1, pivotY + 1);
        if(nextR4 == null && nextR2 == null && nextR3 == null) {
            removeRectangle(r4);
            removeRectangle(r2);
            removeRectangle(r3);
            r4 = addRectangle(pivotX, pivotY - 1);
            r2 = addRectangle(pivotX, pivotY + 1);
            r3 = addRectangle(pivotX + 1, pivotY + 1);
            rectangles[3] = r4;
            rectangles[2] = r3;
            rectangles[1] = r2;
            angle = 90;
        }
    }

    /** {@inheritDoc} */
    public void rotateTo180() {
        //Rotates to:
        //r2 r1 r4
        //r3
        Rectangle nextR4 = getFromGrid(pivotX + 1, pivotY);
        Rectangle nextR2 = getFromGrid(pivotX - 1, pivotY);
        Rectangle nextR3 = getFromGrid(pivotX - 1, pivotY + 1);
        if(nextR4 == null && nextR2 == null && nextR3 == null) {
            removeRectangle(r4);
            removeRectangle(r2);
            removeRectangle(r3);
            r4 = addRectangle(pivotX + 1, pivotY);
            r2 = addRectangle(pivotX - 1, pivotY);
            r3 = addRectangle(pivotX - 1, pivotY + 1);
            rectangles[3] = r4;
            rectangles[2] = r3;
            rectangles[1] = r2;
            angle = 180;
        }
    }

    /** {@inheritDoc} */
    public void rotateTo270() {
        //Rotates to:
        //r3 r2
        //   r1
        //   r4
        Rectangle nextR4 = getFromGrid(pivotX, pivotY + 1);
        Rectangle nextR2 = getFromGrid(pivotX, pivotY - 1);
        Rectangle nextR3 = getFromGrid(pivotX - 1, pivotY - 1);
        if(nextR4 == null && nextR2 == null && nextR3 == null) {
            removeRectangle(r4);
            removeRectangle(r2);
            removeRectangle(r3);
            r4 = addRectangle(pivotX, pivotY + 1);
            r2 = addRectangle(pivotX, pivotY - 1);
            r3 = addRectangle(pivotX - 1, pivotY - 1);
            rectangles[3] = r4;
            rectangles[2] = r3;
            rectangles[1] = r2;
            angle = 270;
        }
    }

    
    /** {@inheritDoc} */
    public void rotateTo0() {
        //Rotates to:
        //      r3
        //r4 r1 r2
        Rectangle nextR4 = getFromGrid(pivotX - 1, pivotY);
        Rectangle nextR2 = getFromGrid(pivotX + 1, pivotY);
        Rectangle nextR3 = getFromGrid(pivotX + 1, pivotY - 1);
        if(nextR4 == null && nextR2 == null && nextR3 == null) {
            removeRectangle(r4);
            removeRectangle(r2);
            removeRectangle(r3);
            r4 = addRectangle(pivotX - 1, pivotY);
            r2 = addRectangle(pivotX + 1, pivotY);
            r3 = addRectangle(pivotX + 1, pivotY - 1);
            rectangles[3] = r4;
            rectangles[2] = r3;
            rectangles[1] = r2;
            angle = 0;
        }        
    }
}
