package cs1302.arcade.gameTetris.shapes;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

/** 
 * Represents an abstract class for all {@code Shape} objects.
 */
public abstract class Shape{
    public int angle; //can be 0, 90, 180, or 270
    public int pivotX, pivotY; //XY positions of pivot point
    public Color color;
    public GridPane grid;
    public Rectangle r1, r2, r3, r4;
    public Rectangle[] rectangles = new Rectangle[4];

    /**
     * Initializes values for {@code Shape} objects.
     * @param y the y value to set {@code pivotY} to
     * @param g the {@code GridPane} that stores the {@code Rectangle} objects
     * @param c the desired {@code Color} for the shape
     */
    public Shape(int y, GridPane g, Color c) {
        angle = 0; //Shapes always start with one orientation
        pivotY = y;
        color = c;
        grid = g;
    }
    
    /**
     * Returns a {@code Rectangle} object at a specified column and row index in {@code grid}.
     * @throws IndexOutOfBoundsException if col or row are out of grid's possible indexes
     * @param col the specified column of {@code grid}
     * @param row the specified row of {@code grid}
     * @return the {@code Rectangle} object at the specified column and row, null if none exists
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

    /**
     * Checks to see if the specified {@code Rectangle} is r1, r2, r3, or r4. 
     * @param r the specified {@code Rectangle}
     * @return true if the specified {@code Rectangle} is not r1, r2, r3, or r4, false otherwise
     */
    public boolean isNotR(Rectangle r) {
        if(r != r1 && r != r2 && r != r3 && r != r4) {
            return true;
        }
        return false;
    }

    /**
     * Reassigns r1, r2, r3, and r4 to their respective positions in the rectangles array.
     */
    public void reassignRectangles() {
        rectangles[0] = r1;
        rectangles[1] = r2;
        rectangles[2] = r3;
        rectangles[3] = r4;
    }

    /**
     * Instantly moves the shape as far down as possible.
     */
    public void moveToBottom() {
        while(moveDown() == true);
    }

    /**
     * Moves the shape down by 1 row.
     * @return true if the shape can move down, false otherwise
     */
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
        }
        return canMove;
    }

   
    /**
     * Moves the shape left by 1 column.
     */
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

    /**
     * Moves the shape right by 1 column.
     */
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
    
    /** 
     * Adds a {@code Rectangle} at the specified row and column of {@code grid}.
     * @param col the specified column to add the {@code Rectangle} to
     * @param row the specified row to add the {@code Rectangle} to
     * @return the {@code Rectangle} that was added
     */
    public Rectangle addRectangle(int col, int row) {
        Rectangle r = new Rectangle(30, 30);
        r.setFill(color);
        grid.add(r, col, row);
        return r;
    }

    /**
     * Removes a specified {@code Rectangle} from {@code grid}.
     * @param r the {@code Rectangle} that is to be removed
     */
    public void removeRectangle(Rectangle r) {
        grid.getChildren().remove(r);
    }

    /**
     * Calls the appropriate rotate methods depending on the shape's current angle.
     */
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

    /**
     * Rotates the Shape from 270 degrees back to the initial 0 degrees.
     */
    public abstract void rotateTo0();

    /**
     * Rotates the Shape from 0 to 90 degrees.
     */
    public abstract void rotateTo90();

    /**
     * Rotates the Shape from 90 to 180 degrees.
     */
    public abstract void rotateTo180();

    /**
     * Rotates the Shape from 180 to 270 degrees.
     */
    public abstract void rotateTo270();
}
