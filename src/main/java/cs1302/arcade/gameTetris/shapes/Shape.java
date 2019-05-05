package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;

/** the tetris pieces
 */
public abstract class Shape{
    public int angle; //can be 0, 90, 180, or 270
    public int pivotX, pivotY; //XY positions of pivot point
    public Color color;
    public Rectangle[][]  board;
    public GridPane grid;
    
    public Shape(int x, int y, Rectangle[][] b, GridPane g, Color c) {
        angle = 0; //Shapes always start with one orientation
        pivotX = x;
        pivotY = y;
        board = b;
        color = c;
        grid = g;
    }

    private int indexToCoord(int index) {
        return index * 20;
    }
    
    public void removeRectangle(int x, int y) {
        grid.getChildren().remove(board[y][x]);
        board[y][x] = null;
    }
    
    public void addRectangle(int x, int y) {
        Rectangle r = new Rectangle(30, 30);
        r.setFill(color);
        board[y][x] = r;
        grid.add(r, x, y);
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
