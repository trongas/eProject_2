package entity;

import javafx.scene.control.cell.CheckBoxListCell;

public class SubjectData {

    private int subjectId;
    private String subjectName;
    private String courseName;
    private boolean selected;

    public SubjectData(int subjectId, String subjectName, String courseName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseName = courseName;
    }

    public SubjectData(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.selected = false; // Mặc định không được chọn
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return subjectName;
    }
}
