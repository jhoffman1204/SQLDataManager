/**
 * Created by James on 5/26/2017.
 */
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class GUIManager extends Application {

    private String state;
    Controller controller = new Controller();


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        this.init();
        primaryStage.setTitle("Code Dash");

        StackPane root = new StackPane();
        root.getChildren().add(this.createMenuBar());

        GridPane signupForm = this.addCreateNewClassForm();
        root.getChildren().add(signupForm);
        root.setMargin(signupForm,new Insets(210,0,0,400));

        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }
    public void init(){
        controller.init();
    }
    public HBox createMenuBar(){
        HBox menuBar = new HBox();

        Button createProfileClass = new Button("Create Profile");
        createProfileClass.setOnAction(event -> {
            controller.setState(FiniteStateMachine.ADD_USER_STATE);
            System.out.println("In the Create Profile State");
        });
        menuBar.getChildren().add(createProfileClass);

        Button addClassButton = new Button("Create Class");
        addClassButton.setOnAction(event -> {
            controller.setState(FiniteStateMachine.ADD_CLASS_STATE);
            System.out.println("In the create class state");
        });
        menuBar.getChildren().add(addClassButton);


        return menuBar;
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
