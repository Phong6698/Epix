package ch.bbcag.epix.levels;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ch.bbcag.epix.audio.AudioPlayer;
import ch.bbcag.epix.controller.EpixController;
import ch.bbcag.epix.display.HUD;
import ch.bbcag.epix.enemies.Boss;
import ch.bbcag.epix.enemies.Magician;
import ch.bbcag.epix.enemies.Plant;
import ch.bbcag.epix.enemies.ShootingPlant;
import ch.bbcag.epix.entity.Coin;
import ch.bbcag.epix.entity.Flag;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.entity.Powerup;
import ch.bbcag.epix.entity.User;
import ch.bbcag.epix.gamestate.GameState;
import ch.bbcag.epix.gamestate.GameStateManager;
import ch.bbcag.epix.powerups.DamageUp;
import ch.bbcag.epix.powerups.Heart;
import ch.bbcag.epix.powerups.Jetpack;
import ch.bbcag.epix.powerups.Shield;
import ch.bbcag.epix.tilemap.Background;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcag.epix.view.EpixView;
import ch.bbcag.epix.view.GameFrame;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Level1State.java.java Copyright Berufsbildungscenter 2015
 */

public class Level1State extends GameState{

	private User user;
	
	private Player player_2;
	
	private TileMap tilemap;
	private Background bg;
	
	private HUD hud;
	
	private ArrayList<Plant> plant;
	private ArrayList<ShootingPlant> shootingPlant;
	private ArrayList<Magician> magicians;
	private ArrayList<Boss> boss;
	
	private ArrayList<Powerup> powerups;
	
	private ArrayList<Coin> coins;
	
	private ArrayList<Flag> flags;
	
	
	public Level1State(GameStateManager gsm, User user) {
		this.gsm = gsm;	
		setUser(user);
		
		init();
	}
    
	/**
	 * 
	 */
	public void init() {
		
		levelName = "Level 1";
	
		tilemap = new TileMap(32);
		tilemap.loadTiles("/TileSets/GroundTileSet.png");
		tilemap.loadMap("/Maps/level1.map");
		tilemap.setPosition(30, 30);

		bg = new Background("/Backgrounds/Background.png", 1);
		
		backgroundMusic = new AudioPlayer("/Musics/Level 1.mp3");
	
		
		player = new Player(tilemap, user);
		
		if(EpixView.isMultiplayer() == true){
			player_2 = new Player(tilemap, user);
			player_2.setPosition(60, 40);
		}
		
		backgroundMusic.playLoop();
		
		spawnEnemies();
		spawnPowerups();
		spawnCoins();
		spawnFlag();	

		player.setPosition(50, 40);

	}
	
	private void spawnPowerups() {
		powerups = new ArrayList<Powerup>();

		/*
		 * damageup
		 */
		DamageUp damageUp;
		Point[] damagUpPoints = new Point[] {
			new Point(560, 144),
			
		};
		for(int i = 0; i < damagUpPoints.length; i++) {
			damageUp = new DamageUp(tilemap);
			damageUp.setPosition(damagUpPoints[i].x, damagUpPoints[i].y);
			powerups.add(damageUp);
		}
		
		
		/*
		 * Heart
		 */
		Heart heart;
		Point[] hearthPoints = new Point[] {
			new Point(2512, 144),
		};
		for(int i = 0; i < hearthPoints.length; i++) {
			heart = new Heart(tilemap);
			heart.setPosition(hearthPoints[i].x, hearthPoints[i].y);
			powerups.add(heart);
		}
				
		/*
		 * Jetpack
		 */
		Jetpack jetpack;
		Point[] jetpackPoints = new Point[] {
			new Point(2000, 240),

		};
		for(int i = 0; i < jetpackPoints.length; i++) {
			jetpack = new Jetpack(tilemap);
			jetpack.setPosition(jetpackPoints[i].x, jetpackPoints[i].y);
			powerups.add(jetpack);
		}
		
		/*
		 * Shield
		 */
		Shield shield;
		Point[] shieldPoints = new Point[] {

		};
		for(int i = 0; i < shieldPoints.length; i++) {
			shield = new Shield(tilemap);
			shield.setPosition(shieldPoints[i].x, shieldPoints[i].y);
			powerups.add(shield);
		}
		
		
	}
	
	private void spawnEnemies() {
		
		/*
		 * plant
		 */
		plant = new ArrayList<Plant>();
			
		Plant s;
		Point[] plantPoints = new Point[] {
				new Point(460, 70),
				new Point(2315, 70)
		};
		for(int i = 0; i < plantPoints.length; i++) {
			s = new Plant(tilemap);
			s.setPosition(plantPoints[i].x, plantPoints[i].y);
			plant.add(s);
		}
			
		/*
		 * Magicians
		 */
		
		magicians = new ArrayList<Magician>();
		
		Magician m;
		Point[] magicanPoints = new Point[] {
				new Point(3150, 240)
		};
		for(int i = 0; i < magicanPoints.length; i++) {
			m = new Magician(tilemap , player);
			m.setPosition(magicanPoints[i].x, magicanPoints[i].y);
			magicians.add(m);
		}
		
		/**
		 * Boss
		 */		
		boss = new ArrayList<Boss>();

		Boss b;
		Point[] bossPoint = new Point[] { 
					
		};
		for (int i = 0; i < bossPoint.length; i++) {
			b = new Boss(tilemap, player);
			b.setPosition(bossPoint[i].x, bossPoint[i].y);
			boss.add(b);
		}
		
		
		
		/*
		 * shootingPlant
		 */
		shootingPlant = new ArrayList<ShootingPlant>();
		
		ShootingPlant p;
		Point[] shootingPlantPoints = new Point[] {
				new Point(700, 70),
				new Point(1000, 70),
				new Point(1520, 70),
				new Point(2610, 70)
			
		};
		for(int i = 0; i < shootingPlantPoints.length; i++) {
			p = new ShootingPlant(tilemap, false);
			p.setPosition(shootingPlantPoints[i].x, shootingPlantPoints[i].y);
			shootingPlant.add(p);
		}		
	}
	
	
	private void spawnCoins() {
		coins = new ArrayList<Coin>();

		/*
		 * coin
		 */
		Coin coin;
		Point[] coinPoints = new Point[] {
			new Point(337, 208),
			new Point(436, 176),
			new Point(496, 176),
			new Point(886, 208),
			new Point(942, 176),
			new Point(1234, 176),
			new Point(1270, 176),
			new Point(2578, 80),
			new Point(2635, 80)
		};
		for(int i = 0; i < coinPoints.length; i++) {
			coin = new Coin(tilemap);
			coin.setPosition(coinPoints[i].x, coinPoints[i].y);
			coins.add(coin);
		}
	}
	
	private void spawnFlag() {
		flags = new ArrayList<Flag>();
		/*
		 * flag
		 */
		Flag flag;
		Point[] flagPoints = new Point[] {
				new Point(3152, 240),
				
		};
		for(int i = 0; i < flagPoints.length; i++) {
			flag = new Flag(tilemap);
			flag.setPosition(flagPoints[i].x, flagPoints[i].y);
			flags.add(flag);
		}
	}

	public void update() {

		// update player
		player.update(player);
		
		if(EpixView.isMultiplayer() == true){
			player_2.update(player_2);
			player_2.checkAttackPlants(plant, player);
			player_2.checkAttackShootingPlants(shootingPlant, player);
			player_2.checkAttackMagician(magicians, player);
			player_2.checkAttackBoss(boss, player);
			
			player_2.checkPowerup(powerups, player);
			player_2.checkCoin(coins);
		}
		
		tilemap.setPosition(GameFrame.WIDTH / 3 - player.getx(), GameFrame.HEIGHT / 3 - player.gety());
		
		//update hud
		
		hud = new HUD(player);

		// set background
		bg.setPosition(tilemap.getx(), tilemap.gety());
		
		player.checkAttackPlants(plant, player);
		player.checkAttackShootingPlants(shootingPlant, player);
		player.checkAttackMagician(magicians, player);
		player.checkAttackBoss(boss, player);
		
		player.checkPowerup(powerups, player);
		player.checkCoin(coins);
		
		
		//check if no enimies in level
		if (plant.size() == 0 && shootingPlant.size() == 0 && magicians.size() == 0) {
			finished = player.checkFlag(flags);
		}
		
				
		for(int i = 0; i < plant.size(); i++) {
			Plant e = plant.get(i);
			e.update(e, player , player_2);
			if(e.isDead()) {
				plant.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < magicians.size(); i++) {
			Magician e = magicians.get(i);
			e.update(e, player);
			if(e.isDead()) {
				magicians.remove(i);
				i--;
			}else {
				if (EpixView.multiplayer == true) {
					e.checkAttackPlayer(player_2, player);
				}
				e.checkAttackPlayer(player, player);
			}
		}
		
		for(int i = 0; i < shootingPlant.size(); i++) {
			ShootingPlant e = shootingPlant.get(i);	
			e.update(e, player);
			if(e.isDead()) {
				shootingPlant.remove(i);
				i--;
			}else {
				if (EpixView.multiplayer == true) {
					e.checkAttackPlayer(player_2, player);
				}
				e.checkAttackPlayer(player, player);
			}
		}
		
		for (int i = 0; i < boss.size(); i++) {
			Boss e = boss.get(i);
			e.update(e, player, false);
			if (e.isDead()) {
				boss.remove(i);
				i--;
			}else {
				if (EpixView.multiplayer == true) {
					e.checkAttackPlayer(player_2, player);
				}
				e.checkAttackPlayer(player, player);
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
		
		for(int i = 0; i < coins.size(); i++) {
			Coin coin = coins.get(i);
			coin.update();
			if(coin.isTaken()) {
				coins.remove(i);
				i--;

			}
		}
		
		for(int i = 0; i < flags.size(); i++) {
			Flag flag = flags.get(i);
			flag.update();
			
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
		
		for(int i = 0; i < magicians.size(); i++) {
			magicians.get(i).draw(g);
		}
		for (int i = 0; i < boss.size(); i++) {
			boss.get(i).draw(g);
		}
		
		for(int i = 0; i < powerups.size(); i++) {
			powerups.get(i).draw(g);
		}
		
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(g);
		}
		
		for(int i = 0; i < flags.size(); i++) {
			flags.get(i).draw(g);
		}
		
		// draw player
		player.draw(g);	
		
		if(EpixView.isMultiplayer() == true){
			player_2.draw(g);
		}
		hud.draw(g);
		
	}
	

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_UP) player.setJumping(true);
		if(k == KeyEvent.VK_R) player.setRainbowing();
		
		if(k == KeyEvent.VK_A) player_2.setLeft(true);
		if(k == KeyEvent.VK_D) player_2.setRight(true);
		if(k == KeyEvent.VK_S) player_2.setDown(true);
		if(k == KeyEvent.VK_W) player_2.setJumping(true);
		if(k == KeyEvent.VK_R) player_2.setRainbowing();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_UP) player.setJumping(false);
		
		if(k == KeyEvent.VK_A) player_2.setLeft(false);
		if(k == KeyEvent.VK_D) player_2.setRight(false);
		if(k == KeyEvent.VK_S) player_2.setDown(false);
		if(k == KeyEvent.VK_W) player_2.setJumping(false);		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
