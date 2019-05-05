package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/** the tetris pieces
 */
public abstract class Shape{
    public int angle; //can be 0, 90, 180, or 270
    public int pivotX, pivotY; //XY positions of pivot point
    public Color color;
    public Rectangle[][]  board;
    
    
    public Shape(int x, int y, Rectangle[][] b, Color c) {
        angle = 0; //Shapes always start with one orientation
        pivotX = x;
        pivotY = y;
        board = b;
        color = c;
    }

    public void removeRectangle(int x, int y) {
        board[y][x] = null;
    }
    
    public void addRectangle(int x, int y) {
        Rectangle r = new Rectangle();
        r.setFill(color);
        board[y][x] = r;
    }
    
    public void rotate90() {
        switch(angle) {
        case 0:
            rotateTo90();
            break;
        case 90:
            rotateTo180();
            break;
        case 180:
            rotateTo270();
            break;
        case 270:
            rotateTo0();
            break;
        }
    }
    
    public abstract void rotateTo0();

    public abstract void rotateTo90();

    public abstract void rotateTo180();

    public abstract void rotateTo270();
}
