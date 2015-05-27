package ch.bbcag.epix.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Enemy;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * Zauberer
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class Magician extends Enemy {

	private boolean hit;
	private boolean remove;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 6, 3 };

	private static final int IDLE = 0;
	private static final int SHOOT = 1;

	private boolean onscreen;

	private boolean shooting;
	
	private boolean shotright;

	private long timer;
	private long time = 450; //animation delay * anzahl sprites 
	private int range;

	private ArrayList<MagicianShot> magicianshots;

	
	/**
	 * Konstruktor
	 * @param tm {@link TileMap}
	 * @param player {@link Player}
	 */
	public Magician(TileMap tm, Player player) {

		super(tm);

		moveSpeed = 1;
		maxMoveSpeed = 1;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;

		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;
		range = 224;

		health = maxHealth = 40;
		damage = 10;

		magicianshots = new ArrayList<MagicianShot>();

		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/Wizard.png"));
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 2; i++) {

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
		width = 32;
	}

	
	/**
	 * Bewegung
	 */
	private void getNextPosition() {

		if (left && dx == 0) {
			right = true;
			left = false;
			facingRight = false;
			shotright = true;
		} else if (right && dx == 0) {
			right = false;
			left = true;
			facingRight = true;
			shotright = false;

		}

		if (currentAction == IDLE) {
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
			}
		} else {
			dx = 0;
		}

		// falling
		if (falling) {
			dy += fallSpeed;
		}

	}
	
	
	/**
	 * Wenn Zeuberer Spieler angreift
	 * @param playerHit {@link Player}
	 * @param playerHealth {@link Player}
	 */
	public void checkAttackPlayer(Player playerHit, Player playerHealth){
		for (int j = 0; j < magicianshots.size(); j++) {
			if (magicianshots.get(j).intersects(playerHit)) {
				playerHit.hit(damage, playerHealth);
				magicianshots.get(j).setHit();
				break;
			}
		}
	}

	/**
	 * Update 
	 * @param m {@link Magician}
	 * @param player {@link Player}
	 */
	public void update(Magician m, Player player) {

		// update position

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		for (int i = 0; i < magicianshots.size(); i++) {
			magicianshots.get(i).update(m, player);
			if (magicianshots.get(i).shouldRemove()) {
				magicianshots.remove(i);
				i--;
			}
		}

		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		} else if (OnScreen(m, 224)) {
			if (currentAction != SHOOT) {
				currentAction = SHOOT;
				animation.setFrames(sprites.get(SHOOT));
				animation.setDelay(150);
				width = 32;
			}
		} else {
			if (currentAction != IDLE) {
				right = true;
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(100);
				width = 32;
			}
		}

		if (currentAction == SHOOT) {
			if (m.getx() > player.getx()) {
				facingRight = true;
			} else {
				facingRight = false;
			}
			if (facingRight) {
				shotright = true;
			} else {
				shotright = false;
			}

			if (animation.getFrame() == 2) {
				MagicianShot ps = new MagicianShot(tileMap, shotright, player);
				ps.setPosition(m.getx(), m.gety());
				if (timer + time <= System.currentTimeMillis()) {
					magicianshots.add(ps);
					timer = System.currentTimeMillis();
				}
			}

		}
		
		animation.update();
	}

	
	/**
	 * Ob der Zauberer den Spieler sieht
	 * @param e {@link Magician}
	 * @param range
	 * @return Ob Boss den Spieler sieht
	 */
	public boolean OnScreen(Magician e, int range) {
		double a = e.getXmap();
		double spielerkoordinaten = (a - a - a) + range;
		if (e.getx() + range > spielerkoordinaten && e.getx() - spielerkoordinaten < range) {
			return true;
		} else {
			return false;
		}
	}

	public void draw(Graphics2D g) {

		// if(notOnScreen()) return;

		setMapPosition();

		for (int i = 0; i < magicianshots.size(); i++) {
			magicianshots.get(i).draw(g);
		}

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		super.draw(g);

	}
	
	
	/*
	 * Getter und Setter
	 */
	public boolean isHit() {
		return hit;
	}

	public boolean isRemove() {
		return remove;
	}

	public ArrayList<BufferedImage[]> getSprites() {
		return sprites;
	}

	public int[] getNumFrames() {
		return numFrames;
	}

	public static int getIdle() {
		return IDLE;
	}

	public static int getShoot() {
		return SHOOT;
	}

	public boolean isOnscreen() {
		return onscreen;
	}

	public boolean isShooting() {
		return shooting;
	}

	public boolean isShotright() {
		return shotright;
	}

	public long getTimer() {
		return timer;
	}

	public long getTime() {
		return time;
	}

	public int getRange() {
		return range;
	}

	public ArrayList<MagicianShot> getMagicianshots() {
		return magicianshots;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public void setSprites(ArrayList<BufferedImage[]> sprites) {
		this.sprites = sprites;
	}

	public void setOnscreen(boolean onscreen) {
		this.onscreen = onscreen;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public void setShotright(boolean shotright) {
		this.shotright = shotright;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setMagicianshots(ArrayList<MagicianShot> magicianshots) {
		this.magicianshots = magicianshots;
	}

}
