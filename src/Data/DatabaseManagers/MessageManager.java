package Data.DatabaseManagers;

import Data.DataManager;
import Data.DataObjects.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by James on 6/3/2017.
 */
public class MessageManager extends DataManager {

    private Connection conn = null;
    public Connection init(){
        this.conn = super.init("classinformation");
        return this.conn;
    }
    public void closeConnection() {
        super.closeConnection();
    }
    @Override
    public void addData(Object obj) {
        // Creates a class participation object
        Message cp = (Message)obj;
        String query = " insert into messages(sending_user, receiving_user, subject, message)"
                + " values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, cp.getSendingUser());
            preparedStmt.setString(2, cp.getReceivingUser());
            preparedStmt.setString(3, cp.getSubject());
            preparedStmt.setString(4, cp.getBody());
            preparedStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object retrieveData(String key) {
        Message temp = new Message();
        String username = key;
        Message[] messages = new Message[30];
        int messageCounter = 0;
        try {
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM messages;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if(username.equalsIgnoreCase(rs.getString(2))) {
                    temp.setSendingUser(rs.getString(1));
                    temp.setReceivingUser(rs.getString(2));
                    temp.setSubject(rs.getString(3));
                    temp.setBody(rs.getString(4));
                    messages[messageCounter] = temp;
                    messageCounter++;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return messageCounter;
    }
    public Message[] retrieveMessages(String key) {
        Message temp = new Message();
        String username = key;
        Message[] messages = new Message[30];
        int messageCounter = 0;
        try {
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM messages;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if(username.equalsIgnoreCase(rs.getString(2))) {
                        Message temp2 = new Message();
                        //System.out.println(rs.getString(2));
                        temp2.setSendingUser(rs.getString(1));
                        temp2.setReceivingUser(rs.getString(2));
                        temp2.setSubject(rs.getString(3));
                        temp2.setBody(rs.getString(4));
                        //System.out.println(temp.getBody());
                        messages[messageCounter] = temp2;
                        //System.out.println(messages[messageCounter].getBody());
                        messageCounter++;

                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return messages;
    }
}
