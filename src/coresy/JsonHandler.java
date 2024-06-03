package coresy;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHandler {

	public static List<CourseTemplate> readCoursesFromJson(String filePath) {
		List<CourseTemplate> courses = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			JSONTokener tokener = new JSONTokener(fileInputStream);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonCourse = jsonArray.getJSONObject(i);
				String courseName = jsonCourse.getString("courseName");
				String courseCode = jsonCourse.getString("courseCode");
				String description = jsonCourse.getString("description");
				int availableSeats = jsonCourse.getInt("availableSeats");
				String schedule = jsonCourse.getString("schedule");
				int credits = jsonCourse.getInt("credits");

				// Deserialize AdvisorTemplate
				JSONObject jsonAdvisor = jsonCourse.getJSONObject("advisor");
				AdvisorTemplate advisor = new AdvisorTemplate(
						jsonAdvisor.getInt("advisorID"),
						jsonAdvisor.getString("advisorUsername"),
						jsonAdvisor.getString("advisorFirstName"),
						jsonAdvisor.getString("advisorLastName"));

				// Deserialize prerequisitesCourses
				JSONArray jsonPrerequisites = jsonCourse.optJSONArray("prerequisitesCourses");
				List<CourseTemplate> prerequisitesCourses = new ArrayList<>();
				if (jsonPrerequisites != null) {
					for (int j = 0; j < jsonPrerequisites.length(); j++) {
						JSONObject jsonPrerequisite = jsonPrerequisites.getJSONObject(j);
						// Parse prerequisite courses attributes and create CourseTemplate object
						String prereqName = jsonPrerequisite.getString("courseName");
						String prereqCode = jsonPrerequisite.getString("courseCode");
						CourseTemplate prereqCourse = new CourseTemplate(prereqName, prereqCode, "", 0, null, null,
								null, "", 0);
						prerequisitesCourses.add(prereqCourse);
					}
				}

				// Deserialize students
				JSONArray jsonStudents = jsonCourse.optJSONArray("students");
				List<StudentTemplate> students = new ArrayList<>();
				if (jsonStudents != null) {
					for (int k = 0; k < jsonStudents.length(); k++) {
						JSONObject jsonStudent = jsonStudents.getJSONObject(k);
						// Parse student attributes and create StudentTemplate object
						StudentTemplate student = new StudentTemplate(
								jsonStudent.getInt("studentID"),
								jsonStudent.getString("studentUsername"),
								jsonStudent.getString("studentFirstName"),
								jsonStudent.getString("studentLastName"));
						students.add(student);
					}
				}

				// Create CourseTemplate object
				CourseTemplate course = new CourseTemplate(courseName, courseCode, description, availableSeats, advisor,
						prerequisitesCourses, students, schedule, credits);
				courses.add(course);
			}

			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return courses;
	}

	// Function to write List<CourseTemplate> to JSON file
	public static void writeCoursesToJson(List<CourseTemplate> courses, String filePath) {
		JSONArray jsonArray = new JSONArray();
		for (CourseTemplate course : courses) {
			JSONObject jsonCourse = new JSONObject();
			jsonCourse.put("courseName", course.getCourseName());
			jsonCourse.put("courseCode", course.getCourseCode());
			jsonCourse.put("description", course.getDescription());
			jsonCourse.put("availableSeats", course.getAvilableSeats());
			jsonCourse.put("schedule", course.getSchedule());
			jsonCourse.put("credits", course.getCridets());

			// Serialize AdvisorTemplate
			JSONObject jsonAdvisor = new JSONObject();
			jsonAdvisor.put("advisorID", course.getAdvisor().getUserID());
			jsonAdvisor.put("advisorUsername", course.getAdvisor().getUsername());
			//jsonAdvisor.put("advisorPassword", course.getAdvisor().getPassword());
			jsonAdvisor.put("advisorFirstName", course.getAdvisor().getFirstName());
			jsonAdvisor.put("advisorLastName", course.getAdvisor().getLastName());
			jsonCourse.put("advisor", jsonAdvisor);

			// Serialize prerequisitesCourses
			JSONArray jsonPrerequisites = new JSONArray();
			if (course.getPrerequistiesCourses() != null) {
				for (CourseTemplate prereqCourse : course.getPrerequistiesCourses()) {
					JSONObject jsonPrerequisite = new JSONObject();
					jsonPrerequisite.put("courseName", prereqCourse.getCourseName());
					jsonPrerequisite.put("courseCode", prereqCourse.getCourseCode());
					jsonPrerequisites.put(jsonPrerequisite);
				}
			}
			jsonCourse.put("prerequisitesCourses", jsonPrerequisites);

			// Serialize students
			JSONArray jsonStudents = new JSONArray();
			if (course.getStudents() != null) {
				for (StudentTemplate student : course.getStudents()) {
					JSONObject jsonStudent = new JSONObject();
					jsonStudent.put("studentID", student.getUserID());
					jsonStudent.put("studentUsername", student.getUsername());
					jsonStudent.put("studentPassword", student.getPassword());
					jsonStudent.put("studentFirstName", student.getFirstName());
					jsonStudent.put("studentLastName", student.getLastName());
					jsonStudents.put(jsonStudent);
				}
			}
			jsonCourse.put("students", jsonStudents);

			jsonArray.put(jsonCourse);
		}

		try {
			FileWriter fileWriter = new FileWriter(filePath);
			jsonArray.write(fileWriter);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Function to read from JSON file into List<AdminTemplate>
	public static List<AdminTemplate> readAdminsFromJson(String filePath) {
		List<AdminTemplate> admins = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			JSONTokener tokener = new JSONTokener(fileInputStream);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonAdmin = jsonArray.getJSONObject(i);
				int userId = jsonAdmin.getInt("userId");
				String username = jsonAdmin.getString("username");
				String password = jsonAdmin.getString("password");
				String firstName = jsonAdmin.getString("firstName");
				String lastName = jsonAdmin.getString("lastName");
				AdminTemplate admin = new AdminTemplate(userId, username, password, firstName, lastName);
				admins.add(admin);
			}

			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return admins;
	}

	// Function to write List<AdminTemplate> to JSON file
	public static void writeAdminsToJson(List<AdminTemplate> admins, String filePath) {
		JSONArray jsonArray = new JSONArray();
		for (AdminTemplate admin : admins) {
			JSONObject jsonAdmin = new JSONObject();
			jsonAdmin.put("userId", admin.getUserID());
			jsonAdmin.put("username", admin.getUsername());
			jsonAdmin.put("password", admin.getPassword());
			jsonAdmin.put("firstName", admin.getFirstName());
			jsonAdmin.put("lastName", admin.getLastName());
			jsonArray.put(jsonAdmin);
		}

		try {
			FileWriter fileWriter = new FileWriter(filePath);
			jsonArray.write(fileWriter);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Function to read from JSON file into List<StudentTemplate>
	public static List<StudentTemplate> readStudentsFromJson(String filePath) {
		List<StudentTemplate> students = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			JSONTokener tokener = new JSONTokener(fileInputStream);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonStudent = jsonArray.getJSONObject(i);
				int userId = jsonStudent.getInt("userId");
				String username = jsonStudent.getString("username");
				String password = jsonStudent.getString("password");
				String firstName = jsonStudent.getString("firstName");
				String lastName = jsonStudent.getString("lastName");
				String phoneNumber = jsonStudent.getString("phoneNumber");

				// Deserialize registeredCourses
				JSONArray jsonRegisteredCourses = jsonStudent.optJSONArray("registeredCourses");
				List<CourseTemplate> registeredCourses = new ArrayList<>();
				if (jsonRegisteredCourses != null) {
					for (int j = 0; j < jsonRegisteredCourses.length(); j++) {
						JSONObject jsonCourse = jsonRegisteredCourses.getJSONObject(j);
						// Parse course attributes and create CourseTemplate object
						String courseName = jsonCourse.getString("courseName");
						String courseCode = jsonCourse.getString("courseCode");
						CourseTemplate course = new CourseTemplate(courseName, courseCode, "", 0, null, null, null,
								null, 0);
						registeredCourses.add(course);
					}
				}

				// Deserialize endedCourses
				JSONArray jsonEndedCourses = jsonStudent.optJSONArray("endedCourses");
				List<CourseTemplate> endedCourses = new ArrayList<>();
				if (jsonEndedCourses != null) {
					for (int k = 0; k < jsonEndedCourses.length(); k++) {
						JSONObject jsonCourse = jsonEndedCourses.getJSONObject(k);
						// Parse course attributes and create CourseTemplate object
						String courseName = jsonCourse.getString("courseName");
						String courseCode = jsonCourse.getString("courseCode");
						CourseTemplate course = new CourseTemplate(courseName, courseCode, "", 0, null, null, null,
								null, 0);
						endedCourses.add(course);
					}
				}

				StudentTemplate student = new StudentTemplate(userId, username, password, firstName, lastName,
						registeredCourses, endedCourses, phoneNumber);
				students.add(student);
			}

			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}

	// Function to write List<StudentTemplate> to JSON file
	public static void writeStudentsToJson(List<StudentTemplate> students, String filePath) {
		JSONArray jsonArray = new JSONArray();
		for (StudentTemplate student : students) {
			JSONObject jsonStudent = new JSONObject();
			jsonStudent.put("userId", student.getUserID());
			jsonStudent.put("username", student.getUsername());
			jsonStudent.put("password", student.getPassword());
			jsonStudent.put("firstName", student.getFirstName());
			jsonStudent.put("lastName", student.getLastName());
			if (student.getPhoneNumber() != null) {
				jsonStudent.put("phoneNumber", student.getPhoneNumber());
			} else { jsonStudent.put("phoneNumber", ""); }

			// Serialize registeredCourses
			JSONArray jsonRegisteredCourses = new JSONArray();
			if (student.viewEnrolledCourses() != null) {
				System.out.println("Serialize registeredCourses for student " + student.getUserID());
				for (CourseTemplate course : student.viewEnrolledCourses()) {
					System.out.println(
							"Serialize course " + course.getCourseName() + " for student " + student.getUserID());
					JSONObject jsonCourse = new JSONObject();
					jsonCourse.put("courseName", course.getCourseName());
					jsonCourse.put("courseCode", course.getCourseCode());
					jsonRegisteredCourses.put(jsonCourse);
				}
			}
			jsonStudent.put("registeredCourses", jsonRegisteredCourses);

			// Serialize endedCourses
			JSONArray jsonEndedCourses = new JSONArray();
			if (student.getEndedCourses() != null) {
				for (CourseTemplate course : student.getEndedCourses()) {
					JSONObject jsonCourse = new JSONObject();
					jsonCourse.put("courseName", course.getCourseName());
					jsonCourse.put("courseCode", course.getCourseCode());
					jsonEndedCourses.put(jsonCourse);
				}
			}
			jsonStudent.put("endedCourses", jsonEndedCourses);

			jsonArray.put(jsonStudent);
		}

		try {
			FileWriter fileWriter = new FileWriter(filePath);
			jsonArray.write(fileWriter);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Function to read from JSON file into List<AdvisorTemplate>
	public static List<AdvisorTemplate> readAdvisorsFromJson(String filePath) {
		List<AdvisorTemplate> advisors = new ArrayList<>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			JSONTokener tokener = new JSONTokener(fileInputStream);
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonAdvisor = jsonArray.getJSONObject(i);
				int userId = jsonAdvisor.getInt("userId");
				String username = jsonAdvisor.getString("username");
				String password = jsonAdvisor.getString("password");
				String firstName = jsonAdvisor.getString("firstName");
				String lastName = jsonAdvisor.getString("lastName");

				List<SpecialRequest> specialRequests = new ArrayList<>();
				// Deserialize specialRequests
				JSONArray jsonSpecialRequests = jsonAdvisor.optJSONArray("specialRequests");
				if (jsonSpecialRequests != null) {
					for (int j = 0; j < jsonSpecialRequests.length(); j++) {
						JSONObject jsonRequest = jsonSpecialRequests.getJSONObject(j);
						String student = jsonRequest.getString("student");
						String course = jsonRequest.getString("course");
						SpecialRequest specialRequest = new SpecialRequest(student, course);
						specialRequests.add(specialRequest);
					}

				}
				AdvisorTemplate advisor = new AdvisorTemplate(userId, username, password, firstName, lastName,
						specialRequests);
				advisors.add(advisor);
			}

			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return advisors;
	}

	// Function to write List<AdvisorTemplate> to JSON file
	public static void writeAdvisorsToJson(List<AdvisorTemplate> advisors, String filePath) {
		JSONArray jsonArray = new JSONArray();
		for (AdvisorTemplate advisor : advisors) {
			JSONObject jsonAdvisor = new JSONObject();
			jsonAdvisor.put("userId", advisor.getUserID());
			jsonAdvisor.put("username", advisor.getUsername());
			jsonAdvisor.put("password", advisor.getPassword());
			jsonAdvisor.put("firstName", advisor.getFirstName());
			jsonAdvisor.put("lastName", advisor.getLastName());

			// Serialize specialRequests
			JSONArray jsonSpecialRequests = new JSONArray();
			for (SpecialRequest request : advisor.getSpecialRequests()) {
				JSONObject jsonRequest = new JSONObject();
				jsonRequest.put("student", request.getStudent());
				jsonRequest.put("course", request.getCourse());
				jsonSpecialRequests.put(jsonRequest);
			}
			jsonAdvisor.put("specialRequests", jsonSpecialRequests);

			jsonArray.put(jsonAdvisor);
		}

		try {
			FileWriter fileWriter = new FileWriter(filePath);
			jsonArray.write(fileWriter);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
