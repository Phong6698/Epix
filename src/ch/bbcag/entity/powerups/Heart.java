package ch.bbcag.entity.powerups;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Powerup;
import ch.bbcag.epix.tilemap.TileMap;

/**
 * 
 * @author  Miguel Jorge, Penglerd Chiramet Phong || ICT Berufsbildungs AG
 *			Copyright Berufsbildungscenter 2015
 */

public class Heart extends Powerup {
	

	private BufferedImage[] sprites;

	public Heart(TileMap tm) {
		super(tm);
	
		plusHealth = 40;
		
		expireTime = 0;
		
		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;
		
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Powerups/PowerUps_Heart.png"));

			sprites = new BufferedImage[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(100);

		right = true;
		facingRight = true;
	}
	
	private void getNextPosition() {
		// falling
		if(falling) {
			dy += getFallSpeed();
		}
		
	}
	
	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();

		// update animation
		animation.update();

	}
	
	public void draw(Graphics2D g) {

		// if(notOnScreen()) return;

		setMapPosition();

		super.draw(g);

	}
	

}
