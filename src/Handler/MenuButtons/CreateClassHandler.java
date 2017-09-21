package Handler.MenuButtons;

import Controller.Controller;
import ModelData.DataObjects.ClassInformation;
import View.ClassGUI.CoursePage;
import View.GUIManager;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class CreateClassHandler {
    GUIManager guiManager;
    Controller controller;

    public CreateClassHandler(GUIManager manager, Controller controller){
        this.guiManager = manager;
        this.controller = controller;
    }
    public Controller getController(){
        return controller;
    }
    public GUIManager getGuiManager(){
        return guiManager;
    }
    public void handle(ClassInformation ci){
        controller.addClass(ci);
        guiManager.setAsBodyPane(CoursePage.createClassPage(ci,guiManager));
    }
}
