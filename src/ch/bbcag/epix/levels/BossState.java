package ch.bbcag.epix.levels;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import ch.bbcag.epix.entity.HUD;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.gamestate.GameState;
import ch.bbcag.epix.gamestate.GameStateManager;
import ch.bbcag.epix.tilemap.Background;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcag.epix.view.GameFrame;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *         BossState.java.java Copyright Berufsbildungscenter 2015
 */

public class BossState extends GameState {

	private User user;

	private TileMap tilemap;
	private Player player;
	private Background bg;

	public BossState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		levelName = "Boss Level";

		tilemap = new TileMap(32);
		tilemap.loadTiles("/TileSets/GroundTileSet.png");
		tilemap.loadMap("/Maps/Boss.map");
		tilemap.setPosition(30, 30);

		bg = new Background("/Backgrounds/Background.png", 1);

		player = new Player(tilemap, user);

		player.setPosition(28, 28);

	}

	public void update() {

		// update player
		player.update(player);
		tilemap.setPosition(GameFrame.WIDTH / 2 - player.getx(), GameFrame.HEIGHT / 2 - player.gety());

		// set background
		bg.setPosition(tilemap.getx(), tilemap.gety());
	}

	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw tilemap
		tilemap.draw(g);

		// draw player
		player.draw(g);

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(true);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(true);
		if (k == KeyEvent.VK_UP)
			player.setJumping(true);
		if (k == KeyEvent.VK_R)
			player.setRainbowing();
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(false);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(false);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(false);
		if (k == KeyEvent.VK_UP)
			player.setJumping(false);

	}
}
