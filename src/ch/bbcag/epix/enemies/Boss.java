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
 * 
 * @author Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *         Boss.java.java Copyright Berufsbildungscenter 2015
 */

public class Boss extends Enemy {

	private boolean hit;
	private boolean remove;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 6, 1, 5, 10 };

	private static final int WALK = 0;
	private static final int IDLE = 1;
	private static final int SHOOT = 2;
	private static final int BOSSATTACK = 3;

	private boolean onscreen;

	private boolean shooting;

	private boolean shotright;

	private long timer;
	private long time = 450; // animation delay * anzahl sprites
	private int range;

	private double fallSpeed = 0.1;
	private double maxFallSpeed = 10.0;

	private ArrayList<BossShot> bossshots;

	public Boss(TileMap tm, Player player) {

		super(tm);

		moveSpeed = 1;
		maxMoveSpeed = 1;

		width = 64;
		height = 64;
		cwidth = 37;
		cheight = 64;
		range = 224;

		health = maxHealth = 40;
		damage = 10;

		bossshots = new ArrayList<BossShot>();

		// load sprites

		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/Boss.png"));
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 4; i++) {

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
		width = 64;
	}

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

		if (currentAction == WALK) {
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
	public void checkAttackPlayer(Player playerHit, Player playerHealth){
		for (int j = 0; j < bossshots.size(); j++) {
			if (bossshots.get(j).intersects(playerHit)) {
				playerHit.hit(damage, playerHealth);
				bossshots.get(j).setHit();
				break;
			}
		}
	}
	public void update(Boss m, Player player, boolean b) {

		// update position

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		for (int i = 0; i < bossshots.size(); i++) {
			bossshots.get(i).update(m, player);
			if (bossshots.get(i).shouldRemove()) {
				bossshots.remove(i);
				i--;
			}
		}

		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}else if (OnScreen(m, 120)) {
			
			if (b == true && currentAction != BOSSATTACK) {
			currentAction = BOSSATTACK;
			animation.setFrames(sprites.get(BOSSATTACK));
			animation.setDelay(150);
			width = 64;
			}
		} 
		else if (OnScreen(m, 240)) {
			if (currentAction != SHOOT && b == false) {
				currentAction = SHOOT;
				animation.setFrames(sprites.get(SHOOT));
				animation.setDelay(150);
				width = 64;
			} 
		} 
		

		else {
			if (currentAction != WALK) {
				currentAction = WALK;
				right = true;
				animation.setFrames(sprites.get(WALK));
				animation.setDelay(100);
				width = 64;
			}
		}

		if (currentAction == SHOOT || currentAction == BOSSATTACK) {
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

//			if (animation.getFrame() == 4) {
//				BossShot ps = new BossShot(tileMap, shotright, player);
//				ps.setPosition(m.getx(), m.gety());
//				if (timer + time <= System.currentTimeMillis()) {
//					bossshots.add(ps);
//					timer = System.currentTimeMillis();
//				}
//			}

		}
		animation.update();

	}

	public boolean OnScreen(Boss e, int range) {
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

		for (int i = 0; i < bossshots.size(); i++) {
			bossshots.get(i).draw(g);
		}

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		super.draw(g);

	}

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

	public ArrayList<BossShot> getBossshots() {
		return bossshots;
	}

	public void setBossshots(ArrayList<BossShot> bossshots) {
		this.bossshots = bossshots;
	}

	public static int getBossattack() {
		return BOSSATTACK;
	}

}
