package ModelData.DatabaseManagers;

import ModelData.DataManager;
import ModelData.DataObjects.Micropost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by James Hoffman on 6/20/2017.
 */
public class MicropostManager extends DataManager{

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
        Micropost mp = (Micropost)obj;
        String query = " insert into microposts(title, post, user)"
                + " values (?, ?, ?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, mp.getTitle());
            preparedStmt.setString(2, mp.getPost());
            preparedStmt.setString(3, mp.getUser());
            preparedStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns an array of posts that have been made
     * @param username
     * @return
     */
    @Override
    public Object retrieveData(String username) {
        Micropost[] postArray = new Micropost[100];
        int postArrayCounter = 0;
        try {
            Statement st = conn.createStatement();
            String sql = ("SELECT * FROM microposts;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if(username.equalsIgnoreCase(rs.getString(3))) {
                    Micropost post = new Micropost();
                    post.setTitle(rs.getString(1));
                    post.setPost(rs.getString(2));
                    post.setUser(rs.getString(3));
                    postArray[postArrayCounter] = post;
                    postArrayCounter++;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return postArray;
    }
}
