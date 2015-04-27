package ch.bbcag.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Enemy;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.TileMap;

public class ShootingPlant extends Enemy {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 4, 6 };

	private static final int IDLE = 0;
	private static final int SHOOT = 1;
	private boolean onscreen;
	private boolean shooting;
	private boolean remove;
	private boolean shotright;
	private long timer;
	private long time = 151;
	private int plantshotsdamage = 10;

	

	private ArrayList<PlantShot> plantshots;
	private int range = 112;

	// position and vector

	public ShootingPlant(TileMap tm, boolean b) {
		super(tm);

		fallSpeed = 1;
		maxFallSpeed = 1.1;

		width = 16;
		height = 16;
		cwidth = 8;
		cheight = 16;

		range = 112;
		timer = System.currentTimeMillis();
		health = maxHealth = 20;
		damage = 10;
		// load sprites

		plantshots = new ArrayList<PlantShot>();

		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/PlantShooting.png"));
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 2; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++) {

					if (i != SHOOT) {
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
		animation.setFrames(sprites.get(currentAction));
		animation.setDelay(100);
		width = 16;

	}

	private void getNextPosition() {
		// falling
		if (falling) {
			dy += fallSpeed;
		}

	}
	
	public void checkAttackPlayer(Player player){
		for (int j = 0; j < plantshots.size(); j++) {
			if (plantshots.get(j).intersects(player)) {
				player.hit(plantshotsdamage);
				plantshots.get(j).setHit();
				break;
			}
		}
	}

	public void update(ShootingPlant e, Player player) {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		for (int i = 0; i < plantshots.size(); i++) {
			plantshots.get(i).update(player, e);
			if (plantshots.get(i).shouldRemove()) {
				plantshots.remove(i);
				i--;
			}
		}
		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		} else if (OnScreen(e, range )) {
			if (currentAction != SHOOT) {
				currentAction = SHOOT;
				animation.setFrames(sprites.get(SHOOT));
				animation.setDelay(150);
				width = 32;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 16;
			}
		}

		if (currentAction == SHOOT) {
			if (facingRight) {
				shotright = true;
			} else {
				shotright = false;
			}
			if (animation.getFrame() == 3) {
				PlantShot ps = new PlantShot(tileMap, shotright , player);
				ps.setPosition(e.getx(), e.gety());
				if (timer + time <= System.currentTimeMillis()) {
					plantshots.add(ps);
					timer = System.currentTimeMillis();
				}
			}
		}

		animation.update();
		if (true) {
			if (e.getx() > player.getx()) {
				facingRight = true;
			} else {
				facingRight = false;
			}
		}
	}

	public boolean OnScreen(ShootingPlant e, int range) {
		double a = e.getXmap();
		double spielerkoordinaten = (a - a - a) + range;
		if (e.getx() + range > spielerkoordinaten && e.getx() - spielerkoordinaten < range) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOnscreen() {
		return onscreen;
	}

	public void setOnscreen(boolean onscreen) {
		this.onscreen = onscreen;
	}

	public void draw(Graphics2D g) {

		// if(notOnScreen()) return;

		setMapPosition();

		for (int i = 0; i < plantshots.size(); i++) {
			plantshots.get(i).draw(g);
		}

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		// draw fireballs
		super.draw(g);

	}
	
	
	public ArrayList<PlantShot> getPlantshots() {
		return plantshots;
	}

	public void setPlantshots(ArrayList<PlantShot> plantshots) {
		this.plantshots = plantshots;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}
	public boolean isShotright() {
		return shotright;
	}

	public void setShotright(boolean shotright) {
		this.shotright = shotright;
	}
}
