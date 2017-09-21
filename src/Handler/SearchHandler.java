package Handler;

import Controller.Controller;
import View.GUIManager;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class SearchHandler extends EventHandler{
    GUIManager guiManager;
    Controller controller;

    public SearchHandler(GUIManager manager, Controller controller){
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
