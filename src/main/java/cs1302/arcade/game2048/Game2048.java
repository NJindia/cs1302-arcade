package cs1302.arcade.game2048;

import javafx.scene.text.Font;
import javafx.scene.*;
import javafx.scene.control.Control;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import java.lang.Math;
import java.util.*;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import javafx.event.*;


import javafx.scene.shape.Rectangle;

import javafx.scene.image.*;

public class Game2048 {

    private final double xMin = 0;
    private final double xMax = 640 - 100;
    private final double yMin = 0;
    private final double yMax = 480 - 100;
    
    /** 4x4 array to store tiles and their positions */
    Tile[][] tiles = new Tile[4][4];

    Group tileGroup = new Group(); //Stores the tiles
    VBox vbox;
    int score = 0;
    //IF 2 tiles merge, add the merged tile's value to the score
   
    public Scene getGameScene () {
        Text t = new Text("High Score: 10");
        Text t2 = new Text("Score: " + score);
        
        t.setFont(new Font(20));
        t2.setFont(new Font(20));
        
        HBox score = new HBox(t, t2);
        score.setSpacing(30);
        Tile t3 = new Tile();
        t3.setX(100);
        Tile t4 = new Tile();
        t4.setX(100);
        t4.setY(200);
  
        tiles[0][0] = t3;
        tileGroup.getChildren().addAll(t3, t4);
        
        vbox = new VBox(score, tileGroup);
        
        Scene scene = new Scene(vbox, 640, 480);
        tileGroup.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.RIGHT) {
                    
                    EventHandler<ActionEvent> handler = event -> {
                        Runnable r = () -> {
                            moveX(1);
                        };
                        Thread y = new Thread(r);
                        y.setDaemon(true);
                        y.start();
                    };
                    KeyFrame keyframe = new KeyFrame(Duration.millis(1000/60), handler);
                    Timeline timeline = new Timeline();
                    timeline.setCycleCount(480);
                    timeline.getKeyFrames().add(keyframe);
                    timeline.play();
                    
                    //moveX(1);
                }
            });
        tileGroup.requestFocus();
        
        return scene;
    }
    
    /** move columns left or right */
    public void moveCols (double value) {
        
    }
    /** move rows up or down */
    public void moveRows(double value) {

    }
    
    public void moveX(int direction) {
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            t.setX(t.getX() + 10);
            
        }
        /*for(Tile[] t1 : tiles) {
            for (Tile t : t1) {
                if (direction == 1) {
                    if(t != null) {
                    Runnable r = () -> {
                          t.setX(t.getX() + 10);
                          //moveRight(t);
                          };
                          Thread y = new Thread(r);
                          y.setDaemon(true);
                          y.start();
                          }
                }
                
            }
        }
        */
    }
/*
    public void moveRight(Tile t) {
        boolean collided = false;
        t.setX(t.getX() + 1);
        // if(collides with another Tile t1) {
        if(t.canMerge(t1)) { //need to make another tile
            //merge t into t1
        }
        collided = true;
        // } else if(t.getX() == last_possible_x_coordinate) {
        // collided = true;
        // }
    }
*/
    public void moveY(int direction) {
        //same as moveX but with Y
    }
    
    public boolean addNewTile() {
        boolean added = false;
        Tile t = new Tile();
        tileGroup.getChildren().add(t);
        //Position tile?
        return true;
    }
}
