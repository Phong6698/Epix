package ch.bbcag.epix.entity;

import ch.bbcag.epix.tilemap.TileMap;

/**
 * Powerup
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class Powerup extends MapObject{

	protected int plusHealth;
	protected int plusDamage;
	
	protected boolean jetpack;
	protected double plusMoveSpeed;
	protected double plusMaxSpeed;
	protected double plusStopSpeed;
	protected double plusFallSpeed;
	protected double plusMaxFallSpeed;
	protected double plusJumpStart;
	protected double plusStopJumpSpeed;
	
	protected boolean shield;
	
	protected double expireTime;
	
	protected boolean taken;
	protected long takenTime;
	protected boolean available;
	

	/**
	 * Konstruktor
	 * @param tm {@link TileMap}
	 */
	public Powerup(TileMap tm) {
		super(tm);
		
	}

	
	/**
	 * Update
	 */
	public void update() {
		
	}

	
	/*
	 * Getter und Setter
	 */
	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
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