package Controller;

import ModelData.DataObjects.*;
import ModelData.DatabaseManagers.*;
import FSM.FiniteStateMachine;
import FSM.UserStateManager;
import View.GUIManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by James on 5/25/2017.
 */
public class Controller {

    UserDataManager userManager;
    ClassParticipantManager classParticipantManager;
    ClassInformationManager classInformationManager;
    MessageManager messageManager;
    MicropostManager micropostManager;
    FiniteStateMachine fsm;
    GUIManager guiManager;
    User currentUser;
    UserStateManager userStateManager;


    /**
     * This class is initialized by the View.GUIManager and the main instance will be accessed from there
     */
    public void init(GUIManager guiManager){
        userManager = new UserDataManager();
        classParticipantManager = new ClassParticipantManager();
        classInformationManager = new ClassInformationManager();
        messageManager = new MessageManager();
        micropostManager = new MicropostManager();
        userStateManager = new UserStateManager();
        fsm = new FiniteStateMachine();
        fsm.init();
        this.guiManager = guiManager;

//        messageManager.init();
//        ModelData.DataObjects.Message[] messages = this.retrieveMessages("dcooper123");
    }
    public void addUser(User user){
        userManager.init();
        userManager.addData(user);
        userManager.closeConnection();
        this.currentUser = user;
    }
    public boolean userExist(User user){
        userManager.init();
        boolean a = userManager.userExist(user);
        userManager.closeConnection();
        return a;
    }
    public void addClass(ClassInformation classInformation){
        classInformationManager.init();
        classInformationManager.addData(classInformation);
        classInformationManager.closeConnection();
    }
    public void changeBio(String newBio){

    }
    public void addUserToClass(User user, String course){
        classParticipantManager.init();
                ClassParticipant participant = new ClassParticipant(course,
                user.getUsername(),
                user.getMajor(),
                user.getYear());
        classParticipantManager.addData(participant);
        classParticipantManager.closeConnection();
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
        if(temp.getPassword().equalsIgnoreCase(encrpyPassword(password).substring(0,15))){
            currentUser = temp;
            System.out.println("You have successfully logged in");
            userManager.closeConnection();
            return true;
        }
        else {
            return false;
        }
    }
    public String encrpyPassword(String password)
    {
        String pass = password;
        StringBuffer sb = null;
        try {
            MessageDigest a = MessageDigest.getInstance("MD5");
            a.update(password.getBytes());
            byte[] b = a.digest();
            sb= new StringBuffer();
            for(byte b1 : b)
            {
                sb.append(Integer.toHexString(b1 & 0xff).toString());
            }
        }
        catch(NoSuchAlgorithmException e) {
            System.out.println("there was a problem with the Message Digest method");
        }
        return sb.toString();
    }
    public ClassInformation[] retrieveCoursesTakenByStudent(String username){
        classParticipantManager.init();
        ClassInformation[] a = classParticipantManager.retrieveClassesFromUser(username,this);
        classParticipantManager.closeConnection();
        return a;
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
        System.out.println(this.currentUser.getUsername());
        messageManager.init();
        messageManager.addData(newMessage);
        messageManager.closeConnection();
    }
    public Message[] retrieveMessages(String username){
        messageManager.init();
        Message[] a = messageManager.retrieveMessages(username);
        messageManager.closeConnection();
        return a;
    }
    public void postMicroPost(Micropost micropost){
        micropostManager.init();
        micropostManager.addData(micropost);
        micropostManager.closeConnection();
    }
    public Micropost[] retrievePosts(String username){
        micropostManager.init();
        Micropost[] a = (Micropost[]) micropostManager.retrieveData(username);
        micropostManager.closeConnection();
        return a;
    }

    /**
     * Retrieves class that a user is the admin of
     * @param username: the admin to retrieve their classes
     * @return: An array of ClassInformation Objects
     */
    public ClassInformation[] retrieveClasses(String username){
        classInformationManager.init();
        ClassInformation[] a = classInformationManager.retrieveClasses(username,true, false);
        classInformationManager.closeConnection();

        classParticipantManager.init();
        //ClassInformation[] b = classParticipantManager.
        return a;
    }

    /**
     * Returns all the User Objects that are in a class
     * @param classname: the name of the class
     * @return a User[] array which contains all of the user objects
     */
    public User[] getParticipantsOfClass(String classname){
        classParticipantManager.init();
        userManager.init();
        String[] usernames = classParticipantManager.getParticipantsUsernames(classname);
        User[] userObjects = userManager.retrieveArrayOfUsers(usernames);
        classParticipantManager.closeConnection();
        userManager.closeConnection();
        return userObjects;
    }
    public User retrieveUser(String username){
        userManager.init();
        User user = userManager.retrieveData(username);
        userManager.closeConnection();
        return user;

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
    public void setCurrentUser(User user){
        this.currentUser = user;
    }
    public void setState(String state)
    {
        fsm.setState(state);
    }
    public UserStateManager getUserStateManager() {
        return userStateManager;
    }
    public void setUserStateManager(UserStateManager userStateManager) {
        this.userStateManager = userStateManager;
    }
}
