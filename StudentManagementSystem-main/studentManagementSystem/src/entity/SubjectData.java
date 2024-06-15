package entity;

import java.util.List;


public class SubjectData {

    private int subjectId;
    private String subjectName;
    private List<String> courseName;

    public SubjectData() {
    }

    public SubjectData(int subjectId, String subjectName, List<String> courseName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseName = courseName;
    }

    public SubjectData(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
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

    public List<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(List<String> courseName) {
        this.courseName = courseName;
    }

    
    @Override
    public String toString() {
        return subjectName;
    }
}
