package View.ClassGUI;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by James Hoffman on 7/8/2017.
 */
public class HomeScreen {

    public static GridPane createHomeScreen(){
        GridPane homescreen = new GridPane();

        Label label = new Label("Home Screen");
        homescreen.add(label,0,0);

        return homescreen;
    }
}
