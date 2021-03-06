package ModelData.DatabaseManagers;

import ModelData.DataManager;
import ModelData.DataObjects.User;

import java.sql.*;
import java.util.Calendar;

/**
 * Created by James on 5/26/2017.
 */
public class UserDataManager extends DataManager {
    private String currentDatabaseConnectionURL = "";
    private String localHostConnectionURL = "jdbc:mysql://localhost/CodeDash?" +
            "verifyServerCertificate=false&user=root&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String onlineDataBaseConnectionURL = "jdbc:mysql://77.104.151.241/stonybr8_test?" +
            "user=stonybr8_james&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection conn = null;
    public int getColumns() {
        return this.columns;
    }
    private int columns = 11;
    public Connection init(){
        this.conn = super.init("users");
        return this.conn;
    }
    public void closeConnection() {
        super.closeConnection();
    }
    public void displayAllUsersInDatabase() {
        super.displayAllUsersInDatabase(this.columns);
    }
    @Override
    public void addData(Object obj) {
        User user = (User)obj;
        // create a sql date object so we can use it in our INSERT statement
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        // the mysql insert statement
        String query = " insert into users (first_name, last_name, username, password, major, year, git, website,courses,email,bio)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, user.getFirstName());
            preparedStmt.setString(2, user.getLastName());
            preparedStmt.setString(3, user.getUsername());
            preparedStmt.setString(4, user.getPassword());
            preparedStmt.setString(5, user.getMajor());
            preparedStmt.setString(6, user.getYear());
            preparedStmt.setString(7, user.getGit());
            preparedStmt.setString(8, user.getWebsite());
            preparedStmt.setString(9, user.getCourses());
            preparedStmt.setString(10, user.getEmail());
            preparedStmt.setString(11, user.getBio());

            preparedStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean userExist(User user){
        if(this.retrieveData(user.getUsername()) != null){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * another useless comment
     * @param username
     * @return
     */
    @Override
    public User retrieveData(String username) {
        User user = new User();
        try {
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM users;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if(username.equalsIgnoreCase(rs.getString(3)) || username.equalsIgnoreCase(rs.getString(11))) {
                    user.setFirstName(rs.getString(1));
                    user.setLastName(rs.getString(2));
                    user.setUsername(rs.getString(3));
                    user.setPassword(rs.getString(4));
                    user.setMajor(rs.getString(5));
                    user.setYear(rs.getString(6));
                    user.setGit(rs.getString(7));
                    user.setWebsite(rs.getString(8));
                    user.setCourses(rs.getString(9));
                    user.setEmail(rs.getString(10));
                    user.setStudentID(Integer.parseInt(rs.getString(11)));
                    user.setBio(rs.getString(12));
                    return user;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public User[] retrieveArrayOfUsers(String usernames[]){
        User[] users = new User[30];
        int userCounter = 0;
        for(int i = 0; i < usernames.length; i++){
            if(usernames[i] != null){
                User temp = retrieveData(usernames[i]);
                users[userCounter] = temp;
                userCounter++;
            }
        }
        return users;
    }
}
