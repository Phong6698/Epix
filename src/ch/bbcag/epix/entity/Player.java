package ch.bbcag.epix.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.entity.enemies.Plant;
import ch.bbcag.entity.enemies.ShootingPlant;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcag.epix.view.EpixView;

public class Player extends MapObject {

	// player stuff
	private int health;
	private int maxHealth;
	private int rainbow;
	private int maxRainbow;
	private boolean dead;
	private boolean flinching;
	
	public boolean isFlinching() {
		return flinching;
	}

	public void setFlinching(boolean flinching) {
		this.flinching = flinching;
	}

	private long flinchTimer;

	// fireball
	private boolean rainbowing;
	private int rainbowcost;
	private int rainbowdamage;
	private ArrayList<Rainbow> rainbows;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1, 6, 1, 3 };

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 2;

	private static final int RAINBOW = 3;

	public Player(TileMap tm) {

		super(tm);

		width = 16;
		height = 16;

		// hitbox
		cwidth = 10;
		cheight = 16;

		moveSpeed = 0.1;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 2.0;
		jumpStart = -2.5;
		stopJumpSpeed = 0.3;

		facingRight = true;

		health = maxHealth = 5;
		rainbow = maxRainbow = 2500;
		
		rainbowdamage = 5;
		rainbows = new ArrayList<Rainbow>();

		// load sprites
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Player/Player.png"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 3; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++) {

					if (i != RAINBOW) {
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					} else {
						bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
					}

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
	public void checkAttackPlants(ArrayList<Plant> plants) {
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
	public void checkAttackShootingPlants(ArrayList<ShootingPlant> shootingplants) {

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
				e.update();
				hit(e.getDamage());
			}

		}
	}
	
	
	
	public void hit(int damage) {
		if (flinching)
			return;
		health -= damage;
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	private void getNextPosition() {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
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
		if (jumping && !falling) {
			dy = jumpStart;
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

	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check attack has stopped
		if (currentAction == RAINBOW) {
			if (animation.hasPlayedOnce())
				rainbowing = false;
		}
		// fireball attack
		if (rainbowing && currentAction != RAINBOW) {
			Rainbow fb = new Rainbow(tileMap, facingRight);
			fb.setPosition(x, y);
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
			if (currentAction != RAINBOW) {
				currentAction = RAINBOW;
				animation.setFrames(sprites.get(RAINBOW));
				animation.setDelay(100);
				width = 16;
			}
		} else if (dy > 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-4);
				width = 16;
			}
		}

		else if (dy < 0) {
			if (currentAction != FALLING) {
				currentAction = FALLING;
				
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 16;
			}
		}

		else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(60);
				width = 16;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 16;
			}
		}

		animation.update();

		// set direction
		if (currentAction != RAINBOW) {
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



}
