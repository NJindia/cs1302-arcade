package cs1302.arcade.game2048;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import java.lang.Math;
import java.util.*;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import javafx.scene.layout.Pane;

import javafx.scene.image.*;

public class Game2048 {

    private enum Direction {LEFT, RIGHT, UP, DOWN};
    Direction direction;
    
    private final double xMin = 12; //12px buffer
    private final double xMax = 380; //480 - Size of Tile (100px)
    private final double yMin = 12; //12px buffer
    private final double yMax = 380; //480 - Size of Tile (100px)
    
    /** 4x4 array to store tiles and their positions */
    Tile[][] tiles = new Tile[4][4];

    Timeline timeline = new Timeline();
    
    Pane tileGroup = new Pane(); //Stores the tiles
    VBox vbox;
    int score = 0;
    //IF 2 tiles merge, add the merged tile's value to the score
   
    public Scene getGameScene () {
        Text t = new Text("High Score: 10");
        Text t2 = new Text("Score: " + score);
        t.setFont(new Font(20));
        t2.setFont(new Font(20));
        HBox score = new HBox(t, t2);

        Button b = new Button("New Game");
        Button b2 = new Button("Back to Games List");
        HBox buttons = new HBox(b, b2);
        
        score.setSpacing(30);
        tileGroup.setStyle("-fx-background-color: black;");
        tileGroup.setPrefSize(480, 480);
        tileGroup.setPadding(new Insets(12));
        Tile t3 = new Tile();
        t3.setX(12);
        Tile t4 = new Tile();
        t4.setX(124);
        tileGroup.getChildren().addAll(t3, t4);
        
        vbox = new VBox(score, /*buttons,*/ tileGroup);
        
        Scene scene = new Scene(vbox, 480, 640);

        EventHandler<ActionEvent> handler = event -> move();
        KeyFrame keyframe = new KeyFrame(Duration.millis(1000/60), handler);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyframe);
        
        tileGroup.setOnKeyPressed(createKeyHandler());
        tileGroup.requestFocus();
        
        return scene;
    }

    private EventHandler<? super KeyEvent> createKeyHandler() {
        return e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                direction = Direction.RIGHT;
                timeline.play();
            } else if (e.getCode() == KeyCode.LEFT) {
                direction = Direction.LEFT;
                timeline.play();
            } else if (e.getCode() == KeyCode.UP) {
                direction = Direction.UP;
                timeline.play();
            } else if (e.getCode() == KeyCode.DOWN) {
                direction = Direction.DOWN;
                timeline.play();
            } //if
        };
    } //createKeyHandler
    
    private void move() {
        if(direction == Direction.UP) {
            moveUp();
        } else if (direction == Direction.DOWN) {
            moveDown();
        } else if (direction == Direction.RIGHT) {
            moveRight();
        } else if (direction == Direction.LEFT) {
            moveLeft();
        } //if
    } //move
    
    private void moveUp() {
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && (t.getY() - 12) >= yMin) {
                t.setY(t.getY() - 12);
            } //if
        } //for
    } //moveUp

    private void moveDown() {
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && (t.getY() + 12) <= yMax) {
                t.setY(t.getY() + 12);
            } //if
        } //for
    } //moveDown

    private void moveLeft() {
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && (t.getX() - 12) >= xMin) {
                t.setX(t.getX() - 12);
            } //if
        } //for
    } //moveLeft

    private void moveRight() {
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && (t.getX() + 12) <= xMax) {
                t.setX(t.getX() + 6);
            }
            for(Node node2 : tileGroup.getChildren()) {
                Tile tile = (Tile)node2;
                if(t != tile) {
                    if(t.getBoundsInParent().intersects(tile.getBoundsInParent())) {
                        if(tile.merge(t)) {
                            tileGroup.getChildren().remove(t);
                        } else {                            
                            t.setX(t.getX() - 12);
                        } //if
                    } //if
                } //if
            } //for
        } //for
    } //moveRight
    
    private boolean addNewTile() {
        boolean added = false;
        Tile t = new Tile();
        tileGroup.getChildren().add(t);
        //Position tile?
        return true;
    }
}
