package GUI.ClassGUI;

import GUI.GUIManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;



/**
 * Created by James Hoffman on 6/20/2017.
 */
public class HomePage {

    GUIManager manager;

    public HomePage(GUIManager manager){
        this.manager = manager;
    }

    public VBox generateHomeScreen(){
        VBox mainHomePane = new VBox();

        Label label = new Label("Welcome to CodeDash!");
        label.setStyle("-fx-font: 50px Verdana;-fx-text-fill: black;");
        mainHomePane.getChildren().add(label);
        mainHomePane.setMargin(label, new Insets(0,100,0,100));
        mainHomePane.setStyle("-fx-border-color: black;-fx-border-width: 0px;-fx-background-color: #E5E8E8  ");


        Button signupButton= new Button("Sign Up");
        signupButton.setStyle("-fx-background-color: #add8e6;-fx-text-fill: white;-fx-font-size: 36px;-fx-border-style: solid;");
        signupButton.setPrefSize(250,75);
        mainHomePane.setMargin(signupButton, new Insets(150,100,0,250));
        mainHomePane.getChildren().add(signupButton);

        signupButton.setOnAction(event -> {
            manager.setAsBodyPane(manager.createSignUpForm());
        });
        signupButton.setOnMouseEntered(event -> {
            signupButton.setStyle("-fx-background-color: #99cfe0;-fx-text-fill: white;-fx-font-size: 36px;-fx-border-style: solid;");
        });
        signupButton.setOnMouseExited(event -> {
            signupButton.setStyle("-fx-background-color: #add8e6;-fx-text-fill: white;-fx-font-size: 36px;-fx-border-style: solid;");
        });

        return mainHomePane;
    }
}
