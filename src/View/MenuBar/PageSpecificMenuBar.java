package View.MenuBar;

import javafx.scene.layout.VBox;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class PageSpecificMenuBar {
    public static VBox getPageSpecificMenuBar(){
        VBox box = new VBox();
        box.setPrefSize(300,300);
        box.setMinSize(250,770);
        box.setMaxSize(250,770);
        box.setStyle("-fx-border-color: black;-fx-border-width: 7px;");
        return box;
    }
}
