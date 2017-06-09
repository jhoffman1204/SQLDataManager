package Data.DatabaseManagers;

import Data.DataObjects.ClassParticipant;
import Data.DataManager;
import Data.DataObjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

    @Override
    public User retrieveData(String username) {
        return null;
    }
}
