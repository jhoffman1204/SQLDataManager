package ModelData.DataObjects;

/**
 * Created by James Hoffman on 6/20/2017.
 */
public class Micropost {

    public String title;
    public String post;
    public String user;

    public Micropost(String title, String post, String user) {
        this.title = title;
        this.post = post;
        this.user = user;
    }

    public Micropost(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
