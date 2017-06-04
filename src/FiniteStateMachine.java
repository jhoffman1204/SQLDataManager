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
     *
     * 0 = not visible
     * 1 = top menu bar
     * 2 = left menu bar
     * @return
     *////                                         012345
    public static final String LOGGED_OUT_STATE = "101000";
    public static final String LOGGED_IN_STATE  = "010110";
    public static final String VIEW_USER_STATE  = "010112";
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
