import java.sql.*;
import java.util.Calendar;

import com.mysql.cj.jdbc.MysqlDataSource;

 public class main{
    public static void main(String args[]){
        WriteDataManager writeData = new WriteDataManager();
        ReadDataManager readData = new ReadDataManager();
        //writeData.InsertUserIntoUsersTable("Edwardo", "Saverin", true, 550);
        //writeData.promptUserToWriteToUserTable();
        //readData.readFromDatabase();
        //writeData.deleteData();
        writeData.simpleQuery();
     }
 }