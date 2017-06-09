package Data.DataObjects;

/**
 * Created by James on 5/26/2017.
 */
public class ClassInformation {

    private String class_name;
    private String description;
    private String professor;
    private String semester;
    private String prerequisites;
    private String admins;

    public ClassInformation(){

    }
    public ClassInformation(String class_name, String description, String professor, String semester, String prerequisites, String admins) {
        this.class_name = class_name;
        this.description = description;
        this.professor = professor;
        this.semester = semester;
        this.prerequisites = prerequisites;
        this.admins = admins;
    }
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getAdmins() {
        return admins;
    }

    public void setAdmins(String admins) {
        this.admins = admins;
    }
}
