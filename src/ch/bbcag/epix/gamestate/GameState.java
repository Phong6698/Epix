package ch.bbcag.epix.gamestate;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			GameState.java.java Copyright Berufsbildungscenter 2015
 */

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected Boolean finished;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}