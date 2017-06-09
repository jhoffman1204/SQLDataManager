package Data.DataObjects;

/**
 * Created by James on 5/26/2017.
 */
public class ClassParticipant {
    private String className;
    private String username;
    private String major;
    private String year;

    /**
     * for generic Data.DataObjects.ClassParticipant objects
     */
    public ClassParticipant(){

    }
    public ClassParticipant(String className, String username, String major, String year){
        this.className = className;
        this.username = username;
        this.major = major;
        this.year = year;
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
