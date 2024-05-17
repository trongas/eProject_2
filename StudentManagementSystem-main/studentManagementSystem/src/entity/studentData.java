package entity;

import java.sql.Date;

public class StudentData {

    private Integer studentNum;
    private String year;
    private String courseName;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birth;
    private String status;
    private String image;
    private Double firstSem;
    private Double secondSem;
    private Double finals;

    public StudentData(Integer studentNum, String year, String courseName, String firstName, String lastName, String gender, Date birth, String status, String image) {
        this.studentNum = studentNum;
        this.year = year;
        this.courseName = courseName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birth = birth;
        this.status = status;
        this.image = image;
    }

    public StudentData(Integer studentNum, String year, String courseName, Double firstSem, Double secondSem, Double finals) {
        this.studentNum = studentNum;
        this.year = year;
        this.courseName = courseName;
        this.firstSem = firstSem;
        this.secondSem = secondSem;
        this.finals = finals;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public String getYear() {
        return year;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirth() {
        return birth;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public Double getFirstSem() {
        return firstSem;
    }

    public Double getSecondSem() {
        return secondSem;
    }

    public Double getFinals() {
        return finals;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFirstSem(Double firstSem) {
        this.firstSem = firstSem;
    }

    public void setSecondSem(Double secondSem) {
        this.secondSem = secondSem;
    }

    public void setFinals(Double finals) {
        this.finals = finals;
    }
    
}
