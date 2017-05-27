import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public User retrieveData(String username) {
        return null;
    }
}