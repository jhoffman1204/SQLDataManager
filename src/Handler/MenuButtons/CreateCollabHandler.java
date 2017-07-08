package Handler.MenuButtons;

import Controller.Controller;
import GUI.GUIManager;
import Handler.EventHandler;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class CreateCollabHandler extends EventHandler{
    GUIManager guiManager;
    Controller controller;

    public CreateCollabHandler(GUIManager manager, Controller controller){
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
