package GUI.ClassGUI;

import Data.DataObjects.ClassInformation;
import Data.DataObjects.User;
import FSM.FiniteStateMachine;
import GUI.GUIManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by James on 6/9/2017.
 */
public class ClassPageController {
    private VBox mainPane;
    GUIManager manager;
    String currentClassSelected;

    public ClassPageController(GUIManager manager){
        mainPane = new VBox();
        this.manager = manager;
    }
    /**
     * This needs to be moved to another class
     * @param username
     * @return
     */
    public VBox createClassesPane(String username, ClassInformation[] adminClasses, ClassInformation[] participatingClasses){
        VBox classSelectPane = new VBox();
        VBox informationPane = new VBox();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(classSelectPane);
        mainPane.getChildren().add(informationPane);

        Label participantLabel = new Label("Classes you are a member of");
        classSelectPane.getChildren().add(participantLabel);
        for(int i = 0; i < 30; i++){
            if(participatingClasses[i] != null){
                ClassInformation temp  = participatingClasses[i];
                Button button = new Button(participatingClasses[i].getClass_name() + " : " + participatingClasses[i].getProfessor());
                button.setMinSize(475,100);
                button.setMaxSize(475,100);
                System.out.println(participatingClasses[i].getClass_name() + " added");
                classSelectPane.getChildren().add(button);
                button.setOnAction(event -> {
                    Label description = new Label("Description: " + temp.getDescription());
                    Label professor = new Label("Professor: " + temp.getProfessor());
                    Label semester = new Label("Semester: "  + temp.getSemester());
                    mainPane.getChildren().add(description);
                    mainPane.getChildren().add(professor);
                    mainPane.getChildren().add(semester);
                    currentClassSelected = temp.getClass_name();
                    manager.getController().setState(FiniteStateMachine.VIEW_CLASS_AS_ADMIN);
                    manager.updateMenuBarState();
                });
            }
        }

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
                    currentClassSelected = temp.getClass_name();
                    manager.getController().setState(FiniteStateMachine.VIEW_CLASS_AS_ADMIN);
                    manager.updateMenuBarState();
                });
            }
        }
        return mainPane;
    }
    public void createaddUserPane(String currentSelectedClass){
        VBox addUserPane = new VBox();
        GridPane fields = new GridPane();

        Label userNameLabel = new Label("Username: ");
        TextField userNameTextField = new TextField();
        Button submitButton = new Button("Submit");

        fields.add(userNameLabel,0,0);
        fields.add(userNameTextField,1,0);
        fields.add(submitButton,0,1);

        addUserPane.getChildren().add(fields);

        this.clearPage();
        this.addToClassPage(addUserPane);

        submitButton.setOnAction(event -> {
            User user = manager.getController().searchForUser(userNameTextField.getText());
            if(user != null){
                manager.getController().addUserToClass(user, currentClassSelected);
            }
            else {
                System.out.println("That user does not exist");
            }
        });
    }
    public VBox createParticipantsPane(){
        VBox participantsPane = new VBox();


        return participantsPane;
    }
    public void clearPage(){
        this.mainPane.getChildren().clear();
    }
    public void addToClassPage(Pane pane){
        this.mainPane.getChildren().add(pane);
    }

}
