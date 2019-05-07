package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
public class TShape extends Shape{
    
    
    public TShape(GridPane g) {
        super(1, g, Color.PURPLE);
        pivotX = (int)(Math.random() * 8) + 1;
        
        //   r2
        //r4 r1 r3
        r1 = addRectangle(pivotX, pivotY);//pivot
        r2 = addRectangle(pivotX, pivotY - 1);
        r3 = addRectangle(pivotX+1, pivotY);
        r4 = addRectangle(pivotX-1, pivotY);
                
        rectangles[0] = r1;
        rectangles[1] = r2;
        rectangles[2] = r3;
        rectangles[3] = r4;
    }
    
    
    /** {@inheritDoc} */
    public void rotateTo90() {
        //Rotates to:
        //r2
        //r1 r3
        //r4
        int x = pivotX;
        int y = pivotY + 1;
        Rectangle next = getFromGrid(x, y);
        if(next == null) {
            removeRectangle(r4);
            r4 = addRectangle(x, y);    
            rectangles[3] = r4;
            angle += 90;
        }
    }

    /** {@inheritDoc} */
    public void rotateTo180() {
        //Rotates to:
        //r2 r1 r3
        //   r4
        int x = pivotX - 1;
        int y = pivotY;
        if(getFromGrid(x, y) == null) {
            removeRectangle(r2);
            r2 = addRectangle(x, y);
            rectangles[1] = r2;
            angle += 90;
        }
    }

    /** {@inheritDoc} */
    public void rotateTo270() {
        //Rotates to:
        //   r3
        //r2 r1
        //   r4
        int x = pivotX;
        int y = pivotY - 1;
        if(getFromGrid(x, y) == null) {
            removeRectangle(r3);
            r3 = addRectangle(x, y);
            rectangles[2] = r3;
            angle += 90;
        }
    }

    /** {@inheritDoc} */
    public void rotateTo0() {
        //Rotates to:
        //   r2
        //r4 r1 r3
        int x = pivotX + 1;
        int y = pivotY;
        if(getFromGrid(x, y) == null) {
            for (Rectangle r : rectangles) {
                removeRectangle(r);
            }
            r1 = addRectangle(pivotX, pivotY);//pivot
            r2 = addRectangle(pivotX, pivotY - 1);
            r3 = addRectangle(pivotX+1, pivotY);
            r4 = addRectangle(pivotX-1, pivotY);            
            rectangles[0] = r1;
            rectangles[1] = r2;
            rectangles[2] = r3;
            rectangles[3] = r4;
            angle = 0;
        }
    }  
}
