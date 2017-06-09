package Data.DataObjects;

/**
 * Created by James on 5/25/2017.
 */
public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String major;
    private String year;
    private String git;
    private String website;
    private String courses;
    private String email;
    private int studentID;
    public User() {

    }
    public User(String firstName, String lastName, String username, String password, String major, String year, String git, String website, String courses, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.major = major;
        this.year = year;
        this.git = git;
        this.website = website;
        this.courses = courses;
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getGit(){
        return this.git;
    }
    public void setGit(String git){
        this.git = git;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

}
