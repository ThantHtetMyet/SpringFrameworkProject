package Session;

public class UserSession {

	private String name;
	private String role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserSession [name=" + name + ", role=" + role + "]";
	}
	
	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserSession(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
	
	
}
