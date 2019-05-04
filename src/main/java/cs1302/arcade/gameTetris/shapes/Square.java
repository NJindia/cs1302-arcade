package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;

public class Square extends Shape {
    
    public Square(int x, int y, Rectangle[][] b) {
        super(x, y, b);
        super.color = "Yellow";
        
    }

    //Square doesn't rotate
    public void rotateTo0() {} 
    public void rotateTo90() {} 
    public void rotateTo180() {} 
    public void rotateTo270() {} 
}

