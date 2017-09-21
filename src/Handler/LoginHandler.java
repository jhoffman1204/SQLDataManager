package Handler;

import Controller.Controller;
import FSM.FiniteStateMachine;
import View.GUIManager;

/**
 * Created by James Hoffman on 7/6/2017.
 */
public class LoginHandler extends EventHandler{
    public LoginHandler(GUIManager manager, Controller controller){
        super(manager,controller);
    }
    @Override
    public Controller getController(){
        return super.getController();
    }
    @Override
    public GUIManager getGuiManager(){
        return super.getGuiManager();
    }
    public void handle(String username){
        guiManager.getController().getUserStateManager().setCurrentViewingUser(username);
        guiManager.getController().getUserStateManager().setCurrentUser(username);
        controller.setCurrentUser(controller.retrieveUser(username));
        controller.getFsm().setState(FiniteStateMachine.LOGGED_IN_STATE);
        guiManager.updateMenuBarState();
    }
}
