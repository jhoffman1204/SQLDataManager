package Handler.MenuButtons;

import Controller.Controller;
import View.GUIManager;
import Handler.EventHandler;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class ViewMessageHandler extends EventHandler{
    GUIManager guiManager;
    Controller controller;

    public ViewMessageHandler(GUIManager manager, Controller controller){
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
