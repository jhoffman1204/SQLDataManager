/**
 * Created by James on 5/25/2017.
 */
public class Controller {

    UserDataManager userManager;
    ClassParticipantManager classParticipantManager;
    ClassInformationManager classInformationManager;
    FiniteStateMachine fsm;
    User currentUser;
    public static void main(String argsp[]){

    }

    /**
     * This class is initialized by the GUIManager and the main instance will be accessed from there
     */
    public void init(){
        userManager = new UserDataManager();
        classParticipantManager = new ClassParticipantManager();
        classInformationManager = new ClassInformationManager();
        fsm = new FiniteStateMachine();
        fsm.init();
    }
    public void addUserToClassInformation(){
        classInformationManager.init();
        ClassInformation ciTemp = new ClassInformation("CSE 219", "Learn design patterns and create a project" ,"Richard McKenna" ,"Spring 2017" ,"CSE 214" ,"James Hoffman");
        classInformationManager.addData(ciTemp);
        classInformationManager.closeConnection();
    }
    public void addUser(User user){
        userManager.init();
        userManager.addData(user);
        userManager.closeConnection();
        this.currentUser = user;
    }
    public void addClass(ClassInformation classInformation){
        classInformationManager.init();
        classInformationManager.addData(classInformation);
        classInformationManager.closeConnection();
    }

    /**
     * Changes the setState of the Finite State Machine
     * @param state
     */
    public void setState(String state){
        fsm.setState(state);
    }
    /**
     * sets the current user to the one corresponding to the username entered if both exist are are correct
     * @param usernameOrEmail: either going to be a username or an email entered
     * @param password: the password that the user thinks is the correct one
     * @return : returns true if the username and password exist and false if it does not
     */
    public boolean login(String usernameOrEmail, String password){
        userManager.init();
        User temp = userManager.retrieveData(usernameOrEmail);
        if(temp == null){
            userManager.closeConnection();
            return false;
        }
        if(temp.getPassword().equalsIgnoreCase(password)){
            currentUser = temp;
            System.out.println("You have successfully logged in");
            userManager.closeConnection();
            return true;
        }
        else {
            return false;
        }
    }
    public User searchForUser(String username){
        userManager.init();
        User temp = userManager.retrieveData(username);
        if(temp == null){
            userManager.closeConnection();
            return null;
        }
        else {
            userManager.closeConnection();
            return temp;
        }
    }
    public void logout(){
        this.currentUser = null;
    }

    public FiniteStateMachine getFsm(){
        return this.fsm;
    }
    public User getCurrentUser(){
        return this.currentUser;
    }
}
