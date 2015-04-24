package ch.bbcag.entity.enemies;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Enemy;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * 
 * @author Miguel Jorge, ICT Berufsbildungs AG, miguel.jorge@bbcag.ch
 *			***.java Copyright Berufsbildungscenter 2015
 */

public class Plant extends Enemy {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 4, 5};
	private static final int IDLE = 0;
	private static final int ATTACK = 1;
	
	public Plant(TileMap tm) {
		super(tm);
		fallSpeed = 1;
		maxFallSpeed = 1.1;

		width = 16;
		height = 16;
		cwidth = 8;
		cheight = 16;

		health = maxHealth = 10;
		damage = 1;
		
		
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/PlantStanding.png"));
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 2; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++) {

					if (i != ATTACK) {
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

		right = true;
		facingRight = true;

	}
	
	private void getNextPosition() {
		// falling
		if(falling) {
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