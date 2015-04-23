package ch.bbcag.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.*;
import ch.bbcag.epix.tilemap.TileMap;
import ch.bbcag.epix.view.EpixView;

public class ShootingPlant extends Enemy {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 4, 6 };

	private static final int IDLE = 0;
	private static final int SHOOT = 1;
	private boolean onscreen;
	private boolean shooting;
	private boolean remove;

	private ArrayList<PlantShot> plantshots;

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

		health = maxHealth = 20;
		damage = 10;
		// load sprites
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
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(100);

		right = true;
		facingRight = true;
	}

	private void getNextPosition() {
		// falling
		if (falling) {
			dy += fallSpeed;
		}

	}

	public void update(ShootingPlant e) {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}
		// if (shooting) {
		// if (currentAction != SHOOT) {
		// currentAction = SHOOT;
		// animation.setFrames(sprites.get(SHOOT));
		// animation.setDelay(100);
		// width = 16;
		// }
		//
		// // } else {
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		width = 16;
		// }
		// fireball attack
		// if (shooting && currentAction != SHOOT) {
		// PlantShot fb = new PlantShot(tileMap);
		// fb.setPosition(x, y);
		// plantshots.add(fb);
		// }

		// update fireballs
		// for (int i = 0; i < plantshots.size(); i++) {
		// plantshots.get(i).update();
		// if (plantshots.get(i).shouldRemove()) {
		// plantshots.remove(i);
		// i--;
		// }
		// }

		// fireball attack
		if (OnScreen(e, range) == true) {
			e.setCurrentAction(SHOOT);

		} else {
			e.setCurrentAction(IDLE);
		}

		// update animation
		animation.update();

		if (currentAction != SHOOT) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
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
		// draw fireballs
		super.draw(g);

	}

	public ArrayList<PlantShot> getPlantshots() {
		return plantshots;
	}

	public void setPlantshots(ArrayList<PlantShot> plantshots) {
		this.plantshots = plantshots;
	}
}
