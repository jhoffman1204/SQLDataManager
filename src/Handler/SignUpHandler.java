package Handler;

import Controller.Controller;
import Data.DataObjects.User;
import FSM.FiniteStateMachine;
import GUI.GUIManager;

/**
 * Created by James Hoffman on 7/6/2017.
 */
public class SignUpHandler extends EventHandler {
    public SignUpHandler(GUIManager manager, Controller controller){
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
    public void handle(User user){
        controller.addUser(user);
        guiManager.setCurrentViewingUser(user.getUsername());
        guiManager.setCurrentUser(user.getUsername());
        controller.setCurrentUser(controller.retrieveUser(user.getUsername()));
        controller.getFsm().setState(FiniteStateMachine.LOGGED_IN_STATE);
        guiManager.updateMenuBarState();
    }
}
