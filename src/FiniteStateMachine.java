/**
 * Created by James on 5/27/2017.
 */
public class FiniteStateMachine {
    public static final String ADD_USER_STATE = "addUser";
    public static final String ADD_CLASS_STATE = "addClass";
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
