package GUI.ClassGUI;

import Data.DataObjects.Message;
import GUI.GUIManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/7/2017.
 */
public class MessagesPane {

    public static HBox createInbox(Controller controller, GUIManager guiManager){
        HBox pane = new HBox();
        VBox subjects = new VBox();
        VBox showMessage = new VBox();
        pane.getChildren().add(subjects);
        pane.getChildren().add(showMessage);
        Label message = new Label("message");
        Label sender  = new Label("Sent by: ");

        Message[] messages = controller.retrieveMessages(controller.getCurrentUser().getUsername());
        System.out.println("the current user is: " + controller.getCurrentUser().getUsername());
        for(int i = 0; i < messages.length; i++){
            if(messages[i] != null) {
                Button button = new Button(messages[i].getSubject());
                String messagereceieved = messages[i].getBody();
                String senderText = messages[i].getSendingUser();
                button.setOnAction(event -> {
                    sender.setText("Sent by: " + senderText);
                    message.setText(messagereceieved);

                });
                subjects.getChildren().add(button);
            }
        }
        showMessage.getChildren().add(sender);
        showMessage.getChildren().add(message);
        pane.setMargin(showMessage, new Insets(0,0,0,50));
        return pane;
    }

}
