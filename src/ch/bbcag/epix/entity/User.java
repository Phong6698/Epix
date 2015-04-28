package ch.bbcag.epix.entity;

/**
 * 
 * @author Chiramet Phong Penglerd, ICT Berufsbildungscenter AG, chirametphong.penglerd@bbcag.ch
 *
 */

public class User {
	//Instanzvariablen
	private String username = null;
	private String email = null;
	private String password = null;
	private String passwordConfirm = null;
	private int coin;
	private int maxHealth = 50;	
	private boolean dead = false;
	

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
	
	public int getCoin() {
		return coin;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	public boolean isDead() {
		return dead;
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
	
	public void setCoin(int coin) {
		this.coin = coin;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	
}
