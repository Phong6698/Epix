package ch.bbcag.epix.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bbcag.epix.tilemap.TileMap;

public class Flag extends MapObject{
	
	private BufferedImage[] sprites;

	public Flag(TileMap tm) {
		super(tm);

		width = 32;
		height = 32;
		cwidth = 10;
		cheight = 32;
		
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Others/Flag.png"));

			sprites = new BufferedImage[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(130);

		right = true;
		facingRight = true;
	}
	
	public void update() {

		// update position

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
