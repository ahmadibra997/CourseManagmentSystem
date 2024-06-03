package coresy;

import java.util.ArrayList;
import java.util.List;

public class CourseTemplate {

	private String courseName;
	private String courseCode;
	private String description;
	private int avilableSeats;
	private List<StudentTemplate> students = new ArrayList<StudentTemplate>();
	private AdvisorTemplate advisor;
	private List<CourseTemplate> prerequistiesCourses = new ArrayList<CourseTemplate>();
	private String schedule;
	private int cridets;

	public CourseTemplate(String courseName, String courseCode, String description,
			int avilableSeats, AdvisorTemplate advisor,
			List<CourseTemplate> prerequistiesCourses, List<StudentTemplate> students, String schedule, int cridets) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.avilableSeats = avilableSeats;
		this.advisor = advisor;
		this.students = students;
		this.prerequistiesCourses = prerequistiesCourses;
		this.description = description;
		this.schedule = schedule;
		this.cridets = cridets;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getAvilableSeats() {
		return avilableSeats;
	}

	public void setAvilableSeats(int avilableSeats) {
		this.avilableSeats = avilableSeats;
	}

	public AdvisorTemplate getAdvisor() {
		return advisor;
	}

	public void setAdvisor(AdvisorTemplate advisor) {
		this.advisor = advisor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getCridets() {
		return cridets;
	}

	public void setCridets(int cridets) {
		this.cridets = cridets;
	}

	public List<CourseTemplate> getPrerequistiesCourses() {
		return prerequistiesCourses;
	}

	public void setPrerequistiesCourses(List<CourseTemplate> prerequistiesCourses) {
		this.prerequistiesCourses = prerequistiesCourses;
	}

	// Adds a student to the course and increments the number of enrolled students
	public boolean addStudent(StudentTemplate student) {
		if (avilableSeats > 0) {
			students.add(student);
			avilableSeats = avilableSeats - 1;
			return true;
		}
		return false;
	}

	public void advisorAddStudent(StudentTemplate student) {
		students.add(student);
	}

	public void removeStudent(StudentTemplate enrolledStudent) {
		for (StudentTemplate student : students) {
			if (student.getUsername().contentEquals(enrolledStudent.getUsername())) {
				students.remove(student);
				avilableSeats = avilableSeats + 1;
				return;
			}
		}
	}

	// Checks to see if this course is full
	public boolean courseIsFull() {
		if (avilableSeats > 0) {
			return false;
		} else {
			return true;
		}
	}

	public List<StudentTemplate> getEnrolledStudents() {
		List<StudentTemplate> enrolledStudents = new ArrayList<>();

		if (!students.isEmpty()) {
			for (StudentTemplate s : students) {
				enrolledStudents.add(s);
			}
		}

		return enrolledStudents;
	}

	public boolean hasStudent(String studentName) {
		if (!students.isEmpty()) {
			for (StudentTemplate s : students) {
				if (s.getFullName().equalsIgnoreCase(studentName)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public static List<CourseTemplate> isAvilable(List<CourseTemplate> allCourses, List<CourseTemplate> endedCourses) {
		List<CourseTemplate> avilableCourses = new ArrayList<>();
		for (CourseTemplate course : allCourses) {
			for (CourseTemplate prerequistyCourse : course.getPrerequistiesCourses()) {
				for (CourseTemplate endedCourse : endedCourses) {
					if (prerequistyCourse.getCourseCode().contentEquals(endedCourse.getCourseCode())) {
						avilableCourses.add(course);
					}
				}
			}
		}
		return avilableCourses;
	}

	public List<StudentTemplate> getStudents() {
		return students;
	}

}
