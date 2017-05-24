import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadDataManager {

    public void readFromDatabase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //Connection conn = DriverManager.getConnection("jdbc:mysql://77.104.151.241/stonybr8_test?" +
            //"user=stonybr8_james&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            // this also does not give me an error
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/integrateJava?" +
                    "verifyServerCertificate=false&user=root&password=hoffman96&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");

            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM users;");
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                for(int i = 1; i < 7; i++){
                    System.out.print(rs.getString(i));
                    System.out.print(" : ");
                }
                System.out.println("");
            }
        }
        catch(Exception e){

        }

    }

}
