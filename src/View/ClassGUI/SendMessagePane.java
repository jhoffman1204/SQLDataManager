package View.ClassGUI;

import View.GUIManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/8/2017.
 */
public class SendMessagePane {

    public static VBox createSendCollabRequestPane(Controller controller, GUIManager guiManager){
        VBox pane = new VBox();
        Label viewUserLabel = new Label("Sending Message to: " +
                guiManager.getController().getUserStateManager().getCurrentViewingUser());
        TextField subjectField = new TextField();
        Label subject = new Label("Subject: ");
        Label message = new Label("Message: ");
        subjectField.setMinSize(200,50);
        subjectField.setMaxSize(200,50);
        TextField bodyField = new TextField();
        bodyField.setMinSize(400,200);
        bodyField.setMaxSize(400,200);

        pane.getChildren().add(viewUserLabel);
        pane.getChildren().add(subject);
        pane.getChildren().add(subjectField);
        pane.getChildren().add(message);
        pane.getChildren().add(bodyField);

        Button submit = new Button("Send");
        submit.setOnAction(event -> {
            controller.sendMessage(guiManager.getController().getUserStateManager().getCurrentViewingUser(), subjectField.getText(), bodyField.getText());
            System.out.println("current viewing user " + guiManager.getController().getUserStateManager().getCurrentViewingUser());
            subjectField.clear();
            bodyField.clear();
            viewUserLabel.setText("Message Sent!");
        });
        pane.getChildren().add(submit);
        return pane;
    }
}
