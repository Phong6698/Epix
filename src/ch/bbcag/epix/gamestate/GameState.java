package ch.bbcag.epix.gamestate;

import ch.bbcag.epix.audio.AudioPlayer;
import ch.bbcag.epix.entity.Player;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			GameState.java.java Copyright Berufsbildungscenter 2015
 */

public abstract class GameState {
	
	protected Player player;
	
	protected String levelName;
	
	protected GameStateManager gsm;
	protected Boolean finished;

	protected AudioPlayer backgroundMusic;	
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}