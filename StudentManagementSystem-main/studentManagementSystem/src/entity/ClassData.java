package entity;

public class ClassData {
    private int classId;
    private String className;
    private String courseName;
    private String teacherName;
    private String sroName;

    public ClassData(int classId, String className, String courseName, String teacherName, String sroName) {
        this.classId = classId;
        this.className = className;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.sroName = sroName;
    }

    public int getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSroName() {
        return sroName;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setSroName(String sroName) {
        this.sroName = sroName;
    }
}