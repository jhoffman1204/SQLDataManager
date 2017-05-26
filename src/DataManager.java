import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;

/**
 * Created by James on 5/25/2017.
 */
public class DataManager {

    private String currentDatabaseConnectionURL = "jdbc:mysql://localhost/integrateJava?" +
            "verifyServerCertificate=false&user=root&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String localHostConnectionURL = "jdbc:mysql://localhost/CodeDash?" +
            "verifyServerCertificate=false&user=root&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String onlineDataBaseConnectionURL = "jdbc:mysql://77.104.151.241/stonybr8_test?" +
             "user=stonybr8_james&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection conn = null;
    public void init(){
        connectToDataBase(localHostConnectionURL);
    }
    public void connectToDataBase(String connectionURL){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addUser(String firstName, String lastName, String username, String password, String major, String year, String git, String website, String courses, String email) {
        // create a sql date object so we can use it in our INSERT statement
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        // the mysql insert statement
        String query = " insert into users (first_name, last_name, username, password, major, year, git, website,courses,email)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, firstName);
            preparedStmt.setString(2, lastName);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, password);
            preparedStmt.setString(5, major);
            preparedStmt.setString(6, year);
            preparedStmt.setString(7, git);
            preparedStmt.setString(8, website);
            preparedStmt.setString(9, courses);
            preparedStmt.setString(10, email);

            preparedStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
