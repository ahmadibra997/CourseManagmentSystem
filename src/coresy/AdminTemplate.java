package coresy;

import java.util.List;

public class AdminTemplate extends UserTemplate {

	public AdminTemplate(int userId, String username, String password, String firstName, String lastName) {
		super(userId, username, password, firstName, lastName, "Admin");
		
	}

	// Create a course

	public void addCourse(CourseTemplate course, List<CourseTemplate> courses) {
		courses.add(course);
	}

	// Delete a Course
	/* public void removeCourse(Course course, List<Course> courses) {
		if (courses.contains(course)) {
			courses.remove(course);
		}
	} */

	// Create a User
	public void addUser(UserTemplate user, List<UserTemplate> users) {
		if (user.getRole() == users.get(0).getRole()) {
			users.add(user);
		}
	}

	// Edit a course

	// Display information by course ID

	// View courses that are full

	// Write to a file the list of courses that are full

	// Sort the courses based on student count

	// View Courses

	// Used in later method

	// View the list of courses that a given student is being registered on

	// View the names of students being registered in a specific course

}
