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
    private Scene scene;
    private GridPane grid = new GridPane();
    private Text score = new Text();
    private Text level = new Text();
    private Timeline tl = new Timeline();
    private Shape currShape;
    private boolean gameOver = false;
    private int points = 0;
       
    public Scene getGameScene(ArcadeApp a) {
        app = a;
        updateScore();
        score.setFont(new Font(20));
        level.setFont(new Font(20));
        HBox scores = new HBox(score, level);
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

        VBox vbox = new VBox(scores, buttons, grid);
        scene = new Scene(vbox);
        grid.requestFocus();

        newGame();        
        grid.setOnKeyPressed(createKeyHandler());

        return scene;        
    }

    private void addPoints(int lines) {
         if(lines == 1)
         {
             points += 40;
         }
         else if(lines == 2)
         {
             points += 100;
         }
         else if(lines==3)
         {
             points += 300;
         }
         else if(lines == 4)
         {
             points +=1200;
         }
         updateScore();
         updateLevel();
    }

    private void updateLevel() {
        if(points >= 200) {
            setTimeline(3);
            tl.play();
            level.setText("Level: 3");
        } else if (points >= 80) {
            setTimeline(2);
            tl.play();
            level.setText("Level: 2");
        }
    }

    private void updateScore() {
        String text = "Score: " + points;
        score.setText(text);
    }
    
    private void newGame(){
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
        newGrid();
        gameOver = false;
        points = 0;
        updateScore();
        level.setText("Level: 1");
        newShape();
        setTimeline(1);
        tl.play();
    }

    private void newGrid(){
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
        switch(shape) {
        case "Square":
            currShape = new Square(grid);
            break;
        case "L":
            currShape = new LShape(grid);
            break;
        case "T":
            currShape = new TShape(grid);
            break;
        case "J":
            currShape = new JShape(grid);
            break;
        case "Z":
            currShape = new ZShape(grid);
            break;
        case "S":
            currShape = new SShape(grid);
            break;
        case "I":
            currShape = new IShape(grid);
            break;
        default:
            currShape = new Square(grid);
            break;
        }       
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
        tl.stop();
        EventHandler<ActionEvent> handler = e -> {
            if(currShape.moveDown() == false) {
                clearLines();
                newShape();
            } //if
        };        
        KeyFrame k;
        switch(level) {
        case 1:
            k = new KeyFrame(Duration.millis(800), handler);
            break;
        case 2:
            k = new KeyFrame(Duration.millis(500), handler);
            break;
        case 3:
            k = new KeyFrame(Duration.millis(200), handler);
            break;
        default:
            k = new KeyFrame(Duration.millis(1000), handler);
        }
        tl.getKeyFrames().clear();
        tl.getKeyFrames().add(k);
        tl.setCycleCount(Timeline.INDEFINITE);
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
                    for (int k = y; k > 0; k--) {
                        Rectangle top = getFromGrid(x, k - 1);
                        if(top != null) {
                            GridPane.setRowIndex(top, k);
                        }
                    }
                }
            }
        }
        addPoints(rowsCleared);
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
