/**
 * Created by James on 5/27/2017.
 */
public class FiniteStateMachine {
    /**
     * The state manager controls which buttons need to be visible
     * 1. Create Profile Button
     * 2. Create Class Button
     * 3. Login Button
     * @return
     */
    public static final String LOGGED_OUT_STATE = "10100";
    public static final String LOGGED_IN_STATE  = "01011";
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
