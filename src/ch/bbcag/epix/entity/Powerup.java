package ch.bbcag.epix.entity;

import ch.bbcag.epix.tilemap.TileMap;

public class Powerup extends MapObject{

	protected int plusHealth;
	protected double plusJump;
	protected int plusDamage;
	protected double plusSpeed;
	protected double expireTime;
	
	protected boolean taken;
	
	
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
}