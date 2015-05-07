package ch.bbcag.epix.gamestate;

import ch.bbcag.epix.controller.EpixController;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.levels.BossState;
import ch.bbcag.epix.levels.Level1State;
import ch.bbcag.epix.levels.Level2State;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			GameStateManager.java.java Copyright Berufsbildungscenter 2015
 */

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentLevel;
	
	private boolean finished;
	
	
	
	public static final int NUMLEVELS = 4;

	public static final int LEVEL1 = 1;
	public static final int LEVEL2 = 2;
	public static final int BOSSLEVEL = 3;
	
	public GameStateManager(int level, User user) {
		
		gameStates = new GameState[NUMLEVELS];
		
		this.setCurrentLevel(level);
		loadState(getCurrentLevel(), user);
		
	}
	


	private void loadState(int level, User user) {
		if(level == LEVEL1){
			gameStates[level] = new Level1State(this, user);
		}
		else if(level == LEVEL2) {
			gameStates[level] = new Level2State(this, user);
		}
		else if(level == BOSSLEVEL) {
			gameStates[level] = new BossState(this);
		}
	}
	
	private void unloadState(int level) {
		gameStates[level] = null;
	}
	
	public void setState(int level, User user) {
		unloadState(currentLevel);
		currentLevel = level;
		loadState(currentLevel, user);
		//gameStates[currentState].init();
	}
	
	public void update() {
		try {
			gameStates[currentLevel].update();
			
			//if finished update coin in database
			if(gameStates[currentLevel].finished) {
				EpixController.getInstance().coinsUpdate(gameStates[currentLevel].player);
				this.setFinished(true);
			}
		} catch(Exception e) {}
		
		
	}
	
	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentLevel].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentLevel].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentLevel].keyReleased(k);
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}



	public boolean isFinished() {
		return finished;
	}



	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
}









