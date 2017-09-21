package View.ClassGUI;

import View.GUIManager;
import Handler.LoginHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class LoginForm {

    public static GridPane createLoginForm(Controller controller, GUIManager guiManager){
        // Creating the login pane
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
                LoginHandler loginHandler = new LoginHandler(guiManager,controller);
                loginHandler.handle(usernameOrEmail.getText());
                UserPage userPage = new UserPage(guiManager);
                guiManager.setAsBodyPane(userPage.generateUserPage(controller.getCurrentUser(),true));
            }
            else{
                Label successLabel = new Label("Incorrect Username/Password");
                loginForm.add(successLabel,0,3);
                loginForm.setMargin(successLabel,new Insets(15,0,0,0));
            }
        });

        loginForm.add(loginSubmit,0,2);
        loginForm.setMargin(loginSubmit, new Insets(20,0,15,20));

        return loginForm;
    }
}
