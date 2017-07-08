package GUI.MenuBar;

import GUI.GUIManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class MenuBar {

    Controller controller;
    GUIManager guiManager;
    Node[] menuButtons;

    public void init(Controller controller, GUIManager guiManager){
        this.menuButtons = new Node[9];
        this.guiManager = guiManager;
        this.controller = controller;
    }
    public VBox creaetMenuPane(){

        VBox pane = new VBox();

        Button createProfileButton = new Button("Create Profile");
        createProfileButton.setOnAction(event -> {
            guiManager.setAsBodyPane(guiManager.createSignUpForm());
        });
        menuButtons[0] = createProfileButton;

        return pane;
    }
}
