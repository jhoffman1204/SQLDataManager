package GUI; /**
 * Created by James on 5/26/2017.
 */
import Controller.Controller;
import Data.DataObjects.ClassInformation;
import Data.DataObjects.Message;
import Data.DataObjects.User;
import FSM.FiniteStateMachine;
import GUI.ClassGUI.ClassPage;
import GUI.ClassGUI.HomePage;
import GUI.ClassGUI.UserPage;
import GUI.MenuBar.MenuBar;
import GUI.MenuBar.PageSpecificMenuBar;
import Handler.LoginHandler;
import Handler.MenuButtons.CreateClassHandler;
import Handler.SignUpHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.security.auth.login.AppConfigurationEntry;


public class GUIManager extends Application {

    Controller controller;
    StackPane root;
    Pane currenBodyPane;
    HBox menuBar;
    HBox mainPage = new HBox();
    VBox pageSpeicificOptionBar;
    Node[] menuButtons = new Node[9];
    String currentUser;
    String currentViewingUser;
    String selectedClass;

    ClassPage classPage;
    UserPage userPage;
    HomePage home;
    MenuBar taskBar;

    @Override
    public void start(Stage primaryStage) {

        this.init();
        taskBar.init(this.controller,this);
        controller.init(this);
        this.initializeMenuBar();

        root.getChildren().add(menuBar);
        root.getChildren().add(mainPage);
        mainPage.getChildren().add(pageSpeicificOptionBar);
        root.setMargin(mainPage, new Insets(30,0,0,0));
        this.controller.getFsm().setState(FiniteStateMachine.LOGGED_OUT_STATE);
        this.updateMenuBarState();

        primaryStage.setTitle("Code Dash");
        primaryStage.setScene(new Scene(root, 1800, 1200));
        primaryStage.setMaximized(true);
        primaryStage.show();
        setAsBodyPane(home.generateHomeScreen());
    }

    /**
     * initializes this and the controller that the gui will be referencing
     */
    public void init(){
        controller = new Controller();
        classPage  = new ClassPage(this,controller);
        home       = new HomePage(this,controller);
        userPage   = new UserPage(this);
        taskBar    = new MenuBar();
        root       = new StackPane();
        menuBar    = new HBox();
        pageSpeicificOptionBar = PageSpecificMenuBar.getPageSpecificMenuBar();
    }
    public void testerMethod(){
        User testUser = new User("james","hoffman","jhoffman1204","hoffman96","CSE","Junior","git@exampl",
                "jamesuhoffman.com","courses","jhoffman1204@gmail.com");
        setAsBodyPane(userPage.generateUserPage(testUser));
        this.currentUser = "jhoffman1204";
        controller.setCurrentUser(testUser);

    }
    public void setController(Controller controller){
        this.controller = controller;
    }
    /**
     * The state manager controls which buttons need to be visible
     * 0. Create Profile Button
     * 1. Create Class Button
     * 2. Login Button
     * 3. Logout Button
     * 4. Search Bar
     * 5. Send Collab Request
     * 6. View Messages
     * 7. View Classes
     * @
     */
    public void initializeMenuBar(){
        menuButtons = taskBar.initializeMenuBar();
    }
    /**
     * Clears the menu and determines which buttons to display based on the current state
     */
    public void updateMenuBarState(){
        menuBar.getChildren().clear();
        pageSpeicificOptionBar.getChildren().clear();
        String state = controller.getFsm().getCurrentState();
        for(int i = 0; i < menuButtons.length; i++){
            if((state.charAt(i)+"").equalsIgnoreCase("1")){
                menuBar.getChildren().add(menuButtons[i]);
            }
            else if((state.charAt(i)+"").equalsIgnoreCase("2")){
                pageSpeicificOptionBar.getChildren().add(menuButtons[i]);
            }
        }
    }
    public VBox createSendCollabRequestPane(){
        VBox pane = new VBox();
        Label viewUserLabel = new Label("Sending Data.DataObjects.Message to: " + currentViewingUser);
        TextField subjectField = new TextField();
        Label subject = new Label("Subject: ");
        Label message = new Label("Data.DataObjects.Message: ");
        subjectField.setMinSize(200,50);
        subjectField.setMaxSize(200,50);
        TextField bodyField = new TextField();
        bodyField.setMinSize(400,200);
        bodyField.setMaxSize(400,200);

        pane.getChildren().add(viewUserLabel);
        pane.getChildren().add(subject);
        pane.getChildren().add(subjectField);
        pane.getChildren().add(message);
        pane.getChildren().add(bodyField);

        Button submit = new Button("Send");
        submit.setOnAction(event -> {
            controller.sendMessage(currentViewingUser, subjectField.getText(), bodyField.getText());
            System.out.println("current viewing user " + currentViewingUser);
            subjectField.clear();
            bodyField.clear();
            viewUserLabel.setText("Data.DataObjects.Message Sent!");
        });
        pane.getChildren().add(submit);
        return pane;
    }
    /**
     * Clears the current body pane and adds the new parameter pane
     * @param pane: Generic Pane so it can be a HBox, GridPane etc.
     */
    public void setAsBodyPane(Pane pane){
        mainPage.getChildren().remove(currenBodyPane);
        mainPage.getChildren().add(pane);
        mainPage.setMargin(pane, new Insets(100,0,0,200));
        pane.setMaxSize(800,500);
        pane.setMinSize(800,500);
        currenBodyPane = pane;
    }

    /**
     * Creates a Textfield that allows one to search for a user
     *   - calls the controller's method to search for a user
     * @return: The HBox that will contain the search box and search button
     */
    public HBox createSearchBar(){
        HBox searchBar = new HBox();

        TextField searchField = new TextField();
        Button submitSearch = new Button("Search");
        searchBar.getChildren().add(searchField);
        searchBar.getChildren().add(submitSearch);
        submitSearch.setOnAction(event -> {
            User temp = controller.searchForUser(searchField.getText());
            if(temp != null){
                controller.getFsm().setState(FiniteStateMachine.VIEW_USER_STATE);
                currentViewingUser = temp.getUsername();
                setAsBodyPane(userPage.generateUserPage(temp));
                updateMenuBarState();
                //System.out.println("current viewing user is " + temp.getUsername());
            }
            ClassInformation classInformation = controller.searchForClass(searchField.getText());
            if(classInformation != null){
                //setAsBodyPane(createClassPage(classInformation));
            }
        });
        return searchBar;
    }
    public GridPane createHomeScreen(){
        GridPane homescreen = new GridPane();

        Label label = new Label("Home Screen");
        homescreen.add(label,0,0);

        return homescreen;
    }
    public void setSelectedClass(String selectedClass){
        this.selectedClass = selectedClass;
    }
    public Controller getController(){
        return this.controller;
    }
    public String getSelectedClass(){ return this.selectedClass; }
    public String getCurrentUser(){ return this.currentUser; }
    public void setCurrentUser(String currentUser){ this.currentUser = currentUser; }
    public String getCurrentViewingUser() {
        return currentViewingUser;
    }
    public void setCurrentViewingUser(String currentViewingUser) {
        this.currentViewingUser = currentViewingUser;
    }
}
