package ch.bbcag.epix.gamestate;

import java.awt.Graphics2D;

import ch.bbcag.epix.audio.AudioPlayer;
import ch.bbcag.epix.entity.Player;

/**
 * GameState fuer Levels
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public abstract class GameState {
	
	protected Player player;
	
	protected String levelName;
	
	protected GameStateManager gsm;
	protected Boolean finished;

	protected AudioPlayer backgroundMusic;	
	
	
	/**
	 * initialisieren
	 */
	public abstract void init();
	
	/**
	 * Update
	 */
	public abstract void update();
	
	/**
	 * Draw
	 * @param g {@link Graphics2D}
	 */
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}