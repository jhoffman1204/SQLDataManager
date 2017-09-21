package View; /**
 * Created by James on 5/26/2017.
 */
import Controller.Controller;
import FSM.FiniteStateMachine;
import View.ClassGUI.ClassPage;
import View.ClassGUI.HomePage;
import View.ClassGUI.UserPage;
import View.MenuBar.MenuBar;
import View.MenuBar.PageSpecificMenuBar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GUIManager extends Application {

    Controller controller;
    StackPane root;
    Pane currenBodyPane;
    HBox menuBar;
    HBox mainPage = new HBox();
    VBox pageSpeicificOptionBar;
    Node[] menuButtons = new Node[10];
    //String currentUser;
    //String currentViewingUser;
    //String selectedClass;

    ClassPage classPage;
    UserPage userPage;
    HomePage home;
    MenuBar taskBar;

    /**
     * Where the beginning to the program starts
     * Initializes the main pane and the
     * @param primaryStage
     */

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
        setAsBodyPane(HomePage.generateHomeScreen(controller,this));
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

    /**
     * Loads the Menu Buttons that will be used throughout the program and places them in
     * an array that the FSM will pick and choose from based on what page the user is on.
     */
    public void initializeMenuBar(){
        this.menuButtons = taskBar.initializeMenuBar();
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

    public void setController(Controller controller){
        this.controller = controller;
    }
    public Controller getController(){
        return this.controller;
    }

//    public String getSelectedClass(){ return this.selectedClass; }
//    public String getCurrentUser(){ return this.currentUser; }
//    public void setCurrentUser(String currentUser){ this.currentUser = currentUser; }
//    public String getCurrentViewingUser() {
//        return currentViewingUser;
//    }
//    public void setSelectedClass(String selectedClass){
//        this.selectedClass = selectedClass;
//    }
//    public void setCurrentViewingUser(String currentViewingUser) {
//        this.currentViewingUser = currentViewingUser;
//    }
}
