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


public class GUIManager extends Application {

    private String state;
    Controller controller;
    StackPane root;
    // sets the main focus pane to be generic so that it is easy to remove and swap for other
    Pane currenBodyPane;
    HBox menuBar;
    HBox mainPage = new HBox();
    VBox pageSpeicificOptionBar;
    VBox leftMenuBar = new VBox();
    Label successLabel;
    Node[] menuButtons = new Node[9];
    String currentUser;
    String currentViewingUser;
    String selectedClass;
    Button viewMessages;

    ClassPage classPage;
    UserPage userPage;
    HomePage home;

    @Override
    public void start(Stage primaryStage) {
        this.init();
        primaryStage.setTitle("Code Dash");
        Controller controller;
        // root contains the menuBar and the mainPage
        // mainPage contains the pageSpecificOptionbar and the currentBodyPane
        root = new StackPane();
        menuBar = new HBox();
        //menuBar.setStyle("-fx-border-color: black;-fx-border-width: 7px;");

        pageSpeicificOptionBar = new VBox();
        pageSpeicificOptionBar.setPrefSize(300,300);
        pageSpeicificOptionBar.setMinSize(250,770);
        pageSpeicificOptionBar.setMaxSize(250,770);
        pageSpeicificOptionBar.setStyle("-fx-border-color: black;-fx-border-width: 7px;");
        this.initializeMenuBar();
        root.getChildren().add(menuBar);

        menuBar.setMinHeight(primaryStage.getY() / 6);
        menuBar.setMinWidth(primaryStage.getX());
        //menuBar.setStyle("-fx-background-color: #797D7F");

        root.getChildren().add(mainPage);
        mainPage.getChildren().add(pageSpeicificOptionBar);
        root.setMargin(mainPage, new Insets(30,0,0,0));
        this.controller.getFsm().setState(FiniteStateMachine.LOGGED_OUT_STATE);
        this.updateMenuBarState();

        primaryStage.setScene(new Scene(root, 1800, 1200));
        primaryStage.setMaximized(true);
        primaryStage.show();


        setAsBodyPane(home.generateHomeScreen());
        //testMethod();
    }

    /**
     * initializes this and the controller that the gui will be referencing
     */
    public void init(){
        controller = new Controller();
        classPage  = new ClassPage(this);
        home = new HomePage(this);
        userPage = new UserPage(this);
        controller.init(this);
    }
    public void testMethod(){
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

        Button createProfileButton = new Button("Create Profile");
        createProfileButton.setOnAction(event -> {
            setAsBodyPane(this.createSignUpForm());
        });
        menuButtons[0] = createProfileButton;
        Button addClassButton = new Button("Create Class");
        addClassButton.setOnAction(event -> {
            setAsBodyPane(this.addCreateNewClassForm());
        });
        menuButtons[1] = addClassButton;
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            setAsBodyPane(this.createLoginForm());
        });
        menuButtons[2] = loginButton;
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> {
            controller.getFsm().setState(FiniteStateMachine.LOGGED_OUT_STATE);
            controller.logout();
            updateMenuBarState();
            setAsBodyPane(createHomeScreen());
        });
        menuButtons[3] = logoutButton;

        menuButtons[4] = createSearchBar();

        Button sendCollabButton = new Button("Send Collaboration Request");
        menuButtons[5] = sendCollabButton;
        sendCollabButton.setOnAction(event -> {
            setAsBodyPane(this.createSendCollabRequestPane());
        });

        viewMessages = new Button("Inbox");
        menuButtons[6] = viewMessages;
        viewMessages.setOnAction(event -> {
            setAsBodyPane(this.createInbox());
        });

        Button viewClasses = new Button("View Classes");
        viewClasses.setOnAction(event -> {
            ClassInformation[] classes = controller.retrieveClasses(this.currentUser);
            ClassInformation[] participantClasses = controller.retrieveCoursesTakenByStudent(this.currentUser);
            //System.out.println(participantClasses[0]);
            setAsBodyPane(classPage.createClassesPane(controller.getCurrentUser().getUsername(), classes, participantClasses));

        });
        menuButtons[7] = viewClasses;

        Button addUser = new Button("Add User to Class");
        addUser.setOnAction(event -> {
            classPage.clearPage();
            classPage.createaddUserPane(this.selectedClass);
        });
        menuButtons[8] = addUser;


    }
    public String getCurrentUser(){
        return this.currentUser;
    }
    public void setCurrentUser(String currentUser){
        this.currentUser = currentUser;
    }
    public String getCurrentViewingUser() {
        return currentViewingUser;
    }

    public void setCurrentViewingUser(String currentViewingUser) {
        this.currentViewingUser = currentViewingUser;
    }

    public HBox createInbox(){
        HBox pane = new HBox();
        VBox subjects = new VBox();
        VBox showMessage = new VBox();
        pane.getChildren().add(subjects);
        pane.getChildren().add(showMessage);
        Label message = new Label("message");
        Label sender  = new Label("Sent by: ");

        Message[] messages = controller.retrieveMessages(controller.getCurrentUser().getUsername());
        System.out.println("the current user is: " + controller.getCurrentUser().getUsername());
        for(int i = 0; i < messages.length; i++){
            if(messages[i] != null) {
                Button button = new Button(messages[i].getSubject());
                String messagereceieved = messages[i].getBody();
                String senderText = messages[i].getSendingUser();
                button.setOnAction(event -> {
                    sender.setText("Sent by: " + senderText);
                    message.setText(messagereceieved);

                });
                subjects.getChildren().add(button);
            }
        }
        showMessage.getChildren().add(sender);
        showMessage.getChildren().add(message);
        pane.setMargin(showMessage, new Insets(0,0,0,50));
        return pane;
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
    public void setSize(Pane pane, int height, int width){
        pane.setMaxSize(width,height);
        pane.setMinSize(width,height);
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
                setAsBodyPane(createClassPage(classInformation));
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
    public GridPane createClassPage(ClassInformation classInformation){
        GridPane classPage = new GridPane();

        Label classNameLabel = new Label("ClassName: " + classInformation.getClass_name());
        Label descriptionLabel = new Label("Description: " + classInformation.getDescription());
        Label professorLabel = new Label("Professor: " + classInformation.getProfessor());
        Label semesterLabel = new Label("Semester: " + classInformation.getSemester());
        Label prerequisitesLabel = new Label("Prerequisites: " + classInformation.getPrerequisites());
        Label adminsLabel = new Label("Admins: " + classInformation.getAdmins());

        classPage.add(classNameLabel,0,0);
        classPage.add(descriptionLabel,0,1);
        classPage.add(professorLabel,0,2);
        classPage.add(semesterLabel,0,3);
        classPage.add(prerequisitesLabel,0,4);
        classPage.add(adminsLabel,0,5);

        return classPage;
    }
    public GridPane createLoginForm(){
        GridPane loginForm = new GridPane();

        TextField usernameOrEmail = new TextField();
        TextField password = new TextField();

        Label usernameOrEmailLabel = new Label("Username/Email:    ");
        Label passwordLabel = new Label("Password: ");

        loginForm.add(usernameOrEmailLabel,0,0);
        loginForm.add(passwordLabel,0,1);
        loginForm.add(usernameOrEmail,1,0);
        loginForm.add(password,1,1);

        loginForm.setMargin(usernameOrEmailLabel , new Insets(20,0,15,20));
        loginForm.setMargin(passwordLabel        , new Insets(20,0,15,20));
        loginForm.setMargin(usernameOrEmail      , new Insets(20,0,15,20));
        loginForm.setMargin(password             , new Insets(20,0,15,20));

        Button loginSubmit = new Button("Login");
        loginSubmit.setOnAction(event -> {
            if(controller.login(usernameOrEmail.getText(),password.getText()) == true){
                if(successLabel != null) {
                    successLabel.setText("");
                }
                controller.setCurrentUser(controller.retrieveUser(usernameOrEmail.getText()));
                System.out.println("controllers's current user is " + controller.getCurrentUser().getUsername());
                currentViewingUser = usernameOrEmail.getText();
                //System.out.println("current viewing user is " + usernameOrEmail.getText());
                successLabel = new Label("You have SuccessFully Logged In");
                loginForm.add(successLabel,0,3);
                loginForm.setMargin(successLabel,new Insets(15,0,0,0));
                usernameOrEmail.clear();
                password.clear();
                controller.getFsm().setState(FiniteStateMachine.LOGGED_IN_STATE);
                updateMenuBarState();
                setAsBodyPane(userPage.generateUserPage(controller.getCurrentUser()));
                this.currentUser = controller.getCurrentUser().getUsername();
            }
            else{
                if(successLabel != null) {
                    successLabel.setText("");
                }
                successLabel = new Label("Incorrect Username/Password");
                loginForm.add(successLabel,0,3);
                loginForm.setMargin(successLabel,new Insets(15,0,0,0));
            }
        });

        loginForm.add(loginSubmit,0,2);
        loginForm.setMargin(loginSubmit, new Insets(20,0,15,20));

        return loginForm;
    }
    public GridPane createSignUpForm(){
        GridPane signupForm = new GridPane();

        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField usernameTextField = new TextField();
        TextField passwordTextField = new TextField();
        TextField majorTextField = new TextField();
        TextField yearTextField = new TextField();
        TextField gitTextField = new TextField();
        TextField websiteTextField = new TextField();
        TextField coursesTextField = new TextField();
        TextField emailTextField = new TextField();

        Label firstNameLabel = new Label("First Name:       ");
        Label lastNameLabel = new Label ("Last Name:        ");
        Label usernameLabel = new Label ("Username:         ");
        Label passwordLabel = new Label ("Password:         ");
        Label majorLabel = new Label    ("Major:            ");
        Label yearLabel = new Label     ("Year (eg.Sophomore)");
        Label gitLabel = new Label      ("Git:              ");
        Label websiteLabel = new Label  ("Website:          ");
        Label coursesLabel = new Label  ("Previous courses (eg. CSE 219):        ");
        Label emailLabel = new Label    ("Email:            ");

        // add the labels to the sign up form
        signupForm.add(firstNameLabel,0,0);
        signupForm.add(lastNameLabel ,0,1);
        signupForm.add(usernameLabel ,0,2);
        signupForm.add(passwordLabel ,0,3);
        signupForm.add(majorLabel    ,0,4);
        signupForm.add(yearLabel     ,0,5);
        signupForm.add(gitLabel      ,0,6);
        signupForm.add(websiteLabel  ,0,7);
        signupForm.add(coursesLabel  ,0,8);
        signupForm.add(emailLabel    ,0,9);
        // add text fields to the sign up form
        signupForm.add(firstNameTextField,1,0);
        signupForm.add(lastNameTextField ,1,1);
        signupForm.add(usernameTextField ,1,2);
        signupForm.add(passwordTextField ,1,3);
        signupForm.add(majorTextField    ,1,4);
        signupForm.add(yearTextField     ,1,5);
        signupForm.add(gitTextField      ,1,6);
        signupForm.add(websiteTextField  ,1,7);
        signupForm.add(coursesTextField  ,1,8);
        signupForm.add(emailTextField    ,1,9);

        signupForm.setMargin(firstNameTextField,new Insets(0,0,15,0));
        signupForm.setMargin(lastNameTextField ,new Insets(0,0,15,0));
        signupForm.setMargin(usernameTextField ,new Insets(0,0,15,0));
        signupForm.setMargin(passwordTextField ,new Insets(0,0,15,0));
        signupForm.setMargin(majorTextField    ,new Insets(0,0,15,0));
        signupForm.setMargin(yearTextField     ,new Insets(0,0,15,0));
        signupForm.setMargin(gitTextField      ,new Insets(0,0,15,0));
        signupForm.setMargin(websiteTextField  ,new Insets(0,0,15,0));
        signupForm.setMargin(coursesTextField  ,new Insets(0,0,15,0));
        signupForm.setMargin(emailTextField    ,new Insets(0,0,15,0));

        signupForm.setMargin(firstNameLabel,new Insets(0,0,15,0));
        signupForm.setMargin(lastNameLabel ,new Insets(0,0,15,0));
        signupForm.setMargin(usernameLabel ,new Insets(0,0,15,0));
        signupForm.setMargin(passwordLabel ,new Insets(0,0,15,0));
        signupForm.setMargin(majorLabel    ,new Insets(0,0,15,0));
        signupForm.setMargin(yearLabel     ,new Insets(0,0,15,0));
        signupForm.setMargin(gitLabel      ,new Insets(0,0,15,0));
        signupForm.setMargin(websiteLabel  ,new Insets(0,0,15,0));
        signupForm.setMargin(coursesLabel  ,new Insets(0,0,15,0));
        signupForm.setMargin(emailLabel    ,new Insets(0,0,15,0));

        Button submitNewUser = new Button();
        submitNewUser.setText("Submit");
        submitNewUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user = new User(firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        usernameTextField.getText(),
                        passwordTextField.getText(),
                        majorTextField.getText(),
                        yearTextField.getText(),
                        gitTextField.getText(),
                        websiteTextField.getText(),
                        coursesTextField.getText(),
                        emailTextField.getText());
                controller.addUser(user);
                firstNameTextField.clear();
                lastNameTextField.clear();
                passwordTextField.clear();
                majorTextField.clear();
                yearTextField.clear();
                gitTextField.clear();
                websiteTextField.clear();
                coursesTextField.clear();
                emailTextField.clear();
                setCurrentViewingUser(controller.getCurrentUser().getUsername());
                setCurrentUser(controller.getCurrentUser().getUsername());
                controller.getFsm().setState(FiniteStateMachine.LOGGED_IN_STATE);
                updateMenuBarState();
                setAsBodyPane(userPage.generateUserPage(controller.getCurrentUser()));
            }
        });
        signupForm.add(submitNewUser    ,0,10);


        return signupForm;
    }
    public void setCurrentViewingUsers(String username){
        this.currentViewingUser = username;
    }
    public GridPane addCreateNewClassForm(){
        GridPane createNewClassForm = new GridPane();

        TextField className = new TextField();
        TextField description = new TextField();
        TextField professor = new TextField();
        TextField semester = new TextField();
        TextField prerequisites = new TextField();
        TextField admins = new TextField();

        Label classNameLabel = new Label     ("Class Name:       ");
        Label descriptionLabel = new Label   ("Description:        ");
        Label professorLabel = new Label     ("Professor:         ");
        Label semesterLabel = new Label      ("Semester:         ");
        Label prerequisitesLabel = new Label ("Prerequisuites:            ");
        Label adminsLabel = new Label        ("Admins: ");

        // add the labels to the sign up form
        createNewClassForm.add(classNameLabel,0,0);
        createNewClassForm.add(descriptionLabel,0,1);
        createNewClassForm.add(professorLabel,0,2);
        createNewClassForm.add(semesterLabel,0,3);
        createNewClassForm.add(prerequisitesLabel,0,4);
        createNewClassForm.add(adminsLabel,0,5);

        // add text fields to the form
        createNewClassForm.add(className,1,0);
        createNewClassForm.add(description,1,1);
        createNewClassForm.add(professor,1,2);
        createNewClassForm.add(semester,1,3);
        createNewClassForm.add(prerequisites,1,4);
        createNewClassForm.add(admins,1,5);

        //
        createNewClassForm.setMargin(classNameLabel    ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(descriptionLabel  ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(professorLabel    ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(semesterLabel     ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(prerequisitesLabel,new Insets(0,0,15,0));
        createNewClassForm.setMargin(adminsLabel       ,new Insets(0,0,15,0));

        createNewClassForm.setMargin(className    ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(description  ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(professor    ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(semester     ,new Insets(0,0,15,0));
        createNewClassForm.setMargin(prerequisites,new Insets(0,0,15,0));
        createNewClassForm.setMargin(admins       ,new Insets(0,0,15,0));

        Button submitNewUser = new Button();
        submitNewUser.setText("Submit");
        submitNewUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ClassInformation ci = new ClassInformation(className.getText(),
                        description.getText(),
                        professor.getText(),
                        semester.getText(),
                        prerequisites.getText(),
                        admins.getText());
                controller.addClass(ci);
                className.clear();
                description.clear();
                professor.clear();
                semester.clear();
                prerequisites.clear();
                admins.clear();
            }
        });
        createNewClassForm.add(submitNewUser    ,0,10);

        return createNewClassForm;
    }
    public void setSelectedClass(String selectedClass){
        this.selectedClass = selectedClass;
    }
    public Controller getController(){
        return this.controller;
    }
}
