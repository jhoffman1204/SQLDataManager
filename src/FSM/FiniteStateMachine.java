package FSM;

/**
 * Created by James on 5/27/2017.
 */
public class FiniteStateMachine {
    /**
     * The state manager controls which buttons need to be visible
     * 0. Create Profile Button
     * 1. Create Class Button
     * 2. Login Button
     * 3. Logout Button
     * 4. Search Bar
     * 5. Send Collab Request
     * 6. View Messages
     * 7. View Classes
     * 8. Add User
     * 9. My Profile
     * @return
     */
     ////                                                0123456789
    public static final String LOGGED_OUT_STATE       = "1010000000";
    public static final String LOGGED_IN_STATE        = "0101101101";
    public static final String VIEW_USER_STATE        = "0101121101";
    public static final String VIEW_OWN_PROFILE_STATE = "0101121101";
    public static final String VIEW_CLASS_AS_ADMIN    = "0101101121";
    public static final String VIEW_CLASS_AS_USER     = "0101101101";
    private String currentState;
    public void init(){

    }
    public String getCurrentState(){
        return this.currentState;
    }
    public void setState(String state){
        this.currentState = state;
    }
}
