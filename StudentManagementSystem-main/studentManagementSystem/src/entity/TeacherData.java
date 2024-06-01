package entity;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherData {
    private int teacherId;
    private String teacherName;
    private int age;
    private String gender;
    private String phoneNumber;
    private String email;
    private String cccd;
    private List<String> subjects; // List of subjects

    public TeacherData(int teacherId, String teacherName, int age, String gender, String phoneNumber, String email, String cccd, List<String> subjects) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cccd = cccd;
        this.subjects = subjects;
    }

    // Getters and Setters
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
    
  
}
