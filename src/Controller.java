/**
 * Created by James on 5/25/2017.
 */
public class Controller {

    UserDataManager manager;
    ClassParticipantManager classParticipantManager;
    ClassInformationManager classInformationManager;

    public static void main(String argsp[]){


        //User user = new User("William","Tell","jhoffman1204","hoffman1996","CSE","Junior","github.com/jhoffman1204","jamesuhoffman.com","219","james.a.hoffman@stonybrook.edu");
        //manager.addData(user);
        //User tempUser = manager.retrieveData("dcooper123");

        //manager.displayAllUsersInDatabase();

        //cpManager.init();
        //ClassParticipant temp = new ClassParticipant("CSE 219","jhoffman1204","CSE","sophomore");
        //cpManager.addData(temp);


    }
    public void init(){
        manager = new UserDataManager();
        classParticipantManager = new ClassParticipantManager();
        classInformationManager = new ClassInformationManager();
    }
    public void addUserToClassInformation(){
        classInformationManager.init();
        ClassInformation ciTemp = new ClassInformation("CSE 219", "Learn design patterns and create a project" ,"Richard McKenna" ,"Spring 2017" ,"CSE 214" ,"James Hoffman");
        classInformationManager.addData(ciTemp);
        classInformationManager.closeConnection();
    }
    public void addUser(User user){
        manager.init();
        manager.addData(user);
        manager.closeConnection();
    }
}
