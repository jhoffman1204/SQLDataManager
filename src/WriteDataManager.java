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
}
