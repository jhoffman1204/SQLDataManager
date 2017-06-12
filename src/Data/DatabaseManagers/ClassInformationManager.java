package Data.DatabaseManagers;

import Data.DataObjects.ClassInformation;
import Data.DataManager;

import java.sql.*;

/**
 * Created by James on 5/26/2017.
 */
public class ClassInformationManager extends DataManager {

    private int columns = 7;
    private Connection conn = null;
    public Connection init(){
        this.conn = super.init("classinformation");
        return this.conn;
    }
    public void closeConnection() {
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
        ClassInformation ci = (ClassInformation) obj;
        String query = " insert into classinformation (class_name, description, professor, semester, prerequisites, admins)"
                + " values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, ci.getClass_name());
            preparedStmt.setString(2, ci.getDescription());
            preparedStmt.setString(3, ci.getProfessor());
            preparedStmt.setString(4, ci.getSemester());
            preparedStmt.setString(5, ci.getPrerequisites());
            preparedStmt.setString(6, ci.getAdmins());
            preparedStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ClassInformation retrieveData(String username) {
        ClassInformation temp = new ClassInformation();
        try {
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM classinformation;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if(username.equalsIgnoreCase(rs.getString(1))) {
                    temp.setClass_name(rs.getString(1));
                    temp.setDescription(rs.getString(2));
                    temp.setProfessor(rs.getString(3));
                    temp.setSemester(rs.getString(4));
                    temp.setPrerequisites(rs.getString(5));
                    return temp;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ClassInformation[] retrieveClasses(String username, boolean admin, boolean participant){
        ClassInformation temp = new ClassInformation();
        String usernametemp = username;
        ClassInformation[] classes = new ClassInformation[30];
        int messageCounter = 0;
        try {
            String compareString = null;
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM classinformation;");
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if(admin) {
                    compareString = rs.getString(6);
                }
                if(username.equalsIgnoreCase(compareString)) {
                    ClassInformation temp2 = new ClassInformation();
                    temp2.setClass_name(rs.getString(1));
                    temp2.setDescription(rs.getString(2));
                    temp2.setProfessor(rs.getString(3));
                    temp2.setSemester(rs.getString(4));
                    temp2.setPrerequisites(rs.getString(5));
                    temp2.setAdmins(rs.getString(6));

                    classes[messageCounter] = temp2;
                    messageCounter++;

                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return classes;
    }
}
