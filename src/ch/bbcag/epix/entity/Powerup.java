package ch.bbcag.epix.entity;

import ch.bbcag.epix.tilemap.TileMap;

public class Powerup extends MapObject{

	protected int plusHealth;
	protected double plusJump;
	protected int plusDamage;
	protected double plusSpeed;
	protected long expireTime;
	protected boolean powerupAvailable;
	
	protected boolean taken;
	
	protected long takenTime;
	

	
	public Powerup(TileMap tm) {
		super(tm);
		
		
		
	}
	
	public void checkPowerupAvailable(Player player) {
	
	}
	
	public void addPowerupToPlayer(Player player) {
		
		player.setHealth(player.getHealth() + plusHealth);
		player.setRainbowdamage(player.getRainbowdamage() + plusDamage);
		powerupAvailable = true;
		takenTime = System.currentTimeMillis();
		taken = true;
			
	}
	
	public void checkExpireTime() {

	}
	
	
	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}



	public void update(Player player) {
		if (expireTime != 0 && isTaken() && takenTime + expireTime <= System.currentTimeMillis()) {
			setPowerupAvailable(false);
		};
		
		if (!powerupAvailable && isTaken()) {
			player.setHealth(player.getHealth() - plusHealth);
			player.setRainbowdamage(player.getRainbowdamage() - plusDamage);
		};
		

	}


	public boolean isPowerupAvailable() {
		return powerupAvailable;
	}


	public void setPowerupAvailable(boolean powerupAvailable) {
		this.powerupAvailable = powerupAvailable;
	}


}