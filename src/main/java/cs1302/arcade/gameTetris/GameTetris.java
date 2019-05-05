package cs1302.arcade.gameTetris;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import java.util.Arrays;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import cs1302.arcade.gameTetris.shapes.*;

public class GameTetris{
    
    Scene scene;
    Rectangle[][] board = new Rectangle[24][10];
    GridPane grid = new GridPane();
    private Text score = new Text();

    //Rectangles are 20x20px
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

        grid.setPrefSize(300, 600);
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
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
        Shape s = new Square(0, 0, board, grid);
        Shape s1 = new Square(5, 0, board, grid);
        Shape s2 = new Square(8, 0, board, grid);
    }


}
