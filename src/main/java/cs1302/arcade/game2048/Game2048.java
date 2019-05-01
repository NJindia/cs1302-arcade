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
import cs1302.arcade.ArcadeApp;
public class Game2048 {

    /** 4x4 array to store tiles and their positions */
    Tile[][] tiles = new Tile[4][4];

    Timeline tlRight = new Timeline();
    Timeline tlLeft = new Timeline();
    Timeline tlUp = new Timeline();
    Timeline tlDown = new Timeline();

    Text hiScore = new Text();
    Text score = new Text();
    Pane tileGroup = new Pane(); //Stores the tiles
    VBox vbox;
    int points = 0;  //IF 2 tiles merge, add the merged tile's value to the score

    public Scene getGameScene () {
        updateScore(0);
        score.setFont(new Font(20));
        hiScore.setFont(new Font(20));
        HBox scores = new HBox(score, hiScore);

        Button b = new Button("New Game");
        b.setOnAction(e -> newGame());
        Button b2 = new Button("Back to Games List");
        b2.setOnAction(e -> mainMenu());
       
        HBox buttons = new HBox(b, b2);
        
        scores.setSpacing(30);
        BackgroundSize size = new BackgroundSize(460, 460, false, false, false, false);
        Image image = new Image("file:src/main/resources/TileBackground.png");
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT, null, size);
        tileGroup.setBackground(new Background(background));
        tileGroup.setPrefSize(460, 460);
        newGame();
               
        vbox = new VBox(scores, buttons, tileGroup);
        
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
                updateTilesRight();
                tlRight.play();
            } else if (e.getCode() == KeyCode.LEFT &&
                tlRight.getStatus() == Animation.Status.STOPPED &&
                tlUp.getStatus() == Animation.Status.STOPPED &&
                tlDown.getStatus() == Animation.Status.STOPPED) {
                updateTilesLeft();
                tlLeft.play();
            } else if (e.getCode() == KeyCode.UP && 
                tlLeft.getStatus() == Animation.Status.STOPPED &&
                tlRight.getStatus() == Animation.Status.STOPPED &&
                tlDown.getStatus() == Animation.Status.STOPPED) {
                updateTilesUp();
                tlUp.play();
            } else if (e.getCode() == KeyCode.DOWN && 
                tlLeft.getStatus() == Animation.Status.STOPPED &&
                tlUp.getStatus() == Animation.Status.STOPPED &&
                tlRight.getStatus() == Animation.Status.STOPPED) {
                updateTilesDown();
                tlDown.play();
            } //if
        };
    } //createKeyHandler

    private void newGame() {
        tileGroup.getChildren().clear();
        updateScore(0);
        tiles = new Tile[4][4];
        addNewTile();
        addNewTile();
    }

    private void mainMenu() {
        ArcadeApp app = new ArcadeApp();
        app.mainMenu();
    }
    
    public void resetMoved() {
        for(Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null) {
                t.moved = false;
                t.merge = false;
            }
        }
    }

    private static int indexToCoordinate(int index) {
        return 12+(112*index);
    }
    
    private void addNewTile() {
        boolean added = false;
        boolean fullBoard = true;
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
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(tiles[i][j] == null) {
                    fullBoard = false;
                }
            }
        }
        if(fullBoard == true) {
            System.out.println("Game Over");
        } //if
    } //addNewTile

    private void remove() {
        ArrayList<Tile> toRemove = new ArrayList<>();
        for(Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t.remove == true) {
                toRemove.add(t);
            }
        }
        for(Tile t : toRemove) {
            tileGroup.getChildren().remove(t);
        }
    }

    public void merge() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(tiles[i][j] != null && tiles[i][j].merge == true) {
                    tiles[i][j].merge();
                    updateScore(tiles[i][j].getValue());
                }
            }
        }
    }

    public void updateScore(int points) {
        if(points == 0) {
            this.points = 0;
        } else {
            this.points += points;
        }
        String text = "Score: " + this.points;
        score.setText(text);
    }
    
    private void moveRight() {
        boolean moving = false;
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && t.moved == false) {
                moving = true;
                if((t.getX() + 12) <= indexToCoordinate(t.xIndex)) {
                    t.setX(t.getX() + 12);
                } else {
                    t.moved = true;
                    t.setX(indexToCoordinate(t.xIndex));
                }
            } //if  
        } //for
        if(moving == false) { //if all tiles are done moving
            tlRight.stop();
            remove();
            merge();
            resetMoved();
            addNewTile();
        }
    } //moveRight
    
    private void updateTilesRight() {
        for(int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if(tiles[i][j] != null) {
                    int k = j;
                    while(k!=3 && tiles[i][k+1] == null) {
                        tiles[i][k+1] = tiles[i][k];
                        tiles[i][k+1].xIndex = k+1;
                        tiles[i][k] = null;
                        k++;
                    } //while
                    if(k!=3 && tiles[i][k+1].canMerge(tiles[i][k])) {
                        tiles[i][k+1].merge = true;
                        tiles[i][k].remove = true;
                        tiles[i][k].xIndex = k+1;
                        tiles[i][k] = null;
                    }
                } //if
            } //for
        } //for
    }
    //TODO
       
    private void moveLeft() {
        boolean moving = false;
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && t.moved == false) {
                moving = true;
                if((t.getX() - 12) >= indexToCoordinate(t.xIndex)) {
                    t.setX(t.getX() - 12);
                } else {
                    t.moved = true;
                    t.setX(indexToCoordinate(t.xIndex));
                }
            } //if  
        } //for
        if(moving == false) { //if all tiles are done moving
            tlLeft.stop();
            remove();
            merge();
            resetMoved();
            addNewTile();
        }
    } //moveLeft
    
    private void updateTilesLeft() {
        for(int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if(tiles[i][j] != null) {
                    int k = j;
                    while(k != 0 && tiles[i][k-1] == null) {
                        tiles[i][k-1] = tiles[i][k];
                        tiles[i][k-1].xIndex = k - 1;
                        tiles[i][k] = null;
                        k--;
                    } //while
                    if(k != 0 && tiles[i][k-1].canMerge(tiles[i][k])) {
                        tiles[i][k-1].merge = true;
                        tiles[i][k].remove = true;
                        tiles[i][k].xIndex = k-1;
                        tiles[i][k] = null;
                    }
                } //if
            }
        }
    }

    private void moveDown(){
        boolean moving = false;
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && t.moved == false) {
                moving = true;
                if((t.getY() + 12) <= indexToCoordinate(t.yIndex)) {
                    t.setY(t.getY() + 12);
                } else {
                    t.moved = true;
                    t.setY(indexToCoordinate(t.yIndex));
                } //if
            } //if  
        } //for
        if(moving == false) { //if all tiles are done moving
            tlDown.stop();
            remove();
            merge();
            resetMoved();
            addNewTile();
        }
    } //moveDown
    
    private void updateTilesDown() {
        for(int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if(tiles[i][j] != null) {
                    int k = i;
                    while(k != 3 && tiles[k + 1][j] == null) {
                        tiles[k + 1][j] = tiles[k][j];
                        tiles[k+1][j].yIndex = k+1;
                        tiles[k][j] = null;
                        k++;
                    } //while
                    if(k != 3 && tiles[k+1][j].canMerge(tiles[k][j])) {
                        tiles[k+1][j].merge = true;
                        tiles[k][j].remove = true;
                        tiles[k][j].yIndex = k+1;
                        tiles[k][j] = null;
                    }
                } //if
            }
        }
    }

    private void moveUp() {
        boolean moving = false;
        for (Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null && t.moved == false) {
                moving = true;
                if((t.getY() - 12) >= indexToCoordinate(t.yIndex)) {
                    t.setY(t.getY() - 12);
                } else {
                    t.moved = true;
                    t.setY(indexToCoordinate(t.yIndex));
                }
            } //if  
        } //for
        if(moving == false) {
            tlUp.stop();
            merge();
            remove();
            resetMoved();
            addNewTile();
        }
    } //moveUp
    
    private void updateTilesUp() {
        for(int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if(tiles[i][j] != null) {
                    int k = i;
                    while(k != 0 && tiles[k - 1][j] == null) {
                        tiles[k - 1][j] = tiles[k][j];
                        tiles[k-1][j].yIndex = k - 1;
                        tiles[k][j] = null;
                        k--;
                    } //while
                    if(k != 0 && tiles[k-1][j].canMerge(tiles[k][j])) {
                        tiles[k-1][j].merge = true;
                        tiles[k][j].remove = true;
                        tiles[k][j].yIndex = k-1;
                        tiles[k][j] = null;
                    }
                } //if
            }
        }
    }
   
    private void setNewTileXY(int x, int y, Tile t) {
        int xCoordinate = 12 + (112*x);
        int yCoordinate = 12 + (112*y);
        t.setX(xCoordinate);
        t.setY(yCoordinate);
        t.xIndex = x;
        t.yIndex = y;
    }
                
}
