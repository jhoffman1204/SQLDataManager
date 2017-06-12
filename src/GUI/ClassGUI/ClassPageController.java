package GUI.ClassGUI;

import Data.DataObjects.ClassInformation;
import Data.DataObjects.User;
import FSM.FiniteStateMachine;
import GUI.GUIManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by James on 6/9/2017.
 */
public class ClassPageController {
    private HBox mainPane;
    GUIManager manager;

    public ClassPageController(GUIManager manager){
        mainPane = new HBox();
        this.manager = manager;
    }
    /**
     * This needs to be moved to another class
     * @param username
     * @return
     */
    public HBox createClassesPane(String username, ClassInformation[] adminClasses, ClassInformation[] participatingClasses){
        VBox classSelectPane = new VBox();
        VBox informationPane = new VBox();

        mainPane.getChildren().add(classSelectPane);
        mainPane.getChildren().add(informationPane);

        Label participantLabel = new Label("Classes you are a member of");

        Label adminLabel = new Label("Classes you are the admin of: ");
        classSelectPane.getChildren().add(adminLabel);
        for(int i = 0; i < 30; i++){
            if(adminClasses[i] != null){
                ClassInformation temp  = adminClasses[i];
                Button button = new Button(adminClasses[i].getClass_name() + " : " + adminClasses[i].getProfessor());
                button.setMinSize(475,100);
                button.setMaxSize(475,100);
                classSelectPane.getChildren().add(button);
                button.setOnAction(event -> {
                    mainPane.getChildren().clear();
                    Label description = new Label("Description: " + temp.getDescription());
                    Label professor = new Label("Professor: " + temp.getProfessor());
                    Label semester = new Label("Semester: "  + temp.getSemester());
                    mainPane.getChildren().add(description);
                    mainPane.getChildren().add(professor);
                    mainPane.getChildren().add(semester);


                });
            }
        }
        return mainPane;
    }
    public void createaddUserPane(String currentSelectedClass){
        VBox addUserPane = new VBox();

        Label userNameLabel = new Label("Username: ");
        TextField userNameTextField = new TextField();
        Button submitButton = new Button("Submit");

        addUserPane.getChildren().add(userNameLabel);
        addUserPane.getChildren().add(userNameTextField);
        addUserPane.getChildren().add(submitButton);

        this.clearPage();
        this.addToClassPage(addUserPane);

        submitButton.setOnAction(event -> {
            User user = manager.getController().searchForUser(userNameTextField.getText());
            if(user != null){
                manager.getController().addUserToClass(user, currentSelectedClass);
            }
            else {
                System.out.println("That user does not exist");
            }
        });
    }
    public void clearPage(){
        this.mainPane.getChildren().clear();
    }
    public void addToClassPage(Pane pane){
        this.mainPane.getChildren().add(pane);
    }

}
