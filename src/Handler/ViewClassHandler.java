package Handler;

import Controller.Controller;
import FSM.FiniteStateMachine;
import View.GUIManager;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class ViewClassHandler extends EventHandler{
    GUIManager guiManager;
    Controller controller;

    public ViewClassHandler(GUIManager manager, Controller controller){
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
        guiManager.getController().setState(FiniteStateMachine.VIEW_CLASS_AS_USER);
        guiManager.updateMenuBarState();
    }
}
