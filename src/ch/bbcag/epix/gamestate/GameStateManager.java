package ch.bbcag.epix.gamestate;


public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentLevel;
	
	public static final int NUMLEVELS = 4;

	public static final int LEVEL1 = 1;
	public static final int LEVEL2 = 2;
	public static final int BOSSLEVEL = 3;
	
	public GameStateManager(int level) {
		
		gameStates = new GameState[NUMLEVELS];
		
		this.setCurrentLevel(level);
		loadState(getCurrentLevel());
		
	}
	


	private void loadState(int level) {
		if(level == LEVEL1){
			gameStates[level] = new Level1State(this);
		}
		else if(level == LEVEL2) {
			gameStates[level] = new Level2State(this);
		}
		else if(level == BOSSLEVEL) {
			gameStates[level] = new BossState(this);
		}
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentLevel);
		currentLevel = state;
		loadState(currentLevel);
		//gameStates[currentState].init();
	}
	
	public void update() {
		try {
			gameStates[currentLevel].update();
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
	
}









