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
     * 6. Send Collab Request
     * 7. Show Messages
     *
     * 0 = not visible
     * 1 = top menu bar
     * 2 = left menu bar
     * @return
     *////                                         01234567
    public static final String LOGGED_OUT_STATE = "10100000";
    public static final String LOGGED_IN_STATE  = "01011011";
    public static final String VIEW_USER_STATE  = "01011211";
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
