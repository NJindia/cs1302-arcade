package cs1302.arcade.game2048;

import javafx.scene.text.Font;
import javafx.scene.*;
import javafx.scene.control.Control;
import javafx.scene.text.*;
import javafx.scene.layout.*;

public class Game2048 {

    private final double xMin = 0;
    private final double xMax = 640 - 100;
    private final double yMin = 0;
    private final double yMax = 480 - 100;    
    
    Text t = new Text("High Score: 10");
    Text t2 = new Text("Score: 10");

    //t.setFont(new Font(20));
    
    HBox score = new HBox(t, t2);
    score.setSpacing(30);
    
    Group group = new Group(score);
    
    Scene scene = new Scene(group, 640, 480); 

    public Scene getScene() {
        return scene;
    }



}
