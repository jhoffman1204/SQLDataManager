package Data.DataObjects;

/**
 * Created by James on 6/3/2017.
 */
public class Message {
    private String sendingUser;
    private String receivingUser;
    private String subject;
    private String body;

    public Message(String sendingUser, String receivingUser, String subject, String body) {
        this.sendingUser = sendingUser;
        this.receivingUser = receivingUser;
        this.subject = subject;
        this.body = body;
    }
    public Message(){

    }
    public String getSendingUser() {
        return sendingUser;
    }

    public void setSendingUser(String sendingUser) {
        this.sendingUser = sendingUser;
    }

    public String getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(String receivingUser) {
        this.receivingUser = receivingUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
