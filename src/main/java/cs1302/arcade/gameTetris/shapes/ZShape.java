package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
public class ZShape extends Shape{
    public ZShape(int x, Rectangle[][] b, GridPane g) {
        super(x, 2, b, g, Color.BLUE);
        r1 = addRectangle(pivotX, pivotY);//pivot
        r2 = addRectangle(pivotX, pivotY - 1);
        r3 = addRectangle(pivotX-1, pivotY-1);
        r4 = addRectangle(pivotX+1, pivotY);

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
