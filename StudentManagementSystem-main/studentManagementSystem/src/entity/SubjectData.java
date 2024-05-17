package entity;

public class SubjectData {
    private int subjectId;
    private String subjectName;
    private String courseName; 

     public SubjectData(int subjectId, String subjectName, String courseName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseName = courseName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    
}
