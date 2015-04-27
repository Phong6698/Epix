package ch.bbcag.epix.entity;

import ch.bbcag.epix.tilemap.TileMap;

public class Powerup extends MapObject{

	protected int plusHealth;
	protected double plusJump;
	protected int plusDamage;
	protected double plusSpeed;
	protected double expireTime;
	


	protected boolean taken;
	protected long takenTime;
	protected boolean available;
	
	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public Powerup(TileMap tm) {
		super(tm);
		
	}

	public void update() {
		
	}

	public long getTakenTime() {
		return takenTime;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setTakenTime(long takenTime) {
		this.takenTime = takenTime;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public double getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(double expireTime) {
		this.expireTime = expireTime;
	}
}