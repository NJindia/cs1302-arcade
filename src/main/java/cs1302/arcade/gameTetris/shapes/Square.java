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
        addRectangle(x, y);
        addRectangle(x+1, y);
        addRectangle(x, y+1);
        addRectangle(x+1,y+1);
    }

    //Square doesn't rotate
    public void rotateTo0() {} 
    public void rotateTo90() {} 
    public void rotateTo180() {} 
    public void rotateTo270() {} 
}

