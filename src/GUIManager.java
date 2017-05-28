/**
 * Created by James on 5/26/2017.
 */
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class GUIManager extends Application {

    private String state;
    Controller controller = new Controller();
    StackPane root;
    // sets the main focus pane to be generic so that it is easy to remove and swap for other
    Pane currenBodyPane;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        this.init();
        primaryStage.setTitle("Code Dash");

        root = new StackPane();
        root.getChildren().add(this.createMenuBar());

        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

        controller.login("dcooper123","test");
        controller.login("ddoesntexist123","test");
        controller.login("james.a.hoffman@stonybrook.edu","test");
    }
    public void init(){
        controller.init();
    }
    public HBox createMenuBar(){
        HBox menuBar = new HBox();

        Button createProfileClass = new Button("Create Profile");
        createProfileClass.setOnAction(event -> {
            setAsBodyPane(this.createSignUpForm());
        });
        menuBar.getChildren().add(createProfileClass);

        Button addClassButton = new Button("Create Class");
        addClassButton.setOnAction(event -> {
            setAsBodyPane(this.addCreateNewClassForm());
        });
        menuBar.getChildren().add(addClassButton);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            setAsBodyPane(this.createLoginForm());
        });
        menuBar.getChildren().add(loginButton);

        return menuBar;
    }
    public void setAsBodyPane(Pane pane){
        root.getChildren().remove(currenBodyPane);
        root.getChildren().add(pane);
        root.setMargin(pane, new Insets(210,0,0,400));
        currenBodyPane = pane;
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

        loginForm.setMargin(usernameOrEmailLabel , new Insets(0,0,15,0));
        loginForm.setMargin(passwordLabel        , new Insets(0,0,15,0));
        loginForm.setMargin(usernameOrEmail      , new Insets(0,0,15,0));
        loginForm.setMargin(password             , new Insets(0,0,15,0));

        Button loginSubmit = new Button("Login");
        loginSubmit.setOnAction(event -> {
            controller.login(usernameOrEmail.getText(),password.getText());
        });

        loginForm.add(loginSubmit,0,2);

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
            }
        });
        signupForm.add(submitNewUser    ,0,10);


        return signupForm;
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
}
