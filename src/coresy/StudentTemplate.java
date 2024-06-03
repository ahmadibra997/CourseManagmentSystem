package coresy;

import java.util.ArrayList;
import java.util.List;

public class StudentTemplate extends UserTemplate {

	List<CourseTemplate> registeredCourses = new ArrayList<CourseTemplate>();
	List<CourseTemplate> endedCourses = new ArrayList<CourseTemplate>();
	String phoneNumber;

	// constructor

	public StudentTemplate(int userId, String username, String password, String firstName, String lastName,
			List<CourseTemplate> registeredCourses, List<CourseTemplate> endedCourses, String phoneNumber) {
		super(userId, username, password, firstName, lastName, "Student");
		this.registeredCourses = registeredCourses;
		this.endedCourses = endedCourses;
		this.phoneNumber = phoneNumber;
	}

	public StudentTemplate(int userId, String username, String firstName, String lastName) {
		super(userId, username, firstName, lastName, "Student");
	}

	public StudentTemplate login(String username, String password, List<StudentTemplate> students) {
		for (StudentTemplate student : students) {
			if (username.contentEquals(student.username) && (password.contentEquals(student.password))) {
				return student;
			}
		}
		return null;
	}

	public List<CourseTemplate> getEndedCourses (){
		return endedCourses;	
	}

	// View all courses that are not full
	public List<CourseTemplate> viewOpenCourses(List<CourseTemplate> allCourses) {
		List<CourseTemplate> availaCourses = new ArrayList<>();

		for (CourseTemplate course : allCourses) {
			if (course.getPrerequistiesCourses().containsAll(endedCourses)) {
				availaCourses.add(course);
			}
		}
		return availaCourses;
	}

	public void dropCourse(CourseTemplate course) {
		registeredCourses.remove(course);
	}

	public List<CourseTemplate> viewEnrolledCourses() {
		return registeredCourses;
	}

	public void registerInCourse(CourseTemplate course) {
		registeredCourses.add(course);
	}

	public List<CourseTemplate> getRegisteredCourses() {
		return registeredCourses;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}	

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
