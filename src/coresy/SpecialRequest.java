package coresy;

public class SpecialRequest {
    private String student;
    private String course;

    public SpecialRequest(String student, String course) {
        this.student = student;
        this.course = course;
    }

    public String getStudent() {
        return student;
    }

    public String getCourse() {
        return course;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public void setCourse(String course) {
        this.course = course;
    }

}
