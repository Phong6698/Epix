package ch.bbcag.entity.powerups;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.entity.Animation;
import ch.bbcag.epix.entity.Powerup;
import ch.bbcag.epix.tilemap.TileMap;

public class Heart extends Powerup {
	

	private BufferedImage[] sprites;

	public Heart(TileMap tm) {
		super(tm);
	
		plusHealth = 3;
		plusJump = 0;
		plusDamage = 0;
		plusSpeed = 0;
		expireTime = 0;
		
		width = 16;
		height = 16;
		cwidth = 8;
		cheight = 16;
		
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
			dy += fallSpeed;
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
