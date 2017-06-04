/**
 * Created by James on 5/25/2017.
 */
public class Controller {

    UserDataManager userManager;
    ClassParticipantManager classParticipantManager;
    ClassInformationManager classInformationManager;
    MessageManager messageManager;
    FiniteStateMachine fsm;
    GUIManager guiManager;
    User currentUser;
    /**
     * This class is initialized by the GUIManager and the main instance will be accessed from there
     */
    public void init(GUIManager guiManager){
        userManager = new UserDataManager();
        classParticipantManager = new ClassParticipantManager();
        classInformationManager = new ClassInformationManager();
        messageManager = new MessageManager();
        fsm = new FiniteStateMachine();
        fsm.init();
        this.guiManager = guiManager;

        messageManager.init();
        Message[] messages = this.retrieveMessages("dcooper123");
        for(int i = 0; i < 4; i++){
            System.out.println(messages[i].getBody());
        }
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
    public ClassInformation searchForClass(String className){
        classInformationManager.init();
        ClassInformation temp = classInformationManager.retrieveData(className);
        if(temp == null){
            classInformationManager.closeConnection();
            return null;
        }
        else{
            classInformationManager.closeConnection();
            return temp;
        }
    }
    public void sendMessage(String desintationUser, String body, String message){

        Message newMessage = new Message(this.currentUser.getUsername(), desintationUser, body, message);
        messageManager.init();
        messageManager.addData(newMessage);
        messageManager.closeConnection();
    }
    public Message[] retrieveMessages(String username){

        return messageManager.retrieveMessages(username);

    }
    /**
     * Getter and Setter methods
     */

    public FiniteStateMachine getFsm(){
        return this.fsm;
    }
    public User getCurrentUser(){
        return this.currentUser;
    }
    public void setState(String state)
    {
        fsm.setState(state);
    }
}
