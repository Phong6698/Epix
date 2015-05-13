package ch.bbcag.epix.entity;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *         User.java.java Copyright Berufsbildungscenter 2015
 */

public class User {
	// Instanzvariablen
	private String username = null;
	private String email = null;
	private String password = null;
	private String passwordConfirm = null;
	private int coin;
	private int maxHealth;
	private boolean dead = false;
	private double maxJump;
	private int damage;
	private double moveSpeed;
	private double maxMoveSpeed;

	// Getter
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

	// Setter
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

	public double getMaxJump() {
		return maxJump;
	}

	public void setMaxJump(double d) {
		this.maxJump =  d;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage =  damage;
	}

	public double getMaxMoveSpeed() {
		return maxMoveSpeed;
	}

	public void setMaxMoveSpeed(double maxMoveSpeed) {
		this.maxMoveSpeed =  maxMoveSpeed;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed =  moveSpeed;
	}
}
