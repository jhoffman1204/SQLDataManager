package View.ClassGUI;

import ModelData.DataObjects.ClassInformation;
import View.GUIManager;
import Handler.MenuButtons.CreateClassHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/8/2017.
 */
public class CreateClassPage {

    public static GridPane addCreateNewClassForm(Controller controller, GUIManager guiManager){
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
        submitNewUser.setOnAction(event ->  {
            ClassInformation ci = new ClassInformation(className.getText(),
                    description.getText(),
                    professor.getText(),
                    semester.getText(),
                    prerequisites.getText(),
                    admins.getText());

            className.clear();
            description.clear();
            professor.clear();
            semester.clear();
            prerequisites.clear();
            admins.clear();

            CreateClassHandler handler = new CreateClassHandler(guiManager,controller);
            handler.handle(ci);
        });
        createNewClassForm.add(submitNewUser    ,0,10);

        return createNewClassForm;
    }
}
