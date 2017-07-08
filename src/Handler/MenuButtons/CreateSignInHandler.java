package Handler.MenuButtons;

import Controller.Controller;
import Data.DataObjects.User;
import GUI.GUIManager;
import Handler.EventHandler;
import Handler.SignUpHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class CreateSignInHandler extends EventHandler{
    GUIManager guiManager;
    Controller controller;

    public CreateSignInHandler(GUIManager manager, Controller controller){
        this.guiManager = manager;
        this.controller = controller;
    }
    public Controller getController(){
        return controller;
    }
    public GUIManager getGuiManager(){
        return guiManager;
    }
    public void handle(){

    }
}