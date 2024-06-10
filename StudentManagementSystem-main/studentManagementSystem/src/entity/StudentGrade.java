package entity;

public class StudentGrade {
    private int gradeId;
    private String studentName;
    private String subject;
    private double score;
    private String status;

    public StudentGrade(int gradeId, String studentName, String subject, double score, String status) {
        this.gradeId = gradeId;
        this.studentName = studentName;
        this.subject = subject;
        this.score = score;
        this.status = status;
    }
    

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
