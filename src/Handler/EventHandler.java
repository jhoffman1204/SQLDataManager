package Handler;
import View.GUIManager;
import Controller.Controller;
/**
 * Created by James Hoffman on 7/6/2017.
 */
public abstract class EventHandler {

    GUIManager guiManager;
    Controller controller;

    public EventHandler(GUIManager manager, Controller controller){
        this.guiManager = manager;
        this.controller = controller;
    }
    public EventHandler(){

    }
    public Controller getController(){
        return controller;
    }
    public GUIManager getGuiManager(){
        return guiManager;
    }
}
