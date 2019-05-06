package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;

public class IShape extends Shape{

     public IShape(int x, Rectangle[][] b, GridPane g) 
         {
             super(x, 1, b, g, Color.GREEN);

              r1 = addRectangle(pivotX, pivotY);
              r2 = addRectangle(pivotX+1, pivotY);
              r3 = addRectangle(pivotX+2, pivotY);
              r4 = addRectangle(pivotX-1, pivotY);
              rectangles[0] = r1;
              rectangles[1] = r2;
              rectangles[2] = r3;
              rectangles[3] = r4;
         }
         public void rotateTo0() {}
         public void rotateTo90() {} 
         public void rotateTo180() {}
         public void rotateTo270() {}
}
