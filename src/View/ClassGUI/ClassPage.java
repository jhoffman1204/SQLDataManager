package View.ClassGUI;

import ModelData.DataObjects.ClassInformation;
import ModelData.DataObjects.User;
import FSM.FiniteStateMachine;
import View.GUIManager;
import Handler.ViewClassHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import Controller.Controller;
/**
 * Created by James on 6/9/2017.
 */
public class ClassPage {
    private VBox mainPane;
    GUIManager manager;
    Controller controller;
    String currentClassSelected;

    public ClassPage(GUIManager manager, Controller controller){
        mainPane = new VBox();
        this.manager = manager;
        this.controller = controller;
    }
    /**
     * Used to generate the pane for the class pane that displays classes you are the admin of
     * and classes that you are a part of.
     *
     * Forms the View based on an array of classes that the use is the admin of and an array of classes that the user is a participant
     *
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
                    mainPane.getChildren().clear();
                    currentClassSelected = temp.getClass_name();
                    manager.getController().getUserStateManager().setSelectedClass(temp.getClass_name());
                    manager.getController().setState(FiniteStateMachine.VIEW_CLASS_AS_ADMIN);
                    mainPane.getChildren().add(CoursePage.createClassPage(temp,manager));
                    ViewClassHandler handler = new ViewClassHandler(manager,controller);
                    handler.handle();
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
                    currentClassSelected = temp.getClass_name();
                    manager.getController().getUserStateManager().setSelectedClass(temp.getClass_name());
                    mainPane.getChildren().add(CoursePage.createClassPage(temp,manager));
                    manager.getController().setState(FiniteStateMachine.VIEW_CLASS_AS_ADMIN);
                    manager.updateMenuBarState();
                });
            }
        }
        return mainPane;
    }
    public VBox createaddUserPane(String currentSelectedClass){
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
                manager.getController().addUserToClass(user, currentSelectedClass);
            }
            else {
                System.out.println("That user does not exist");
            }
        });

        return addUserPane;
    }
    public void clearPage(){
        this.mainPane.getChildren().clear();
    }
    public void addToClassPage(Pane pane){
        this.mainPane.getChildren().add(pane);
    }

}
