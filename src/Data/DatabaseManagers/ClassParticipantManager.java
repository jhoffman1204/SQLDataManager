package Data.DatabaseManagers;

import Controller.Controller;
import Data.DataObjects.ClassInformation;
import Data.DataObjects.ClassParticipant;
import Data.DataManager;
import Data.DataObjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by James on 5/26/2017.
 */
public class ClassParticipantManager extends DataManager {

    private int columns = 5;
    private Connection conn = null;
    public Connection init(){
        this.conn = super.init("classparticipants");
        return this.conn;
    }
    public void closeConnection(){
        super.closeConnection();
    }
    public int getColumns() {
        return this.columns;
    }
    public void displayAllUsersInDatabase() {
        super.displayAllUsersInDatabase(this.columns);
    }
    @Override
    public void addData(Object obj) {
        // Creates a class participation object
        ClassParticipant cp = (ClassParticipant)obj;
        String query = " insert into classparticipants (class_name, username, major, year)"
                + " values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, cp.getClassName());
            preparedStmt.setString(2, cp.getUsername());
            preparedStmt.setString(3, cp.getMajor());
            preparedStmt.setString(4, cp.getYear());
            preparedStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * returns a string with the names of the classes that a user is in
     **/
    public ClassInformation[] retrieveClassesFromUser(String username, Controller controller){
        ClassInformation[] classes = new ClassInformation[30];
        int counter = 0;

        try {
            String compareString = username;
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM classparticipants;");
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if(username.equalsIgnoreCase(rs.getString(2))) {
                    // if the user name matches then searches for the class with the specified class name
                    ClassInformation a = controller.searchForClass(rs.getString(1));
                    if(a != null){
                        classes[counter] = a;
                        System.out.println(rs.getString(1) + " added");
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return classes;
    }
    @Override
    public User retrieveData(String username) {
        return null;
    }
}
