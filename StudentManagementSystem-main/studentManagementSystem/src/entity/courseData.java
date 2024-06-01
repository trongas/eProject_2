package entity;

public class CourseData {

    public int id;
    private String course;
    private String description;
    private String degree;
    private String duration;

    public CourseData(int id, String course, String description, String degree, String duration) {
        this.id = id;
        this.course = course;
        this.description = description;
        this.degree = degree;
        this.duration = duration;
    }
    
    public CourseData(int id, String course) {
        this.id = id;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getCourse() {
        return course;
    }

    public String getDescription() {
        return description;
    }

    public String getDegree() {
        return degree;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

   

}