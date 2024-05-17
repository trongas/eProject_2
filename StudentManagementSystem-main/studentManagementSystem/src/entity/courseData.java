package entity;

public class CourseData {
    private int Id;
    private String course;
    private String description;
    private String degree;

    public CourseData(String course, String description, String degree) {
        this.course = course;
        this.description = description;
        this.degree = degree;
    }

    public CourseData(int Id, String course) {
        this.course = course;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

}
