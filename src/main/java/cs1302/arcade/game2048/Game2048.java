package cs1302.arcade.game2048;

import javafx.scene.text.Font;
import javafx.scene.*;
import javafx.scene.control.Control;
import javafx.scene.text.*;
public class Game2048 {

    private final double xMin = 0;
    private final double xMax = 640 - 100;
    private final double yMin = 0;
    private final double yMax = 480 - 100;

    Group group = new Group();
    Scene scene = new Scene(group, 640, 480);

    Text t = new Text("2040 Game!");

    t.setFont(new Font(20));
  
    HBox gameTitle = new HBox(t);

    group.getChildren().add(gameTitle);


    public static Scene getScene()
        {
            return scene;
        }



}
