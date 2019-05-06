package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;

public class Square extends Shape {
    //pivot is 1:
    //10
    //00
    public Square(int x, int y, Rectangle[][] b, GridPane g) {
        super(x, y, b, g, Color.YELLOW);
        r1 = addRectangle(x, y);
        r2 = addRectangle(x+1, y);
        r3 = addRectangle(x, y+1);
        r4 = addRectangle(x+1,y+1);
        rectangles[0] = r1;
        rectangles[1] = r2;
        rectangles[2] = r3;
        rectangles[3] = r4;
    }

    
    //Square doesn't rotate
    public void rotateTo0() {} 
    public void rotateTo90() {} 
    public void rotateTo180() {}
    public void rotateTo270() {}
}
