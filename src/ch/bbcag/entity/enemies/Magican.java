package ch.bbcag.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Enemy;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.TileMap;

@SuppressWarnings("unused") public class Magican extends Enemy {

	private boolean hit;
	private boolean remove;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 6, 3 };

	private static final int IDLE = 0;
	private static final int SHOOT = 1;

	protected boolean flinching;
	protected long flinchTimer;
	private boolean onscreen;

	private boolean shooting;
	private boolean shotright;

	private long timer;
	private long time = 250;
	private int wizarddamage = 10;
	private int range;

	private ArrayList<MagicanShot> magicanshots;

	public Magican(TileMap tm, Player player) {

		super(tm);

		moveSpeed = 1;
		maxSpeed = 1;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;

		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;
		range = 224;

		health = maxHealth = 2;
		damage = 0;

		magicanshots = new ArrayList<MagicanShot>();

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
				if (dx < -maxSpeed) {
					dx = -maxSpeed;
				}
			} else if (right) {
				dx += moveSpeed;
				if (dx > maxSpeed) {
					dx = maxSpeed;
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

	public void update(Magican m, Player player) {

		// update position

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		for (int i = 0; i < magicanshots.size(); i++) {
			magicanshots.get(i).update(m, player);
			if (magicanshots.get(i).shouldRemove()) {
				magicanshots.remove(i);
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
				animation.setDelay(250);
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
				MagicanShot ps = new MagicanShot(tileMap, shotright, player);
				ps.setPosition(m.getx(), m.gety());
				if (timer + time <= System.currentTimeMillis()) {
					magicanshots.add(ps);
					timer = System.currentTimeMillis();
				}
			}

			MagicanShot ps = new MagicanShot(tileMap, shotright, player);
			ps.setPosition(m.getx(), m.gety());
			if (timer + time <= System.currentTimeMillis()) {
				magicanshots.add(ps);
				timer = System.currentTimeMillis();
			}
		}
		
		animation.update();

	}

	public boolean OnScreen(Magican e, int range) {
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

		for (int i = 0; i < magicanshots.size(); i++) {
			magicanshots.get(i).draw(g);
		}

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		super.draw(g);

	}
}
