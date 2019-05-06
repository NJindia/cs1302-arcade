package cs1302.arcade.gameTetris;


import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.*;

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
import cs1302.arcade.gameTetris.*;
public class GameTetris{
    boolean gameOver =false;
    Scene scene;
    Rectangle[][] board = new Rectangle[24][10];
    GridPane grid = new GridPane();
    private Text score = new Text();
<<<<<<< HEAD
    Timeline tl = new Timeline();
    Shape currShape;
    private boolean gameOver = false;
    
=======
    //Timelines for different movement directions
    private Timeline tlRight = new Timeline();
    private Timeline tlLeft = new Timeline();
    // private Timeline tlUp = new Timeline();
    private Timeline tlDown = new Timeline();
    //Rectangles are 20x20px
>>>>>>> some changes
    public Scene getGameScene () {
        //updateScore(0);
        score.setFont(new Font(20));
        HBox scores = new HBox(score);
        scores.setSpacing(30);

        //Creates and sets behavior of buttons
        Button b = new Button("New Game") {
                public void requestFocus() { } //Prevents b from taking focus
            };
        //b.setOnAction(e -> newGame());
        Button b2 = new Button("Back to Games List") {
                public void requestFocus() { } //Prevents b2 from taking focus
            };
        //b2.setOnAction(e -> mainMenu());
        HBox buttons = new HBox(b, b2);

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
        
        VBox vbox = new VBox(scores, buttons, grid);
        scene = new Scene(vbox);
        newShape();
        return scene;        
    }

    private void newShape() {
<<<<<<< HEAD
        Shape s = new Square(0, 0, board, grid);
        currShape = s;
        tl.play();
=======
//        Shape s = new Square(0, 0, board, grid);
        //      Shape s1 = new Square(5, 0, board, grid);
        // Shape s2 = new Square(8, 0, board, grid);
        



>>>>>>> some changes
    }
    /** Sets each of the 4 timelines to move the tiles at 60fps. */
    private void setTimelines() {
        EventHandler<ActionEvent> handlerRight = event -> moveRight();
        EventHandler<ActionEvent> handlerLeft = event -> moveLeft();
        EventHandler<ActionEvent> handlerDown = event -> moveDown();
        //    EventHandler<ActionEvent> handlerUp = event -> moveUp();

        //Calls the respective handlers at 60fps
        KeyFrame keyframeRight = new KeyFrame(Duration.millis(1000/60), handlerRight);
        KeyFrame keyframeLeft = new KeyFrame(Duration.millis(1000/60), handlerLeft);
        // KeyFrame keyframeUp = new KeyFrame(Duration.millis(1000/60), handlerUp);
        KeyFrame keyframeDown = new KeyFrame(Duration.millis(1000/60), handlerDown);

        tlRight.setCycleCount(Timeline.INDEFINITE);
        tlLeft.setCycleCount(Timeline.INDEFINITE);
        // tlUp.setCycleCount(Timeline.INDEFINITE);
        tlDown.setCycleCount(Timeline.INDEFINITE);

<<<<<<< HEAD
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
                newShape();
            } //if
        };        
        KeyFrame k;
        switch(level) {
        case 1:
            k = new KeyFrame(Duration.millis(1000), handler);
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
=======
        tlRight.getKeyFrames().add(keyframeRight);
        tlLeft.getKeyFrames().add(keyframeLeft);
        // tlUp.getKeyFrames().add(keyframeUp);
        tlDown.getKeyFrames().add(keyframeDown);
    } //setTimelines

>>>>>>> some changes
}
