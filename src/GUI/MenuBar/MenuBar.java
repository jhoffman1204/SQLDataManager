package GUI.MenuBar;

import Data.DataObjects.ClassInformation;
import Data.DataObjects.User;
import FSM.FiniteStateMachine;
import GUI.ClassGUI.*;
import GUI.GUIManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/7/2017.
 *
 * /**
 * The state manager controls which buttons need to be visible
 * 0. Create Profile Button
 * 1. Create Class Button
 * 2. Login Button
 * 3. Logout Button
 * 4. Search Bar
 * 5. Send Collab Request
 * 6. View Messages
 * 7. View Classes
 * 8. Add User to Class
 * 9. View My Profile
 * @
 */

public class MenuBar {

    Controller controller;
    GUIManager guiManager;
    Node[] menuButtons;

    public void init(Controller controller, GUIManager guiManager){
        this.menuButtons = new Node[10];
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
            guiManager.setAsBodyPane(HomePage.generateHomeScreen(controller,guiManager));
        });
        menuButtons[3] = logoutButton;

        menuButtons[4] = SearchBar.createSearchBar(controller,guiManager);

        Button sendCollabButton = new Button("Send Collaboration Request");
        menuButtons[5] = sendCollabButton;
        sendCollabButton.setOnAction(event -> {
            guiManager.setAsBodyPane(SendMessagePane.createSendCollabRequestPane(controller,guiManager));
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
            guiManager.setAsBodyPane(classPage.createaddUserPane(guiManager.getSelectedClass()));
        });
        menuButtons[8] = addUser;

        Button myProfileButton = new Button("My Profile");
        myProfileButton.setOnAction(event -> {
            UserPage userPage = new UserPage(guiManager);
            User user = controller.getCurrentUser();
            guiManager.setAsBodyPane(userPage.generateUserPage(user,true));
        });

        menuButtons[9] = myProfileButton;

        return menuButtons;
    }
}
