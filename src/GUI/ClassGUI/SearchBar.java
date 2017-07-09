package GUI.ClassGUI;

import Data.DataObjects.ClassInformation;
import Data.DataObjects.User;
import FSM.FiniteStateMachine;
import GUI.GUIManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import Controller.Controller;

/**
 * Created by James Hoffman on 7/8/2017.
 */
public class SearchBar {
    /**
     * Creates a Textfield that allows one to search for a user
     *   - calls the controller's method to search for a user
     * @return: The HBox that will contain the search box and search button
     */
    public static HBox createSearchBar(Controller controller, GUIManager guiManager){
        HBox searchBar = new HBox();

        TextField searchField = new TextField();
        Button submitSearch = new Button("Search");
        searchBar.getChildren().add(searchField);
        searchBar.getChildren().add(submitSearch);
        submitSearch.setOnAction(e -> {
            User temp = controller.searchForUser(searchField.getText());
            if(temp != null){
                controller.getFsm().setState(FiniteStateMachine.VIEW_USER_STATE);
                guiManager.setCurrentViewingUser(temp.getUsername());
                UserPage userPage = new UserPage(guiManager);
                guiManager.setAsBodyPane(userPage.generateUserPage(temp));
                guiManager.updateMenuBarState();
                //System.out.println("current viewing user is " + temp.getUsername());
            }
            ClassInformation classInformation = controller.searchForClass(searchField.getText());
            if(classInformation != null){
                //setAsBodyPane(createClassPage(classInformation));
            }
        });
        return searchBar;
    }
}
