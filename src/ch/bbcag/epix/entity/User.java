package ch.bbcag.epix.entity;

public class User {
	//Instanzvariablen
	private String username = null;
	private String email = null;
	private String password = null;
	private String passwordConfirm = null;
	
	//Getter
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	//Setter
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	
}
