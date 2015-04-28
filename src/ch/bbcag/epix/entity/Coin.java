package ch.bbcag.epix.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.tilemap.TileMap;

public class Coin extends MapObject {
	
	private BufferedImage[] sprites;

	public Coin(TileMap tm) {
		super(tm);
		
		setFallSpeed(0.1);
		maxFallSpeed = 1.1;
		
		width = 32;
		height = 32;
		cwidth = 32;
		cheight = 32;
		
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Items/Coin.png"));

			sprites = new BufferedImage[8];
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
