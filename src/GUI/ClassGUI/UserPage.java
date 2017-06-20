package GUI.ClassGUI;

import Data.DataObjects.User;
import GUI.GUIManager;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by James Hoffman on 6/20/2017.
 */
public class UserPage {

    GUIManager guimanager;

    public UserPage(GUIManager manager){
        this.guimanager = manager;
    }
    public VBox generateUserPage(User user){
        VBox userPage = new VBox();
        VBox userInformationPane = new VBox();

        Label userNameLabel = new Label(user.getUsername());
        userNameLabel.setStyle("-fx-font: 35px Verdana;-fx-text-fill: black;");
        Label information = new Label("Your Information: ");
        information.setStyle("-fx-font: 23px Verdana;-fx-underline: true;");
        Label majorLabel  = new Label("Major: " + user.getMajor());
        Label yearLabel = new Label("Year: " + user.getYear());
        Label gitLabel = new Label("Git: " + user.getGit());
        Label website = new Label("Website: " + user.getWebsite());
        Label courses = new Label("Courses: " + user.getCourses());
        Label email  = new Label("Email: " + user.getEmail());

        userInformationPane.getChildren().add(userNameLabel);
        userInformationPane.getChildren().add(information);
        userInformationPane.getChildren().add(majorLabel);
        userInformationPane.getChildren().add(yearLabel);
        userInformationPane.getChildren().add(gitLabel);
        userInformationPane.getChildren().add(website);
        userInformationPane.getChildren().add(courses);
        userInformationPane.getChildren().add(email);

        for (Node child : userInformationPane.getChildren()) {
            //userInformationPane.setMargin(child , new Insets(20,0,15,20));
        }

        userPage.getChildren().add(userInformationPane);

        return userPage;
    }
}
