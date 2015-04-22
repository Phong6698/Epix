package ch.bbcag.epix.gamestate;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ch.bbcag.entity.enemies.Plant;
import ch.bbcag.epix.Main.GamePanel;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.Background;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcg.entity.enemies.ShootingPlant;

public class Level1State extends GameState{

	private TileMap tilemap;
	private Player player;
	private Background bg;
	
	private ArrayList<Plant> plant;
	private ArrayList<ShootingPlant> shootingplant;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;	
		init();
	}

	public void init() {
	
		tilemap = new TileMap(16);
		tilemap.loadTiles("/TileSets/GroundTileSet.png");
		tilemap.loadMap("/Maps/level1.map");
		tilemap.setPosition(30, 30);
		
	
		bg = new Background("/Backgrounds/Background.png", 1);
		
		player = new Player(tilemap);
		populateEnemies();
		player.setPosition(28, 28);

	}
	
private void populateEnemies() {
		
		plant = new ArrayList<Plant>();
	
		Plant s;
		Point[] points = new Point[] {
			new Point(120, 10),
			new Point(200, 10),
			new Point(250, 10),
			new Point(325, 10),
			new Point(400, 10),
			new Point(450, 10),
			new Point(576, 10),
		};
		for(int i = 0; i < points.length; i++) {
			s = new Plant(tilemap);
			s.setPosition(points[i].x, points[i].y);
			plant.add(s);
		}
		
		shootingplant = new ArrayList<ShootingPlant>();
		
		ShootingPlant p;
		Point[] point = new Point[] {
			new Point(160, 10),
			new Point(260, 10),
			new Point(230, 10),
			new Point(375, 10),
			new Point(480, 10),
			new Point(420, 10),
			new Point(606, 10),
		};
		for(int i = 0; i < point.length; i++) {
			p = new ShootingPlant(tilemap, false);
			p.setPosition(point[i].x, point[i].y);
			shootingplant.add(p);
			}
		
	}

	public void update() {

		// update player
		player.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

		// set background
		bg.setPosition(tilemap.getx(), tilemap.gety());
		
		player.checkAttackPlants(plant);
		player.checkAttackShootingPlants(shootingplant);
		
		for(int i = 0; i < plant.size(); i++) {
			Plant e = plant.get(i);
			e.update();
			
			
			if(e.isDead()) {
				plant.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < shootingplant.size(); i++) {
			ShootingPlant e = shootingplant.get(i);
			e.update();
			if(e.isDead()) {
				shootingplant.remove(i);
				i--;
			}
		}

	}

	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw tilemap
		tilemap.draw(g);

		for(int i = 0; i < plant.size(); i++) {
			plant.get(i).draw(g);
		}
		
		for(int i = 0; i < shootingplant.size(); i++) {
			shootingplant.get(i).draw(g);
		}
		
		// draw player
		player.draw(g);	
	}
	

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_UP) player.setJumping(true);
		if(k == KeyEvent.VK_R) player.setRainbowing();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_UP) player.setJumping(false);
		
	}
}
