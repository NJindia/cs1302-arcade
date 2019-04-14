package cs1302.arcade.game2048;

import javafx.scene.text.Font;
import javafx.scene.*;
import javafx.scene.control.Control;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import java.lang.Math;
import java.util.*;

public class Game2048 {

    private final double xMin = 0;
    private final double xMax = 640 - 100;
    private final double yMin = 0;
    private final double yMax = 480 - 100;
    
    /** 4x4 array to store tiles and their positions */
    Tile[][] tiles = new Tile[4][4];

    Group tileGroup; //Stores the tiles

    int score = 0;
    //IF 2 tiles merge, add the merged tile's value to the score
   
    public Scene getGameScene () {
        Text t = new Text("High Score: 10");
        Text t2 = new Text("Score: " + score);
        
        t.setFont(new Font(20));
        
        HBox score = new HBox(t, t2);
        score.setSpacing(30);
    
        Scene scene = new Scene(tileGroup, 640, 480);
        return scene;
    }
    
    /** move columns left or right */
    public void moveCols (double value) {
        
    }
    /** move rows up or down */
    public void moveRows(double value) {

    }
    
    public void moveX(int direction) {
        //Tile[] == rows
        for(Tile[] t1 : tiles) {
            for (Tile t : t1) {
                Runnable r = () -> {
                    moveToEdgeX(t, direction);
                };
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.start();
            }
        }
    }

    public void moveToEdgeX(Tile t, int direction) {
        boolean collided = false;
        switch(direction) {
        case 1:
            while(collided == false) {
                t.setX(t.getX() + 1);
                if(/*collides with another Tile t1*/) {
                    if(t.canMerge(t1)) {
                        //merge t into t1
                    }
                    collided = true;
                } else if(t.getX() == /*last_possible_x_coordinate*/) {
                    collided = true;
                }
            }
            break;
        case -1:
            //Same as case 1 but for the other way
            break;
        default:
            break;

    }
        
    public void moveY(int direction) {
        //same as moveX but with Y
    }

    public boolean addNewTile() {
        boolean added = false;
        Tile t = new Tile();
        tileGroup.getChildren().add(t);
        //Position tile?
    }

}
