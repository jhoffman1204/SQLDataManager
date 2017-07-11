package FSM;

/**
 * Created by James Hoffman on 7/10/2017.
 */
public class UserStateManager {

    String currentUser;
    String currentViewingUser;
    String selectedClass;

    public UserStateManager(){

    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentViewingUser() {
        return currentViewingUser;
    }

    public void setCurrentViewingUser(String currentViewingUser) {
        this.currentViewingUser = currentViewingUser;
    }

    public String getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(String selectedClass) {
        this.selectedClass = selectedClass;
    }



}
