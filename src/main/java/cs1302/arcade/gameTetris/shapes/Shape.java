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
    public GridPane grid;
    public Rectangle r1, r2, r3, r4;
    public Rectangle[] rectangles = new Rectangle[4];
    
    public Shape(int y, GridPane g, Color c) {
        angle = 0; //Shapes always start with one orientation
        pivotY = y;
        color = c;
        grid = g;
    }
    /**
     * @throws IndexOutOfBoundsException if col or row are out of the grid's index
     */
    public Rectangle getFromGrid(int col, int row) {
        if(col < 0 || col > 9 || row < 0 || row > 19) {
            throw new IndexOutOfBoundsException();
        }
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

    public boolean isNotR(Rectangle r) {
        if(r != r1 && r != r2 && r != r3 && r != r4) {
            return true;
        }
        return false;
    }

    public void reassignRectangles() {
        rectangles[0] = r1;
        rectangles[1] = r2;
        rectangles[2] = r3;
        rectangles[3] = r4;
    }

    
    public void moveToBottom() {
        while(moveDown() == true);
    }
    
    public boolean moveDown() {
        int col, row;
        boolean canMove = true;
        for(Rectangle r : rectangles) {
            if(r != null) {
                col = GridPane.getColumnIndex(r);
                row = GridPane.getRowIndex(r);
                if(row == 19) { 
                    canMove = false;
                } else {
                    Rectangle next = getFromGrid(col, row + 1);
                    if(next != null && isNotR(next) == true) {
                        canMove = false;
                    }
                }
            }
        }
        if(canMove) {
            for(Rectangle r : rectangles) {
                GridPane.setRowIndex(r, GridPane.getRowIndex(r) + 1);
            }
            pivotY++;
        } else {
            //Check to see if game over
            //Check to see if row can be cleared
        }
        return canMove;
    }

   

    public void moveLeft() {
        int col, row;
        boolean canMove = true;
        for (Rectangle r : rectangles) {
            if (r != null) {
                col = GridPane.getColumnIndex(r);
                row = GridPane.getRowIndex(r);
                if(col == 0) { //If a rectangle is at leftmost index
                    canMove = false;
                } else {
                    Rectangle next = getFromGrid(col - 1, row);
                    if(next != null && isNotR(next) == true) {
                        canMove = false;
                    }
                }
            }
        }
        if (canMove) {
            for(Rectangle r : rectangles) {
                GridPane.setColumnIndex(r, GridPane.getColumnIndex(r) - 1);
            }
            pivotX--;
        }
    }

    
    public void moveRight() {
        int col, row;
        boolean canMove = true;
        for (Rectangle r : rectangles) {
            if (r != null) {
                col = GridPane.getColumnIndex(r);
                row = GridPane.getRowIndex(r);
                if(col == 9) { //If a rectangle is at rightmost index
                    canMove = false;
                } else {
                    Rectangle next = getFromGrid(col + 1, row);
                    if(next != null && isNotR(next) == true) {
                        canMove = false;
                    }
                }
            }
        }
        if (canMove) {
            for(Rectangle r : rectangles) {
                GridPane.setColumnIndex(r, GridPane.getColumnIndex(r) + 1);
            }
            pivotX++;
        }
    }
    

    public Rectangle addRectangle(int x, int y) {
        Rectangle r = new Rectangle(30, 30);
        r.setFill(color);
        grid.add(r, x, y);
        return r;
    }

    public void removeRectangle(Rectangle r) {
        grid.getChildren().remove(r);
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
