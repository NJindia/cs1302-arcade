package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
/** the tetris pieces
 */
public abstract class Shape{
    public int angle; //can be 0, 90, 180, or 270
    public int pivotX, pivotY; //XY positions of pivot point
    public Color color;
    public Rectangle[][]  board;
    public GridPane grid;
    public Rectangle r1, r2, r3, r4;
    public Rectangle[] rectangles = new Rectangle[4];
    
    public Shape(int x, int y, Rectangle[][] b, GridPane g, Color c) {
        angle = 0; //Shapes always start with one orientation
        pivotX = x;
        pivotY = y;
        board = b;
        color = c;
        grid = g;
    }
    
    public Rectangle getFromGrid(int col, int row) {
        for (Node node : grid.getChildren()) {
            if(node != null && GridPane.getColumnIndex(node)!= null
               && GridPane.getRowIndex(node) != null) {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return (Rectangle)node;
                }
            }
        }
        return null;
    }
    
    public void removeRectangle(int x, int y) {
        grid.getChildren().remove(board[y][x]);
        board[y][x] = null;
    }

    public boolean moveDown() {
        int col, row;
        boolean canMove = true;
        for(Rectangle r : rectangles) {
            if(r != null) {
                col = GridPane.getColumnIndex(r);
                row = GridPane.getRowIndex(r);
                if(row == 19) { //CHANGE IF MAKING 24 ROWS
                    canMove = false;
                } else {
                    Rectangle next = getFromGrid(col, row + 1);
                    if(next != null && next != r1 && next != r2 && next != r3 && next != r4) {
                        canMove = false;
                    }
                }
            }
        }
        if(canMove) {
            for(Rectangle r : rectangles) {
                GridPane.setRowIndex(r, GridPane.getRowIndex(r) + 1);
            }
        } else {
            //Check to see if game over
            //Check to see if row can be cleared
        }
        return canMove;
    }

    public void moveLeft() {}

    public void moveToBottom() {}

    public void moveRight() {
        int col, row;
        System.out.println("right");
        boolean canMove = true;
        for (Rectangle r : rectangles) {
            if (r != null) {
                col = GridPane.getColumnIndex(r);
                row = GridPane.getRowIndex(r);
                if(col == 9) { //If a rectangle is at rightmost index
                    canMove = false;
                } else {
                    Rectangle next = getFromGrid(col + 1, row);
                    if(next != null && next != r1 && next != r2 && next != r3 && next != r4) {
                        canMove = false;
                    }
                }
            }
        }
        if (canMove) {
            for(Rectangle r : rectangles) {
                GridPane.setColumnIndex(r, GridPane.getColumnIndex(r) + 1);
            }
        }
    }
    

    public Rectangle addRectangle(int x, int y) {
        Rectangle r = new Rectangle(30, 30);
        r.setFill(color);
        board[y][x] = r;
        grid.add(r, x, y);
        return r;
    }
    
    public void rotate() {
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
