package GUI.ClassGUI;

import Data.DataObjects.ClassInformation;
import Data.DataObjects.User;
import GUI.GUIManager;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by James Hoffman on 7/8/2017.
 */
public class CoursePage {

    public static VBox createClassPage(ClassInformation classInformation, GUIManager guiManager){

        VBox mainPage = new VBox();
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
        mainPage.getChildren().add(classPage);

        Label classParticiapntsLabel = new Label("Class Participants: ");
        mainPage.getChildren().add(classParticiapntsLabel);
        mainPage.getChildren().add(createParticipantsPane(classInformation.getClass_name(),guiManager));


        return mainPage;
    }

    public static VBox createParticipantsPane(String currentClassSelected, GUIManager guiManager){
        VBox participantsPane = new VBox();

        User[] classMembers = guiManager.getController().getParticipantsOfClass(currentClassSelected);
        for(int i = 0; i < classMembers.length; i++){
            if(classMembers[i] != null) {
                Label memberLabel = new Label(classMembers[i].getUsername());
                participantsPane.getChildren().add(memberLabel);
            }
        }

        return participantsPane;
    }
}
