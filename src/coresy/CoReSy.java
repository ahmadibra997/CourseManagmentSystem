package coresy;

import java.util.List;

import coresy.userinterface.LoginUI;

public class CoReSy {

    public static void main(String[] args) {

        List<CourseTemplate> courses = JsonHandler.readCoursesFromJson("src/coresy/systemdata/courses.json");

        List<AdminTemplate> admins = JsonHandler.readAdminsFromJson("src/coresy/systemdata/admins.json");

        List<StudentTemplate> students = JsonHandler.readStudentsFromJson("src/coresy/systemdata/students.json");

        List<AdvisorTemplate> advisors = JsonHandler.readAdvisorsFromJson("src/coresy/systemdata/advisors.json");

        LoginUI loginUI = new LoginUI(students, advisors, admins, courses);
        
        loginUI.setVisible(true);

    }

}