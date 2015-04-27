package ch.bbcag.entity.levels;

import java.awt.Graphics2D;

import ch.bbcag.epix.entity.HUD;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ch.bbcag.entity.enemies.Plant;
import ch.bbcag.entity.enemies.ShootingPlant;
import ch.bbcag.entity.powerups.DamageUp;
import ch.bbcag.entity.powerups.Heart;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.entity.Powerup;
import ch.bbcag.epix.gamestate.GameState;
import ch.bbcag.epix.gamestate.GameStateManager;
import ch.bbcag.epix.tilemap.Background;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcag.epix.view.GameFrame;

public class Level1State extends GameState{

	private TileMap tilemap;
	private Player player;
	private Player player2;
	private Background bg;
	
	private HUD hud;
	
	private ArrayList<Plant> plant;
	private ArrayList<ShootingPlant> shootingPlant;
	
	private ArrayList<Powerup> powerups;
	
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
		
		spawnEnemies();
		spawnPowerups();
		
		player.setPosition(28, 28);

	}
	
	private void spawnPowerups() {
		powerups = new ArrayList<Powerup>();

		/*
		 * damageup
		 */
		DamageUp damageUp;
		Point[] damagUpPoints = new Point[] {
			new Point(100, 120),
			new Point(290, 70)
			
		};
		for(int i = 0; i < damagUpPoints.length; i++) {
			damageUp = new DamageUp(tilemap);
			damageUp.setPosition(damagUpPoints[i].x, damagUpPoints[i].y);
			powerups.add(damageUp);
		}
		
		/*
		 * heart
		 */
		Heart heart;
		Point[] hearthPoints = new Point[] {
			new Point(120, 120),
		};
		for(int i = 0; i < hearthPoints.length; i++) {
			heart = new Heart(tilemap);
			heart.setPosition(hearthPoints[i].x, hearthPoints[i].y);
			powerups.add(heart);
		}
	}
	
	private void spawnEnemies() {
		
		/*
		 * plant
		 */
		plant = new ArrayList<Plant>();
			
		Plant s;
		Point[] plantPoints = new Point[] {
				new Point(190, 70)
		};
		for(int i = 0; i < plantPoints.length; i++) {
			s = new Plant(tilemap);
			s.setPosition(plantPoints[i].x, plantPoints[i].y);
			plant.add(s);
		}
			
		
		/*
		 * shootingPlant
		 */
		shootingPlant = new ArrayList<ShootingPlant>();
		
		ShootingPlant p;
		Point[] shootingPlantPoints = new Point[] {
				new Point(240, 70)
			
		};
		for(int i = 0; i < shootingPlantPoints.length; i++) {
			p = new ShootingPlant(tilemap, false);
			p.setPosition(shootingPlantPoints[i].x, shootingPlantPoints[i].y);
			shootingPlant.add(p);
		}
		
	}

	public void update() {

		// update player
		player.update();
		tilemap.setPosition(GameFrame.WIDTH / 2 - player.getx(), GameFrame.HEIGHT / 2 - player.gety());

		// set background
		bg.setPosition(tilemap.getx(), tilemap.gety());
		
		player.checkAttackPlants(plant);
		player.checkAttackShootingPlants(shootingPlant);
		player.checkPowerup(powerups);
		
		
		for(int i = 0; i < plant.size(); i++) {
			Plant e = plant.get(i);
			e.update(e, player);
			if(e.isDead()) {
				plant.remove(i);
				i--;
			}
		}
		
		
		for(int i = 0; i < shootingPlant.size(); i++) {
			ShootingPlant e = shootingPlant.get(i);	
			e.update(e, player);
			if(e.isDead()) {
				shootingPlant.remove(i);
				i--;
			}else {
				e.checkAttackPlayer(player);
			}
					
		}
		
		for(int i = 0; i < powerups.size(); i++) {
			Powerup e = powerups.get(i);
			e.update();
			if(e.isTaken()) {
				powerups.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < powerups.size(); i++) {
			Powerup e = powerups.get(i);
			e.update();
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
		
		for(int i = 0; i < shootingPlant.size(); i++) {
			shootingPlant.get(i).draw(g);
		}
		
		for(int i = 0; i < powerups.size(); i++) {
			powerups.get(i).draw(g);
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