package cs1302.arcade;

import javafx.scene.control.Labeled;
import javafx.scene.text.*;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Orientation;
import javafx.scene.layout.Background;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import cs1302.arcade.game2048.*;
import cs1302.arcade.gameTetris.*;
import java.util.Random;
import javafx.scene.control.Separator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

/**
 * Represents the entire application with 2 games: 2048 and Tetris.
 */
public class ArcadeApp extends Application {
    public Stage stage;

    /**
     * Creates the main menu scene.
     * @return a {@code Scene} for the main menu
     */
    public Scene mainMenu() {
        Game2048 game2048 = new Game2048();
        GameTetris gameTetris = new GameTetris();

        Button b1 = new Button();
        ImageView iv1 = new ImageView(new Image("2048tiles/2048.png"));
        iv1.setFitWidth(200);
        iv1.setPreserveRatio(true);
        b1.setGraphic(iv1);
        b1.setOnAction(e -> stage.setScene(game2048.getGameScene(this)));

        Button b2 = new Button();
        ImageView iv2 = new ImageView(new Image("tetris-logo.png"));
        iv2.setFitWidth(305);
        iv2.setPreserveRatio(true);
        b2.setGraphic(iv2);
        b2.setOnAction(e -> stage.setScene(gameTetris.getGameScene(this)));                 

        Button exit = new Button("Exit");
        exit.setMinWidth(100);
        exit.setStyle("-fx-background-color: Red");
        exit.setOnAction(e -> System.exit(0));
        HBox buttons = new HBox(b1, b2);

        VBox newPane = new VBox(buttons, exit);
        BackgroundFill bf = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        newPane.setBackground(new Background(bf));
        newPane.setPadding(new Insets(10, 10, 10, 10));  
        newPane.setSpacing(10);
        Scene scene = new Scene(newPane, 560, 275);
        return scene;
    }
    
    /**
     * Displays the menu.
     */
    public void setSceneToMenu() {
        stage.setScene(mainMenu());
    }
    
    /** {@inheritdoc} */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("cs1302-arcade!");
        stage.setScene(mainMenu());
        stage.sizeToScene();
        stage.show();
    } //start
} // ArcadeApp
