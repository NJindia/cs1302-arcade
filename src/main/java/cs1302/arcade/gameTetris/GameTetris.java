package cs1302.arcade.gameTetris;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import java.util.Arrays;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.*;
import cs1302.arcade.gameTetris.shapes.*;
import cs1302.arcade.ArcadeApp;


public class GameTetris{
    private ArcadeApp app;
    Scene scene;
    Rectangle[][] board = new Rectangle[24][10];
    GridPane grid = new GridPane();
    private Text score = new Text();
    Timeline tl = new Timeline();
    Shape currShape;
    private boolean gameOver = false;
    private int points = 0;
    int lines = 0;
    int level = 1;

    
    public Scene getGameScene(ArcadeApp a) {
        //updateScore(0);
        app = a;
        updateScore(0);
        score.setFont(new Font(20));
        HBox scores = new HBox(score);
        scores.setSpacing(30);

        //Creates and sets behavior of buttons
        Button b = new Button("New Game") {
                public void requestFocus() { } //Prevents b from taking focus
            };
        b.setOnAction(e -> newGame());
        Button b2 = new Button("Back to Games List") {
                public void requestFocus() { } //Prevents b2 from taking focus
            };
        b2.setOnAction(e -> mainMenu());
        HBox buttons = new HBox(b, b2);

/*        grid.setOnKeyPressed(createKeyHandler());
        setTimeline(1);
        
        grid.setPrefSize(300, 600);
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        
        //adds cols and rows to grid
        final int numCols = 10;
        final int numRows = 20;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            grid.getRowConstraints().add(rowConst);         
        }
        grid.setGridLinesVisible(true);
*/      newGrid();   
        VBox vbox = new VBox(scores, buttons, grid);
        scene = new Scene(vbox);
        newShape();
        grid.requestFocus();
        return scene;        
    }

    private void setLinesCleared(int x)
        {
            System.out.println("hi12345");
            lines=x;
        }

    private void updateScore(int lines) {
        /*if(points == 0) {
             this.points = 0;
         }
        */
         if(lines == 1)
         {
             this.points += 40;
         }
         else if(lines == 2)
         {
             this.points += 100;
         }
         else if(lines==3)
         {
             this.points += 300;
         }
         else if(lines == 4)
         {
             this.points +=1200;
         }
         
         String text = "Score: " + this.points;
         score.setText(text);
    }
    
    private void newGame(){
        //    grid.getChildren().clear();
        for(int row = 0; row<20; row++)
        {
            for(int col = 0; col<10; col++)
            {
                if(getFromGrid(col, row) != null)
                {
                    grid.getChildren().remove(getFromGrid(col, row));
                }
        
            }
        }
        gameOver = false;
        newShape();
        newGrid();
        //grid.setGridLinesVisible(true);
    }

    private void newGrid(){
        grid.setOnKeyPressed(createKeyHandler());
        setTimeline(1);
        
        grid.setPrefSize(300, 600);
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        
        //adds cols and rows to grid
        final int numCols = 10;
        final int numRows = 20;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            grid.getRowConstraints().add(rowConst);         
        }
        grid.setGridLinesVisible(true);
    }



    private void newShape() {
        String[] shapes = {"Square", "L", "J", "S", "Z", "I", "T"};
        String shape = shapes[(int)(Math.random() * 7)];
        Shape s;
        switch(shape) {
        case "Square":
            s = new Square(grid);
            break;
        case "L":
            s = new LShape(grid);
            break;
        case "T":
            s = new TShape(grid);
            break;
        case "J":
            s = new JShape(grid);
            break;
        case "Z":
            s = new ZShape(grid);
            break;
        case "S":
            s = new SShape(grid);
            break;
        case "I":
            s = new IShape(grid);
            break;
        default:
            s = new Square(grid);
            break;
      }
        currShape = s;
        tl.play();
    }

    private EventHandler<? super KeyEvent> createKeyHandler() {
        return e -> {
            if(gameOver == false){
                if (e.getCode() == KeyCode.RIGHT) {
                    currShape.moveRight();
                } else if (e.getCode() == KeyCode.LEFT) {
                    currShape.moveLeft();
                } else if (e.getCode() == KeyCode.UP) {
                    currShape.rotate();
                } else if (e.getCode() == KeyCode.DOWN) {
                    currShape.moveToBottom();
                } //if
            } //if
        }; //return
    } //createKeyHandler

    private void setTimeline(int level) {
        EventHandler<ActionEvent> handler = e -> {
            if(currShape.moveDown() == false) {
                clearLines();
                newShape();
            } //if
        };        
        KeyFrame k;
        switch(level) {
        case 1:
            k = new KeyFrame(Duration.millis(400), handler);
            break;
        case 2:
            k = new KeyFrame(Duration.millis(666), handler);
            break;
        case 3:
            k = new KeyFrame(Duration.millis(333), handler);
            break;
        default:
            k = new KeyFrame(Duration.millis(1000), handler);
        }
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.getKeyFrames().add(k);
    } //setTimeline

    private void clearLines() {
        int rowsCleared = 0;
        for(int y = 0; y < 20; y++) {
            boolean isFull = true;
            for (int x = 0; x < 10; x++) {
                if(getFromGrid(x, y) == null) {
                    isFull = false;
                } //if
            } //for
            if(isFull) {
                rowsCleared++;
                for (int x = 0; x < 10; x++) {
                    Rectangle rect = getFromGrid(x, y);
                    grid.getChildren().remove(rect);
                    Rectangle top = getFromGrid(x, y-1);
                    if(top != null) {
                        
//                        System.out.println("yo " + top);
//       grid.addRow(y, top);
                        // grid.getChildren().remove( x, y-1);
                        //grid.add(top, x, y);
                        //          grid.setRowIndex(top, x);
                    }
                    
                }
                //clear row and add points
            }
        }
        setLinesCleared(rowsCleared);
        updateScore(rowsCleared);
    }
     /** Changes the scene to that of the main menu. */
    private void mainMenu() {
        app.stage.setScene(app.mainMenu());
    } //mainMenu

        

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
    
    
}
