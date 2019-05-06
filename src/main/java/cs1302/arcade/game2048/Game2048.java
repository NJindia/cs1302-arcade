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
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import cs1302.arcade.ArcadeApp;

public class Game2048 {
    private ArcadeApp app;
    
    /** 4x4 array to store tiles and their positions */
    private Tile[][] tiles = new Tile[4][4];
//    ArcadeApp app = new ArcadeApp();
    //Timelines for different movement directions
    private Timeline tlRight = new Timeline();
    private Timeline tlLeft = new Timeline();
    private Timeline tlUp = new Timeline();
    private Timeline tlDown = new Timeline();

    private Text score = new Text();
    private Pane tileGroup = new Pane(); //Stores the tiles
    private VBox vbox;

    private int points = 0;
    private boolean gameOver = false;
    
    /**
     * Creates the 2048 game scene.
     * @param a a reference to the original {@code ArcadeApp}
     * @return the scene for 2048
     */
    public Scene getGameScene (ArcadeApp a) {
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

        //Sets size and background of tileGroup
        BackgroundSize size = new BackgroundSize(460, 460, false, false, false, false);
        Image image = new Image("file:src/main/resources/TileBackground.png");
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT, null, size);
        tileGroup.setBackground(new Background(background));
        tileGroup.setPrefSize(460, 460);
        
        setTimelines();
        newGame();

        vbox = new VBox(scores, buttons, tileGroup);
        Scene scene = new Scene(vbox, 460, 500);
        tileGroup.setOnKeyPressed(createKeyHandler());
        tileGroup.requestFocus();
        return scene;
    } //getGameScene

    /** Sets each of the 4 timelines to move the tiles at 60fps. */
    private void setTimelines() {
        EventHandler<ActionEvent> handlerRight = event -> moveRight();
        EventHandler<ActionEvent> handlerLeft = event -> moveLeft();
        EventHandler<ActionEvent> handlerDown = event -> moveDown();
        EventHandler<ActionEvent> handlerUp = event -> moveUp();

        //Calls the respective handlers at 60fps
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
    } //setTimelines

    /**
     * Creates and returns a KeyEvent EventHandler that plays one of the timelines depending on what
     * arrow key the user presses.
     * @return an EventHandler that handles KeyEvents
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return e -> {
            if(gameOver == false){
                if (e.getCode() == KeyCode.RIGHT && timelinesStopped()) {
                    if(updateTilesRight()) { //If any tiles are moved/merged
                        tlRight.play();
                    } //if
                } else if (e.getCode() == KeyCode.LEFT && timelinesStopped()) {
                    if(updateTilesLeft()) { //If any tiles are moved/merged
                        tlLeft.play();
                    } //if
                } else if (e.getCode() == KeyCode.UP && timelinesStopped()) {
                    if(updateTilesUp()) { //If any tiles are moved/merged
                        tlUp.play();
                    } //if
                } else if (e.getCode() == KeyCode.DOWN && timelinesStopped()) {
                    if(updateTilesDown()) { //If any tiles are moved/merged
                        tlDown.play();
                    } //if
                } //if
            } //if
        }; //return
    } //createKeyHandler

    /**
     * Returns true if all 4 timelines are stopped.
     * @return true if all timelines are stopped, false otherwise
     */
    private boolean timelinesStopped() {
        if(tlRight.getStatus() == Animation.Status.STOPPED &&
           tlLeft.getStatus() == Animation.Status.STOPPED &&
           tlUp.getStatus() == Animation.Status.STOPPED &&
           tlRight.getStatus() == Animation.Status.STOPPED) {
            return true;
        } else {
            return false;
        } //if
    } //timelinesStopped
           
    /** Clears the board, sets score to 0, and adds 2 tiles to the board to start a new game. */
    private void newGame() {
        tileGroup.getChildren().clear();
        updateScore(0);
        tiles = new Tile[4][4];
        gameOver = false;
        addNewTile();
        addNewTile();
    } //newGame

    /** Changes the scene to that of the main menu. */
    private void mainMenu() {
        app.stage.setScene(app.mainMenu());
    } //mainMenu

    /** Resets the {@code moved} and {@code merge} property for all tiles on the board. */
    public void resetMoved() {
        for(Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t != null) {
                t.moved = false;
                t.merge = false;
            } //if
        } //for
    } //resetMoved

    /**
     * Converts a tile's position in a 4x4 2D array to actual coordinates.
     * @param index the specified index that is to be converted to coordinates
     * @return the coordinate translation of an index position
     */
    private static int indexToCoordinate(int index) {
        return 12+(112*index);
    } //indexToCoordinate

    /** 
     * Randomly adds a tile to one of the empty spaces on the board. The tile can either have a 
     * value of 2 or 4. If the board is full after adding a tile and no more moves can be made, 
     * the game ends.
     */
    private void addNewTile() {
        boolean added = false;
        boolean fullBoard = true;
        Tile t = new Tile();
        tileGroup.getChildren().add(t);
        int xIndex, yIndex;

        //Assign new tile to random empty space on board
        while(added == false) {
            xIndex = (int)(Math.random() * 4);
            yIndex = (int)(Math.random() * 4);
            if(tiles[yIndex][xIndex] == null) {
                tiles[yIndex][xIndex] = t;
                setNewTileXY(xIndex, yIndex, t);
                added = true;
            } //if
        } //while

        //Checks to see if board is full
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(tiles[i][j] == null) {
                    fullBoard = false;
                } //if
            } //for
        } //for
        
        //Ends the game if the board is full and no moves can be made
        if(fullBoard == true && canMove() == false) {
            gameOver();
        } //if
    } //addNewTile

    /** Ends the game and displays an {@code Alert} informing the user that the game is over. */
    private void gameOver() {
        gameOver = true;
        Alert alert = new Alert(AlertType.INFORMATION,
                                "GAME OVER. No more possible moves.\n"  +
                                "Final score: " + points);
        Runnable r = () -> alert.showAndWait().filter(response -> response == ButtonType.OK);
        Platform.runLater(r);
    } //gameOver

    /** Ends the game and displays an {@code Alert} informing the user that they won. */
    private void winner() {
        gameOver = true;
        Alert alert = new Alert(AlertType.INFORMATION,
                                "CONGRATS! You have reached 2048!\n" +
                                "Final score: " + points);
        Runnable r = () -> alert.showAndWait().filter(response -> response == ButtonType.OK);
        Platform.runLater(r);
    } //winner
    
    /** Removes all tiles with a true {@code remove} property from {@code tileGroup}. */
    private void remove() {
        ArrayList<Tile> toRemove = new ArrayList<>();
        for(Node node : tileGroup.getChildren()) {
            Tile t = (Tile)node;
            if(t.remove == true) {
                toRemove.add(t);
            } //if
        } //for
        for(Tile t : toRemove) {
            tileGroup.getChildren().remove(t);
        } //for
    } //remove

    /** Merges all tiles with a true {@code merge} property and updates the score. */
    private void merge() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(tiles[i][j] != null && tiles[i][j].merge == true) {
                    tiles[i][j].merge();
                    updateScore(tiles[i][j].getValue());
                } //if
            } //for
        } //for
    } //merge

    /** 
     * Adds the specified number of points to {@code score}.
     * @param points the specified number of points to add to the score, a value of 0
     * will reset the score and a value of 2048 will display a winner message
     */
    private void updateScore(int points) {
        if(points == 0) {
            this.points = 0;
        } else if (points == 2048) { //If player reaches 2048
            winner();
        } else {
            this.points += points;
        } //if
        String text = "Score: " + this.points;
        score.setText(text);
    } //updateScore

    /**
     * Moves all the tiles right 12px. If the tiles are done moving, stops the timeline,
     * adds a new tile to the board, and merges/removes tiles as necessary.
     */
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
                } //if
            } //if  
        } //for
        if(moving == false) { //if all tiles are done moving
            tlRight.stop();
            remove();
            merge();
            resetMoved();
            addNewTile();
        } //if
    } //moveRight

    /**
     * Updates the {@code tiles} 2D array to reflect all the tiles moving as far right as possible.
     * Also updates each tile's {@code remove} and {@code merge} properties where appropriate.
     * @return true if a tile is moved or merged
     */
    private boolean updateTilesRight() {
        boolean moved = false;
        for(int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if(tiles[i][j] != null) {
                    int k = j;
                    while(k!=3 && tiles[i][k+1] == null) {
                        tiles[i][k+1] = tiles[i][k];
                        tiles[i][k+1].xIndex = k+1;
                        moved = true; //Signifies that a tile has been moved
                        tiles[i][k] = null;
                        k++;
                    } //while
                    if(k!=3 && tiles[i][k+1].canMerge(tiles[i][k])) {
                        tiles[i][k+1].merge = true;
                        tiles[i][k].remove = true;
                        tiles[i][k].xIndex = k+1;
                        moved = true; //Signifies that a tile has been merged
                        tiles[i][k] = null;
                    } //if
                } //if
            } //for
        } //for
        return moved;
    } //updateTilesRight
    
    /**
     * Moves all the tiles left 12px. If the tiles are done moving, stops the timeline,
     * adds a new tile to the board, and merges/removes tiles as necessary.
     */
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
                } //if
            } //if  
        } //for
        if(moving == false) { //if all tiles are done moving
            tlLeft.stop();
            remove();
            merge();
            resetMoved();
            addNewTile();
        } //if
    } //moveLeft

    /**
     * Updates the {@code tiles} 2D array to reflect all the tiles moving as far left as possible.
     * Also updates each tile's {@code remove} and {@code merge} properties where appropriate.
     * @return true if a tile is moved or merged
     */
    private boolean updateTilesLeft() {
        boolean moved = false;
        for(int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if(tiles[i][j] != null) {
                    int k = j;
                    while(k != 0 && tiles[i][k-1] == null) {
                        tiles[i][k-1] = tiles[i][k];
                        tiles[i][k-1].xIndex = k - 1;
                        moved = true; //Signifies that a tile has been moved
                        tiles[i][k] = null;
                        k--;
                    } //while
                    if(k != 0 && tiles[i][k-1].canMerge(tiles[i][k])) {
                        tiles[i][k-1].merge = true;
                        tiles[i][k].remove = true;
                        tiles[i][k].xIndex = k-1;
                        moved = true; //Signifies that a tile has been merged
                        tiles[i][k] = null;
                    } //if
                } //if
            } //for
        } //for
        return moved;
    } //updateTilesLeft

    
    /**
     * Moves all the tiles down 12px. If the tiles are done moving, stops the timeline,
     * adds a new tile to the board, and merges/removes tiles as necessary.
     */
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
        } //if
    } //moveDown

    /**
     * Updates the {@code tiles} 2D array to reflect all the tiles moving as far down as possible.
     * Also updates each tile's {@code remove} and {@code merge} properties where appropriate.
     * @return true if a tile is moved or merged
     */
    private boolean updateTilesDown() {
        boolean moved = false;
        for(int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if(tiles[i][j] != null) {
                    int k = i;
                    while(k != 3 && tiles[k + 1][j] == null) {
                        tiles[k + 1][j] = tiles[k][j];
                        tiles[k+1][j].yIndex = k+1;
                        moved = true; //Signifies that a tile has been moved
                        tiles[k][j] = null;
                        k++;
                    } //while
                    if(k != 3 && tiles[k+1][j].canMerge(tiles[k][j])) {
                        tiles[k+1][j].merge = true;
                        tiles[k][j].remove = true;
                        tiles[k][j].yIndex = k+1;
                        moved = true; //Signifies that a tile has been merged
                        tiles[k][j] = null;
                    } //if
                } //if
            } //for
        } //for
        return moved;
    } //updateTilesDown
    
    /**
     * Moves all the tiles up 12px. If the tiles are done moving, stops the timeline,
     * adds a new tile to the board, and merges/removes tiles as necessary.
     */
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
                } //if
            } //if  
        } //for
        if(moving == false) {
            tlUp.stop();
            merge();
            remove();
            resetMoved();
            addNewTile();
        } //if
    } //moveUp

    /**
     * Updates the {@code tiles} 2D array to reflect all the tiles moving as far up as possible.
     * Also updates each tile's {@code remove} and {@code merge} properties where appropriate.
     * @return true if a tile is moved or merged
     */
    private boolean updateTilesUp() {
        boolean moved = false;
        for(int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if(tiles[i][j] != null) {
                    int k = i;
                    while(k != 0 && tiles[k - 1][j] == null) {
                        tiles[k - 1][j] = tiles[k][j];
                        tiles[k-1][j].yIndex = k - 1;
                        moved = true; //Signifies that a tile has been moved
                        tiles[k][j] = null;
                        k--;
                    } //while
                    if(k != 0 && tiles[k-1][j].canMerge(tiles[k][j])) {
                        tiles[k-1][j].merge = true;
                        tiles[k][j].remove = true;
                        tiles[k][j].yIndex = k-1;
                        moved = true; //Signifies that a tile has been merged
                        tiles[k][j] = null;
                    } //if
                } //if
            } //for
        } //for
        return moved;
    } //updateTilesUp

    /** 
     * Sets a specified Tile's X and Y coordinates. Should only be called on freshly made Tiles.
     * @param x the specified x coordinate to set {@code t} to
     * @param y the specified y coordinate to set {@code t} to
     * @param t the specified Tile to set the x and y coordinates of
     */
    private void setNewTileXY(int x, int y, Tile t) {
        int xCoordinate = 12 + (112*x);
        int yCoordinate = 12 + (112*y);
        t.setX(xCoordinate);
        t.setY(yCoordinate);
        t.xIndex = x;
        t.yIndex = y;
    } //setNewTileXY

    /**
     * Checks to see if tiles in a full board can merge in any direction.
     * @return false if there are no more possible moves, true otherwise
     */
    private boolean canMove() {
        boolean canMove = false;
        for(int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) { //Checks if moving right is possible
                if(tiles[i][j] != null && tiles[i][j+1].canMerge(tiles[i][j])) {
                    canMove = true;
                } //if
            } //for
            for (int k = 1; k < 4; k++) { //Checks if moving left is possible
                if(tiles[i][k] != null && tiles[i][k-1].canMerge(tiles[i][k])) {
                    canMove = true;
                } //if
            } //for
        } //for
        for(int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) { //Checks if moving down is possible
                if(tiles[i][j] != null && tiles[i+1][j].canMerge(tiles[i][j])) {
                    canMove = true;
                } //if
            } //for
            for (int k = 1; k < 4; k++) { //Checks if moving up is possible
                if(tiles[k][j] != null && tiles[k-1][j].canMerge(tiles[k][j])) {
                    canMove = true;
                } //if
            } //for
        } //for
        return canMove;
    } //canMove     
                    
}
