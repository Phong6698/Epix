package ch.bbcg.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Enemy;
import ch.bbcag.epix.entity.Player;
import ch.bbcag.epix.tilemap.TileMap;

public class ShootingPlant extends Enemy {

	private BufferedImage[] sprites;
	private BufferedImage[] hitsprites;

	public ShootingPlant(TileMap tm, boolean b) {
		super(tm);

		fallSpeed = 1;
		maxFallSpeed = 1.1;

		width = 32;
		height = 16;
		cwidth = 8;
		cheight = 16;

		health = maxHealth = 20;
		damage = 10;
			// load sprites
			try {

				BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/PlantShooting_Attack.png"));

				sprites = new BufferedImage[6];
				for (int i = 0; i < sprites.length; i++) {
					sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);

		right = true;
		facingRight = true;

	}

	private void getNextPosition() {
		// falling
		if (falling) {
			dy += fallSpeed;
		}

	}

	public void update() {

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

		// update animation
		animation.update();
	}

	public void draw(Graphics2D g) {

		// if(notOnScreen()) return;

		setMapPosition();

		super.draw(g);

	}
}
