import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.*;
import java.util.Calendar;

public class WriteDataManager {
    public void InsertUserIntoUsersTable(String firstName, String lastName, Boolean is_admin, int score){
        try{
            // this connectionn does not give me an error
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //Connection conn = DriverManager.getConnection("jdbc:mysql://77.104.151.241/stonybr8_test?" +
            //"user=stonybr8_james&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

            // this also does not give me an error
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/integrateJava?" +
                    "verifyServerCertificate=false&user=root&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");

            // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            // the mysql insert statement
            String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, firstName);
            preparedStmt.setString (2, lastName);
            preparedStmt.setDate   (3, startDate);
            preparedStmt.setBoolean(4, is_admin);
            preparedStmt.setInt    (5, score);

            // execute the preparedstatement
            preparedStmt.execute();
            conn.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void promptUserToWriteToUserTable(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the first name of the new user: ");
        String firstName = in.nextLine();
        System.out.print("Enter the last  name of the new user: ");
        String lastName = in.nextLine();
        System.out.print("Is the person an admin (true) or (false)?: ");
        Boolean isAdmin = in.nextBoolean();
        System.out.print("What is the current score of the person: ");
        int score = in.nextInt();
        System.out.println("User Created");
        this.InsertUserIntoUsersTable(firstName,lastName,isAdmin,score);
    }
    public void deleteData(){
        try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/integrateJava?" +
                    "verifyServerCertificate=false&user=root&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
                String query = "delete from users where first_name = 'james';";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.execute();
                conn.close();
        }
        catch(Exception e){

        }
    }
    public void simpleQuery(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://77.104.151.241/stonybr8_test?" +
                    "user=stonybr8_james&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//            String query = ("CREATE TABLE student(" +
//                    "first_name VARCHAR(30) NOT NULL," +
//                    "last_name VARCHAR(30) NOT NULL," +
//                    "email VARCHAR(60) NULL," +
//                    "street VARCHAR(50) NOT NULL," +
//                    "city VARCHAR(40) NOT NULL," +
//                    "state CHAR(2) NOT NULL DEFAULT \"PA\"," +
//                    "zip MEDIUMINT UNSIGNED NOT NULL," +
//                    "phone VARCHAR(20) NOT NULL," +
//                    "birth_date DATE NOT NULL," +
//                    "sex ENUM('M', 'F') NOT NULL," +
//                    "date_entered TIMESTAMP," +
//                    "lunch_cost FLOAT NULL," +
//                    "student_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY\n" +
//                    ");");
            String query = "INSERT INTO student VALUES('Dale', 'Cooper', 'dcooper@aol.com', " +
                    "\t'123 Main St', 'Yakima', 'WA', 98901, '792-223-8901', \"1959-2-22\"," +
                    "\t'M', NOW(), 3.50, NULL);";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
