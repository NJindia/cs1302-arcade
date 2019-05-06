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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
//import java.awt.Font;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;


public class ArcadeApp extends Application {
    public Stage stage;
    
    public Scene mainMenu() {
        Separator style = new Separator(Orientation.HORIZONTAL);
        Game2048 game2048 = new Game2048();
        GameTetris gameTetris = new GameTetris();
        Font font = Font.font("Lucida Sans Typewriter", FontWeight.BOLD, 50);
        Scene scene1;
        Font font1 = Font.font("Lucida Sans Typewriter", FontWeight.BOLD, 50);
        Button b1 = new Button("2048 Game");
        b1.setFont(font);
        b1.setStyle(" -fx-background-color: Orange");
        b1.setOnAction(e -> {
                stage.setScene(game2048.getGameScene(this));
            });
        b1.setMinWidth(175);
        Button b2 = new Button("Tetris Game");
        b2.setMinWidth(150);
        b2.setFont(font1);
        b2.setStyle("-fx-background-color: #00CE52;");
//            game2.setPadding(new Insets(10, 50, 100, 50));
        b2.setOnAction(e ->{
                stage.setScene(gameTetris.getGameScene());
            });                             
        Button exit = new Button("Exit");
        exit.setMinWidth(100);
        exit.setStyle("-fx-background-color: Red");
        exit.setOnAction(e -> System.exit(0));
        style.setPadding(new Insets(30, 10, 10, 30));
        
        VBox newPane = new VBox(b1, style, b2, exit);
        
        newPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        newPane.setPadding(new Insets(10, 10, 10, 10));  
        newPane.setSpacing(10);
        Scene scene = new Scene(newPane, 480, 330);
        return scene;
    }

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
