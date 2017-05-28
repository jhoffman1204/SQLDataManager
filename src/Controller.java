/**
 * Created by James on 5/25/2017.
 */
public class Controller {

    UserDataManager userManager;
    ClassParticipantManager classParticipantManager;
    ClassInformationManager classInformationManager;
    FiniteStateMachine fsm;
    public static void main(String argsp[]){


        //User user = new User("William","Tell","jhoffman1204","hoffman1996","CSE","Junior","github.com/jhoffman1204","jamesuhoffman.com","219","james.a.hoffman@stonybrook.edu");
        //manager.addData(user);
        //User tempUser = manager.retrieveData("dcooper123");

        //manager.displayAllUsersInDatabase();

        //cpManager.init();
        //ClassParticipant temp = new ClassParticipant("CSE 219","jhoffman1204","CSE","sophomore");
        //cpManager.addData(temp);


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
    }
    public void addClass(ClassInformation classInformation){
        classInformationManager.init();
        classInformationManager.addData(classInformation);
        classInformationManager.closeConnection();
    }
    public void setState(String state){
        fsm.setState(state);
    }
    public void login(String usernameOrEmail, String password){
        /**
         * 1. check what is entered to every username and email that is in the database, O(2n) Operation
         * 2. check to see if the password matches the password in the field
         * 3. retrieve all user data and convert to an object that will serve as the current user object
         */
        userManager.init();
        userManager.retrieveData(usernameOrEmail);
        userManager.closeConnection();
    }
}
