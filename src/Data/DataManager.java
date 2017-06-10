package Data;

import java.sql.*;

/**
 * Created by James on 5/25/2017.
 */
public abstract class DataManager {

    private String currentDatabaseConnectionURL = "";
    private String localHostConnectionURL = "jdbc:mysql://localhost/CodeDash?" +
            "verifyServerCertificate=false&user=root&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String onlineDataBaseConnectionURL = "jdbc:mysql://77.104.151.241/stonybr8_test?" +
             "user=stonybr8_james&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection conn = null;
    private String currentlySelectedTable = "";

    /**
     * Child classes implementation works as follows:
     * Data.DatabaseManagers.UserDataManager takes a user object and adds that to the users database
     * ClassParticipationManager takes in a classParticipation object and adds that to the database
     *
     * @param obj
     */
    public abstract void addData(Object obj);
    public abstract Object retrieveData(String key);
    /**
     * This method will only be called by child class of Data.DataManager. The initialization will set the table to the class that it was initialized by.
     * The current database that is being connected to is decided by the developer
     * Calls the connectToDataBase() method
     * @param table: the table that is currently being manipulated
     * @return: a Connection that a child class will use
     */
    public Connection init(String table){
        //this.createTable();

        this.currentDatabaseConnectionURL = onlineDataBaseConnectionURL;
        this.currentlySelectedTable = table;
        return connectToDataBase(this.currentDatabaseConnectionURL);
    }
    public void createTable(){
        try{
            String onlineDataBaseConnectionURL = "jdbc:mysql://77.104.151.241/stonybr8_test?" +
                    "user=stonybr8_james&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(onlineDataBaseConnectionURL);

            String query = "CREATE TABLE users(" +
                    "first_name VARCHAR(30) NOT NULL," +
                    "last_name VARCHAR(30) NOT NULL," +
                    "username VARCHAR(15) NOT NULL," +
                    "password VARCHAR(15) NOT NULL," +
                    "major VARCHAR(15) NOT NULL," +
                    "year VARCHAR(15) NOT NULL," +
                    "git VARCHAR(45) NOT NULL," +
                    "website VARCHAR(45) NOT NULL," +
                    "courses VARCHAR(60) NOT NULL," +
                    "student_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY" +
                    "email VARCHAR(60) NOT NULL," +
                    ");";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
        }
        catch(Exception e){

        }
    }
    public Connection connectToDataBase(String connectionURL){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conn = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.conn;
    }
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAllUsersInDatabase(int columns){
        try{
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM " + currentlySelectedTable + ";");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                for(int i = 1; i < (columns+1); i++){
                    System.out.print(rs.getString(i));
                    System.out.print(" : ");
                }
                System.out.println("");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
