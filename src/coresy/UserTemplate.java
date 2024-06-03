package coresy;

public class UserTemplate {
	protected int userID;
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String role;

	public UserTemplate(int userId, String username, String password, String firstName, String lastName, String role) {
		this.userID = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public UserTemplate(int userId, String usrname, String firstName, String lastName, String role) {
		this.userID = userId;
		this.username = usrname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	// getters and setters
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		String fullName = firstName + " " + lastName;
		return fullName;
	}

	public void setFullName(String fullNameWithSpace) {
		String[] namesArray = fullNameWithSpace.split(" ");
		firstName = namesArray[0];
		lastName = namesArray[1];

	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
