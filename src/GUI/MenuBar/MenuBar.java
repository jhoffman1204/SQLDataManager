package GUI.MenuBar;

import Data.DataObjects.ClassInformation;
import FSM.FiniteStateMachine;
import GUI.ClassGUI.*;
import GUI.GUIManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class MenuBar {

    Controller controller;
    GUIManager guiManager;
    Node[] menuButtons;

    public void init(Controller controller, GUIManager guiManager){
        this.menuButtons = new Node[9];
        this.guiManager = guiManager;
        this.controller = controller;
    }
    public VBox createMenuPane(){

        VBox pane = new VBox();

        return pane;
    }
    public Node[] initializeMenuBar(){

        Button createProfileButton = new Button("Create Profile");
        createProfileButton.setOnAction(event -> {
            guiManager.setAsBodyPane(SignupForm.createSignUpForm(controller,guiManager));
        });
        menuButtons[0] = createProfileButton;


        Button addClassButton = new Button("Create Class");
        addClassButton.setOnAction(event -> {
            guiManager.setAsBodyPane(CreateClassPage.addCreateNewClassForm(controller,guiManager));
        });
        menuButtons[1] = addClassButton;
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            guiManager.setAsBodyPane(LoginForm.createLoginForm(controller,guiManager));
        });
        menuButtons[2] = loginButton;
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> {
            controller.getFsm().setState(FiniteStateMachine.LOGGED_OUT_STATE);
            controller.logout();
            guiManager.updateMenuBarState();
            guiManager.setAsBodyPane(guiManager.createHomeScreen());
        });
        menuButtons[3] = logoutButton;

        menuButtons[4] = guiManager.createSearchBar();

        Button sendCollabButton = new Button("Send Collaboration Request");
        menuButtons[5] = sendCollabButton;
        sendCollabButton.setOnAction(event -> {
            guiManager.setAsBodyPane(guiManager.createSendCollabRequestPane());
        });

        Button viewMessages = new Button("Inbox");
        menuButtons[6] = viewMessages;
        viewMessages.setOnAction(event -> {
            guiManager.setAsBodyPane(MessagesPane.createInbox(controller,guiManager));
        });

        Button viewClasses = new Button("View Classes");
        viewClasses.setOnAction(event -> {
            ClassInformation[] classes = controller.retrieveClasses(guiManager.getCurrentUser());
            ClassInformation[] participantClasses = controller.retrieveCoursesTakenByStudent(guiManager.getCurrentUser());
            ClassPage classPage = new ClassPage(guiManager,controller);
            guiManager.setAsBodyPane(classPage.createClassesPane(controller.getCurrentUser().getUsername(), classes, participantClasses));

        });
        menuButtons[7] = viewClasses;

        Button addUser = new Button("Add User to Class");
        addUser.setOnAction(event -> {
            ClassPage classPage = new ClassPage(guiManager,controller);
            classPage.clearPage();
            classPage.createaddUserPane(guiManager.getSelectedClass());
        });
        menuButtons[8] = addUser;


        return menuButtons;
    }
}
