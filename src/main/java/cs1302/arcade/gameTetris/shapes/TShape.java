package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
public class TShape extends Shape{


    public TShape(int x, Rectangle[][] b, GridPane g) {
        super(x, 2, b, g, Color.BLUE);
        r1 = addRectangle(pivotX, pivotY);//pivot
        r2 = addRectangle(pivotX, pivotY - 1);
        r3 = addRectangle(pivotX+1, pivotY);
        r4 = addRectangle(pivotX-1, pivotY);

        rectangles[0] = r1;
        rectangles[1] = r2;
        rectangles[2] = r3;
        rectangles[3] = r4;
    }

     public void rotateTo90() {
         removeRectangle(r4);
         r4 = addRectangle(pivotX, pivotY + 1);    
         rectangles[3] = r4;
     }
    
    public void rotateTo180() {
        removeRectangle(r2);
        r2 = addRectangle(pivotX - 1, pivotY);
        rectangles[1] = r2;
    }
    public void rotateTo270() {
        System.out.println("270");
        rectangles[3] = addRectangle(pivotX, pivotY+1);
        //removeRectangle(pivotX+1, pivotY);

        rectangles[2] = addRectangle(pivotX, pivotY-1);
        //removeRectangle(pivotX-1, pivotY);

        rectangles[1] = addRectangle(pivotX -1, pivotY);
        //removeRectangle(pivotX, pivotY+1);
        
        
    }    
    public void rotateTo0() {
         System.out.println("0");
         rectangles[3] = addRectangle(pivotX-1, pivotY);
         //removeRectangle(pivotX, pivotY+1);

         rectangles[2] = addRectangle(pivotX+1, pivotY);
         //removeRectangle(pivotX, pivotY-1);

         rectangles[1] = addRectangle(pivotX, pivotY-1);
         //removeRectangle(pivotX -1, pivotY);

         


     }

    
    
}
