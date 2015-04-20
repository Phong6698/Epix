package ch.bbcag.epix.gamestate;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.Background;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcag.epix.view.EpixView;

public class Level_1_State extends GameState{

	private TileMap tilemap;
	private Player player;
	private Background bg;
	
	public Level_1_State(GameStateManager gsm) {
		this.gsm = gsm;	
		init();
	}

	public void init() {
	
		tilemap = new TileMap(16);
		tilemap.loadTiles("/Ground/GroundTileSet.png");
		tilemap.loadMap("/Map/level2-1.map");
		tilemap.setPosition(0, 0);

		bg = new Background("/Background/mid-NSMBintro.ogv.png", 0.1);

		player = new Player(tilemap);

		player.setPosition(100, 100);

	}

	public void update() {

		// update player
		player.update();
		tilemap.setPosition(EpixView.WIDTH / 2 - player.getx(), EpixView.HEIGHT / 2 - player.gety());

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
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
	}
}
