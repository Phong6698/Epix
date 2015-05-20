package ch.bbcag.epix.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.epix.enemies.Boss;
import ch.bbcag.epix.enemies.Magician;
import ch.bbcag.epix.enemies.Plant;
import ch.bbcag.epix.enemies.PlantShot;
import ch.bbcag.epix.enemies.ShootingPlant;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *         Player.java.java Copyright Berufsbildungscenter 2015
 */

public class Player extends MapObject {

	// player stuff
	private int health;

	private double jumpStart;
	
	private int coin;
	private int collectedCoin;
	private String username;
	private int maxHealth;

	private int rainbow;
	private int maxRainbow;
	private boolean dead;
	private boolean flinching;

	private long flinchTimer;

	private User user;

	private ArrayList<Powerup> powerups = new ArrayList<Powerup>();

	private boolean jetpack;
	private boolean shield;

	// rainbow
	private boolean rainbowing;
	private int rainbowcost;
	private int rainbowdamage;
	private ArrayList<Rainbow> rainbows;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1, 6, 1, 3, 1, 6, 1, 3 };
	private ArrayList<PlantShot> plantshots;

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 2;
	private static final int RAINBOW = 3;

	private static final int IDLE_JETPACK = 4;
	private static final int WALKING_JETPACK = 5;
	private static final int JUMPING_JETPACK = 6;
	private static final int FALLING_JETPACK = 6;
	private static final int RAINBOW_JETPACK = 7;

	public Player(TileMap tm, User user) {
		super(tm);
		
		this.setUser(user);
		
		setUsername(user.getUsername());
		setCoin(user.getCoin());
		setCollectedCoin(user.getCollectedCoin());
		setMaxHealth(user.getMaxHealth());
		setHealth(user.getMaxHealth());
		setJumpStart(user.getMaxJump());
		setMoveSpeed(user.getMoveSpeed());
		setMaxMoveSpeed(user.getMaxMoveSpeed());
		setRainbowdamage(user.getDamage());


		width = 32;
		height = 32;

		// hitbox
		cwidth = 24;
		cheight = 32;

		stopSpeed = 0.8;
		fallSpeed = 0.30;
		maxFallSpeed = 4.0;
		
		stopJumpSpeed = 0.9;

		facingRight = true;


		rainbow = maxRainbow = 2500;
		rainbows = new ArrayList<Rainbow>();

		// load sprites
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Player/Player.png"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 8; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);

				}

				sprites.add(bi);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(100);

		

	}

	public void checkAttackPlants(ArrayList<Plant> plants, Player player) {
		// loop through enemies
		for (int i = 0; i < plants.size(); i++) {

			Plant e = plants.get(i);

			// fireballs
			for (int j = 0; j < rainbows.size(); j++) {
				if (rainbows.get(j).intersects(e)) {
					e.hit(rainbowdamage);
					rainbows.get(j).setHit();
					break;
				}
			}
			// check enemy collision
			if (intersects(e)) {
				hit(e.getDamage());
			}
		}
	}

	public void checkAttackShootingPlants(ArrayList<ShootingPlant> shootingplants, Player player) {
		// loop through enemies
		for (int i = 0; i < shootingplants.size(); i++) {
			ShootingPlant e = shootingplants.get(i);
			// fireballs
			for (int j = 0; j < rainbows.size(); j++) {
				if (rainbows.get(j).intersects(e)) {
					e.hit(rainbowdamage);
					rainbows.get(j).setHit();
					break;
				}
			}
			// check enemy collision
			if (intersects(e)) {
				e.update(e, player);
				hit(e.getDamage());
			}
		}
	}

	public void checkAttackBoss(ArrayList<Boss> boss, Player player) {
		// loop through enemies
		for (int i = 0; i < boss.size(); i++) {
			Boss b = boss.get(i);
			// fireballs
			for (int j = 0; j < rainbows.size(); j++) {
				if (rainbows.get(j).intersects(b)) {
					b.hit(rainbowdamage);
					rainbows.get(j).setHit();
					break;
				}
			}
			boolean p = false;

			// check enemy collision
			if (intersects(b)) {
				p = true;
				b.update(b, player, p);
				hit(b.getDamage());
			}
		}
	}

	public void checkAttackMagician(ArrayList<Magician> magicians, Player player) {
		// loop through enemies
		for (int i = 0; i < magicians.size(); i++) {
			Magician magician = magicians.get(i);
			// fireballs
			for (int j = 0; j < rainbows.size(); j++) {
				if (rainbows.get(j).intersects(magician)) {
					magician.hit(rainbowdamage);
					rainbows.get(j).setHit();
					break;
				}
			}
			// check enemy collision
			if (intersects(magician)) {
				magician.update(magician, player);
				hit(magician.getDamage());
			}
		}
	}

	public void checkPowerup(ArrayList<Powerup> powerups, Player player) {

		// loop through powerups
		for (int i = 0; i < powerups.size(); i++) {

			Powerup powerup = powerups.get(i);

			// check enemy collision
			if (intersects(powerup)) {
				powerup.update();
				addPowerupToPlayer(powerup, player);
			}

		}
	}

	public void checkCoin(ArrayList<Coin> coins) {
		// loop through powerups
		for (int i = 0; i < coins.size(); i++) {

			Coin coin = coins.get(i);

			// check coin collision
			if (intersects(coin)) {
				coin.update();
				this.setCoin(this.getCoin() + coin.getCoinValue());
				this.setCollectedCoin(this.getCollectedCoin() + coin.getCoinValue());
				coin.setTaken(true);
			}

		}

	}

	public boolean checkFlag(ArrayList<Flag> flags) {
		for (int i = 0; i < flags.size(); i++) {

			Flag flag = flags.get(i);

			if (intersects(flag)) {
				return true;

			}

		}
		return false;
	}

	public void addPowerupToPlayer(Powerup powerup, Player player) {
		if (player.getHealth() + powerup.plusHealth >= player.getMaxHealth()) {
			player.setHealth(player.getMaxHealth());
		} else {
			setHealth(getHealth() + powerup.plusHealth);
		}
		setRainbowdamage(getRainbowdamage() + powerup.plusDamage);

		// Jetpack
		if (powerup.jetpack == true) {
			jetpack = true;
		}
		moveSpeed = moveSpeed + powerup.moveSpeed;
		maxMoveSpeed = maxMoveSpeed + powerup.moveSpeed;
		stopSpeed = stopSpeed + powerup.moveSpeed;
		fallSpeed = fallSpeed + powerup.moveSpeed;
		maxFallSpeed = maxFallSpeed + powerup.moveSpeed;
		jumpStart = jumpStart + powerup.moveSpeed;
		stopJumpSpeed = stopJumpSpeed + powerup.moveSpeed;

		// Shield
		if (powerup.shield == true) {
			shield = true;
		}

		powerups.add(powerup);
		powerup.setTaken(true);
		powerup.setAvailable(true);
		powerup.setTakenTime(System.currentTimeMillis());
	}

	public void removePowerupFromPlayer(Powerup powerup) {
		setHealth(getHealth() - powerup.plusHealth);
		setRainbowdamage(getRainbowdamage() - powerup.plusDamage);

		// Jetpack
		if (powerup.jetpack == true) {
			jetpack = false;
		}
		moveSpeed = moveSpeed - powerup.moveSpeed;
		maxMoveSpeed = maxMoveSpeed - powerup.moveSpeed;
		stopSpeed = stopSpeed - powerup.moveSpeed;
		fallSpeed = fallSpeed - powerup.moveSpeed;
		maxFallSpeed = maxFallSpeed - powerup.moveSpeed;
		jumpStart = jumpStart - powerup.moveSpeed;
		stopJumpSpeed = stopJumpSpeed - powerup.moveSpeed;

		// Shield
		if (powerup.shield == true) {
			shield = false;
		}

		powerups.remove(powerup);
	}

	/**
	 * player gets damage
	 * 
	 * @param damage
	 */
	public void hit(int damage) {
		if (!shield) {
			if (flinching) {
				return;
			}
			health -= damage;
			if (health < 0) {
				health = 0;
			}
			if (health == 0) {
				setDead(true);
			}
			flinching = true;
			flinchTimer = System.nanoTime();
		}
	}

	private void getNextPosition(Player player) {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxMoveSpeed) {
				dx = -maxMoveSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxMoveSpeed) {
				dx = maxMoveSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		// cannot move while attacking, except in air
		if ((currentAction == RAINBOW) && !(jumping || falling)) {
			dx = 0;
		}

		// jumping
		if (jumping && !falling && !jetpack) {
			dy = jumpStart;
			falling = true;
		}

		// jetpack
		if (jumping && jetpack && player.gety() > 20) {
			dy = jumpStart;
			falling = false;
		} else if (jumping && jetpack && player.gety() < 20) {
			falling = true;
		}

		// falling
		if (falling) {
			if (dy > 0)
				dy += fallSpeed * 1;
			else dy += fallSpeed;

			if (dy > 0)
				jumping = false;
			if (dy < 0 && !jumping)
				dy += stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;

		}
	}

	public void update(Player player) {

		// update position
		getNextPosition(player);
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// wenn spieler unter der Map dann sterben
		if ( this.gety() >= 270) {
			setDead(true);
		}

		if (powerups.size() != 0) {
			for (int i = 0; i < powerups.size(); i++) {
				Powerup powerup = powerups.get(i);
				if (powerup.getExpireTime() > 0 && powerup.getTakenTime() + powerup.getExpireTime() <= System.currentTimeMillis()) {
					removePowerupFromPlayer(powerup);

				}
			}
		}

		// check attack has stopped
		if (currentAction == RAINBOW) {
			if (animation.hasPlayedOnce())
				rainbowing = false;
		}
		if (currentAction == RAINBOW_JETPACK) {
			if (animation.hasPlayedOnce())
				rainbowing = false;
		}
		// fireball attack
		if (rainbowing && currentAction != RAINBOW && currentAction != RAINBOW_JETPACK && rainbows.size() < 2) {
			Rainbow fb = new Rainbow(tileMap, facingRight);
			fb.setPosition(x, y - (height / 2 - cheight / 2) / 2);
			rainbows.add(fb);
		}

		// update fireballs
		for (int i = 0; i < rainbows.size(); i++) {
			rainbows.get(i).update();
			if (rainbows.get(i).shouldRemove()) {
				rainbows.remove(i);
				i--;
			}
		}

		// check done flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 1000) {
				flinching = false;
			}
		}

		// set animation
		else if (rainbowing) {
			if (currentAction != RAINBOW && !jetpack) {
				currentAction = RAINBOW;
				animation.setFrames(sprites.get(RAINBOW));
				animation.setDelay(100);
				width = 32;
			} else if (currentAction != RAINBOW_JETPACK && jetpack) {
				currentAction = RAINBOW_JETPACK;
				animation.setFrames(sprites.get(RAINBOW_JETPACK));
				animation.setDelay(100);
				width = 32;
			}
		} else if (dy > 0) {
			if (currentAction != FALLING && !jetpack) {

				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(-4);
				width = 32;
			} else if (currentAction != FALLING_JETPACK && jetpack) {
				currentAction = FALLING_JETPACK;
				animation.setFrames(sprites.get(FALLING_JETPACK));
				animation.setDelay(-4);
				width = 32;
			}
		} else if (dy < 0) {
			if (currentAction != JUMPING && !jetpack) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(100);
				width = 32;
			} else if (currentAction != JUMPING_JETPACK && jetpack) {
				currentAction = JUMPING_JETPACK;
				animation.setFrames(sprites.get(JUMPING_JETPACK));
				animation.setDelay(100);
				width = 32;
			}
		} else if (left || right) {
			if (currentAction != WALKING && !jetpack) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(60);
				width = 32;
			} else if (currentAction != WALKING_JETPACK && jetpack) {
				currentAction = WALKING_JETPACK;
				animation.setFrames(sprites.get(WALKING_JETPACK));
				animation.setDelay(60);
				width = 32;
			}
		} else {
			if (currentAction != IDLE && !jetpack) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 32;
			} else if (currentAction != IDLE_JETPACK && jetpack) {
				currentAction = IDLE_JETPACK;
				animation.setFrames(sprites.get(IDLE_JETPACK));
				animation.setDelay(400);
				width = 32;
			}
		}

		animation.update();
		System.out.println(this.getHealth());

		// set direction
		if (currentAction != RAINBOW) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
		}
		if (currentAction != RAINBOW_JETPACK) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
		}

	}

	public void draw(Graphics2D g) {

		setMapPosition();

		// draw fireballs
		for (int i = 0; i < rainbows.size(); i++) {
			rainbows.get(i).draw(g);
		}
		// draw player
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		super.draw(g);

	}

	public ArrayList<Powerup> getPowerups() {
		return powerups;
	}

	public void setPowerups(ArrayList<Powerup> powerups) {
		this.powerups = powerups;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getRainbow() {
		return rainbow;
	}

	public int getMaxRainbow() {
		return maxRainbow;
	}

	public void setRainbowing() {
		rainbowing = true;
	}

	public int getRainbowcost() {
		return rainbowcost;
	}

	public void setRainbowcost(int rainbowcost) {
		this.rainbowcost = rainbowcost;
	}

	public int getRainbowdamage() {
		return rainbowdamage;
	}

	public void setRainbowdamage(int rainbowdamage) {
		this.rainbowdamage = rainbowdamage;
	}

	public ArrayList<Rainbow> getRainbows() {
		return rainbows;
	}

	public boolean isFlinching() {
		return flinching;
	}

	public void setFlinching(boolean flinching) {
		this.flinching = flinching;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public double getJumpStart() {
		return jumpStart;
	}

	public void setJumpStart(double d) {
		this.jumpStart = d;
	}

	public int getCollectedCoin() {
		return collectedCoin;
	}

	public void setCollectedCoin(int collectedCoin) {
		this.collectedCoin = collectedCoin;
	}
}
