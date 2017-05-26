/**
 * Created by James on 5/25/2017.
 */
public class Controller {
    public static void main(String argsp[]){

        UserDataManager manager = new UserDataManager();
        ClassParticipantManager cpManager = new ClassParticipantManager();
        ClassInformationManager ciManager = new ClassInformationManager();

        //manager.init();

        //User user = new User("William","Tell","jhoffman1204","hoffman1996","CSE","Junior","github.com/jhoffman1204","jamesuhoffman.com","219","james.a.hoffman@stonybrook.edu");
        //manager.addData(user);
        //User tempUser = manager.retrieveData("dcooper123");

        //manager.displayAllUsersInDatabase();

        //cpManager.init();
        //ClassParticipation temp = new ClassParticipation("CSE 219","jhoffman1204","CSE","sophomore");
        //cpManager.addData(temp);

        ciManager.init();
        ClassInformation ciTemp = new ClassInformation("CSE 219", "Learn design patterns and create a project" ,"Richard McKenna" ,"Spring 2017" ,"CSE 214" ,"James Hoffman");
        ciManager.addData(ciTemp);
        ciManager.closeConnection();

        //manager.closeConnection();

    }

}
