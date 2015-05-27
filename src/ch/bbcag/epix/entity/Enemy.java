package ch.bbcag.epix.entity;

import ch.bbcag.epix.tilemap.TileMap;

/**
 * Gegner
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */
public class Enemy extends MapObject {

	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;

	protected boolean flinching;
	protected long flinchTimer;


	/**
	 * Konstruktor
	 * @param tm {@link TileMap}
	 */
	public Enemy(TileMap tm) {
		super(tm);
	}


	/**
	 * Schaden bekommen
	 * @param damage
	 */
	public void hit(int damage) {
		if (dead || flinching)
			return;
		health -= damage;
		if (health < 0){
			health = 0;
		}
		
		if (health == 0) {
			dead = true;
		}
			
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	
	/*
	 * Getter und Setter
	 */
	public boolean isDead() {
		return dead;
	}

	public int getDamage() {
		return damage;
	}
}
