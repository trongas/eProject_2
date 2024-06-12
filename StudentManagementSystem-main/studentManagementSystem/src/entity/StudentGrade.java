package entity;

public class StudentGrade {
    private int gradeId;
    private String studentName;
    private String subject;
    private double score;
    private double maxScore;
    private double rate;
    private String status;

    public StudentGrade(int gradeId, String studentName, String subject, double score, double maxScore, double rate, String status) {
        this.gradeId = gradeId;
        this.studentName = studentName;
        this.subject = subject;
        this.score = score;
        this.maxScore = maxScore;
        this.rate = rate;
        this.status = status;
    }

    // Getters and setters
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

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getWeightedScore() {
        return score * rate ;
    }
}
