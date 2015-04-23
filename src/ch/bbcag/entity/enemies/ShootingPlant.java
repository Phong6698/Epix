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
	private double a;

	// position and vector

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

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/PlantShooting_2.png"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 2; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++) {

					if (i != SHOOT) {
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					} else {
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					}

				}

				sprites.add(bi);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		animation.setFrames(sprites.get(IDLE));
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
//		System.out.println("Plant:");
//		System.out.println("xmap = "+ xmap + "      ||     ymap = " + ymap);
//		System.out.println("x =    "+ x +    "      ||     y = " + y);

		// fireball attack
		
		a = xmap - xmap - xmap;
		
			OnScreen(a);

		// update animation
		animation.update();
	}
	
	
	
	public void OnScreen(double a) {
		if (a < x && xmap < 0 ){
			System.out.println(a);
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

		super.draw(g);

	}
}
