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
import javafx.animation.Animation;
import javafx.scene.image.*;

public class Game2048 {

    private enum Direction {LEFT, RIGHT, UP, DOWN};
    Direction direction;
    
    private final double xMin = 12; //12px buffer
    private final double xMax = 360; //460 - Size of Tile (100px)
    private final double yMin = 12; //12px buffer
    private final double yMax = 360; //460 - Size of Tile (100px)
    
    /** 4x4 array to store tiles and their positions */
    Tile[][] tiles = new Tile[4][4];

    Timeline tlRight = new Timeline();
    Timeline tlLeft = new Timeline();
    Timeline tlUp = new Timeline();
    Timeline tlDown = new Timeline();
    
    Pane tileGroup = new Pane(); //Stores the tiles
    VBox vbox;
    int score = 0;
    //IF 2 tiles merge, add the merged tile's value to the score

    //xboolean moving = false;

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
        tileGroup.setPrefSize(460, 460);
        tileGroup.setPadding(new Insets(12));
        addNewTile();
        addNewTile();
               
        vbox = new VBox(score, /*buttons,*/ tileGroup);
        
        Scene scene = new Scene(vbox, 460, 640);

        EventHandler<ActionEvent> handlerRight = event -> moveRight();
        EventHandler<ActionEvent> handlerLeft = event -> moveLeft();
        EventHandler<ActionEvent> handlerDown = event -> moveDown();
        EventHandler<ActionEvent> handlerUp = event -> moveUp();
        KeyFrame keyframeRight = new KeyFrame(Duration.millis(1000/60), handlerRight);
        KeyFrame keyframeLeft = new KeyFrame(Duration.millis(1000/60), handlerLeft);
        KeyFrame keyframeUp = new KeyFrame(Duration.millis(1000/60), handlerUp);
        KeyFrame keyframeDown = new KeyFrame(Duration.millis(1000/60), handlerDown);
        tlRight.setCycleCount(Timeline.INDEFINITE);
        tlLeft.setCycleCount(Timeline.INDEFINITE);
        tlUp.setCycleCount(Timeline.INDEFINITE);
        tlDown.setCycleCount(Timeline.INDEFINITE);
        tlRight.getKeyFrames().add(keyframeRight);
        tlLeft.getKeyFrames().add(keyframeLeft);
        tlUp.getKeyFrames().add(keyframeUp);
        tlDown.getKeyFrames().add(keyframeDown);
        
        tileGroup.setOnKeyPressed(createKeyHandler());
        tileGroup.requestFocus();
        
        return scene;
    }

    private EventHandler<? super KeyEvent> createKeyHandler() {
        return e -> {
            if (e.getCode() == KeyCode.RIGHT &&
                tlLeft.getStatus() == Animation.Status.STOPPED &&
                tlUp.getStatus() == Animation.Status.STOPPED &&
                tlDown.getStatus() == Animation.Status.STOPPED) {
                //direction = Direction.RIGHT;
                tlRight.play();
            } else if (e.getCode() == KeyCode.LEFT &&
                tlRight.getStatus() == Animation.Status.STOPPED &&
                tlUp.getStatus() == Animation.Status.STOPPED &&
                tlDown.getStatus() == Animation.Status.STOPPED) {
                //direction = Direction.LEFT;
                tlLeft.play();
            } else if (e.getCode() == KeyCode.UP && 
                tlLeft.getStatus() == Animation.Status.STOPPED &&
                tlRight.getStatus() == Animation.Status.STOPPED &&
                tlDown.getStatus() == Animation.Status.STOPPED) {
                //direction = Direction.UP;
                tlUp.play();
            } else if (e.getCode() == KeyCode.DOWN && 
                tlLeft.getStatus() == Animation.Status.STOPPED &&
                tlUp.getStatus() == Animation.Status.STOPPED &&
                tlRight.getStatus() == Animation.Status.STOPPED) {
                //direction = Direction.DOWN;
                tlDown.play();
            } //if
        };
    } //createKeyHandler
      
    private void moveUp() {
        boolean moving = false;
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && t.moved == false) {
                moving = true;
                if((t.getY() - 12) >= yMin) {
                    for(Node node2 : tileGroup.getChildren()) {
                        Tile tile = (Tile)node2;         
                        if(t != tile && t.getBoundsInLocal().intersects(tile.getBoundsInLocal())
                           && tile.moved == true) {
                            t.moved = true;
                            t.setY(t.getY() + 12);
                        } //if
                    } //for
                } else {
                    t.moved = true;
                }
                if(t.moved == false) {
                    t.setY(t.getY() - 12);
                } //if
            } //if  
        } //for
        if(moving == false) {
            tlUp.stop();
            addNewTile();
            resetMoved();
        }
    } //moveUp

    private void moveDown() {
        boolean moving = false;
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && t.moved == false) {
                moving = true;
                if((t.getY() + 12) <= yMax) {
                    for(Node node2 : tileGroup.getChildren()) {
                        Tile tile = (Tile)node2;         
                        if(t != tile && t.getBoundsInLocal().intersects(tile.getBoundsInLocal())
                           && tile.moved == true) {
                            t.moved = true;
                            t.setY(t.getY() - 12);
                        } //if
                    } //for
                } else {
                    t.moved = true;
                }
                if(t.moved == false) {
                    t.setY(t.getY() + 12);
                } //if
            } //if  
        } //for
        if(moving == false) {
            tlDown.stop();
            addNewTile();
            resetMoved();
        }
    } //moveDown

    private void moveLeft() {
        boolean moving = false;
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && t.moved == false) {
                moving = true;
                if((t.getX() - 12) >= xMin) {
                    for(Node node2 : tileGroup.getChildren()) {
                        Tile tile = (Tile)node2;         
                        if(t != tile && t.getBoundsInLocal().intersects(tile.getBoundsInLocal())
                           && tile.moved == true) {
                            t.moved = true;
                                t.setX(t.getX() - 12);
                        } //if
                    } //for
                } else {
                    t.moved = true;
                }
                if(t.moved == false) {
                    t.setX(t.getX() - 12);
                } //if
            } //if  
        } //for
        if(moving == false) {
            tlLeft.stop();
            addNewTile();
            resetMoved();
        }
    } //moveLeft
    
    public void resetMoved() {
        for(Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null) {
                t.moved = false;
            }
        }
    }

    private void moveRight() {
        if(tlLeft.getStatus() == Animation.Status.STOPPED) {
            boolean moving = false;
            for (Node node : tileGroup.getChildren()) {
                Tile t = (Tile)node;
                if(t != null && t.moved == false) {
                    moving = true;
                    if((t.getX() + 12) < xMax) {
                        for(Node node2 : tileGroup.getChildren()) {
                            Tile tile = (Tile)node2;
                            if(t != tile && t.getBoundsInLocal().intersects(tile.getBoundsInLocal())
                               && tile.moved == true) {
                                t.moved = true;
                                t.setX(t.getX() - 12);
                            } //if
                        } //for
                    } else {
                        t.moved = true;
                    }
                    if(t.moved == false) {
                        t.setX(t.getX() + 12);
                    } //if
                } //if  
            } //for
            if(moving == false) {
                updateTilesRight();
                tlRight.stop();
                addNewTile();
                resetMoved();
            }
        }
    } //moveRight
    
    private void addNewTile() {
        boolean added = false;
        boolean fullBoard = true;
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(tiles[i][j] == null) {
                    fullBoard = false;
                }
            }
        }
        if(fullBoard == false) {
            Tile t = new Tile();
            tileGroup.getChildren().add(t);
            int xIndex, yIndex;
            while(added == false) {
                xIndex = (int)(Math.random() * 4);
                yIndex = (int)(Math.random() * 4);
                if(tiles[yIndex][xIndex] == null) {
                    tiles[yIndex][xIndex] = t;
                    setNewTileXY(xIndex, yIndex, t);
                    added = true;
                } //if
            } //while
        } else {
            System.out.println("Game Over");
        }
    }

    private void updateTilesRight() {
        for(int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if(tiles[i][j] != null) {
                    while(tiles[i][j+1] == null) {
                        tiles[i][j+1] = tiles[i][j];
                        tiles[i][j] = null;
                    } //while
                    if(tiles[i][j+1].merge(tiles[i][j])) {
                        tileGroup.getChildren().remove(tiles[i][j]);
                        tiles[i][j] = null;
                    }
                } //if
            }
        }
    }

    private void updateTilesLeft() {
        for(int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if(tiles[i][j] != null) {
                    while(tiles[i][j-1] == null) {
                        tiles[i][j-1] = tiles[i][j];
                        tiles[i][j] = null;
                    } //while
                    if(tiles[i][j-1].merge(tiles[i][j])) {
                        tileGroup.getChildren().remove(tiles[i][j]);
                        tiles[i][j] = null;
                    }
                } //if
            }
        }
    }

    private void setNewTileXY(int x, int y, Tile t) {
        int xCoordinate = 12;
        int yCoordinate = 12;
        t.setX(xCoordinate);
        t.setY(yCoordinate);
    }
                
}
