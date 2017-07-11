package Handler;

import Controller.Controller;
import Data.DataObjects.User;
import FSM.FiniteStateMachine;
import GUI.ClassGUI.UserPage;
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
    public boolean handle(User user){
        if(controller.userExist(user) == false) {
            controller.addUser(user);
        }
        else{
            return false;
        }
        guiManager.setCurrentViewingUser(user.getUsername());
        guiManager.setCurrentUser(user.getUsername());
        UserPage userPage = new UserPage(guiManager);
        guiManager.setAsBodyPane(userPage.generateUserPage(controller.getCurrentUser(),true));
        controller.setCurrentUser(controller.retrieveUser(user.getUsername()));
        controller.getFsm().setState(FiniteStateMachine.LOGGED_IN_STATE);
        guiManager.updateMenuBarState();
        return true;
    }
}
